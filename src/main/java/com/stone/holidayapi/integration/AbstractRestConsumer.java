package com.stone.holidayapi.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

public abstract class AbstractRestConsumer {

    protected final String baseUrl;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    protected WebClient webClient;

    protected AbstractRestConsumer(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    protected WebClient.ResponseSpec getDefineSpec(URI uri) {
        return webClient.get()
                .uri(uri)
                .retrieve();
    }

    protected <R> Mono<R> getRequest(URI uri, Class<R> responseClass) {
        return getDefineSpec(uri)
                .bodyToMono(responseClass)
                .doOnError(this::getExceptionFromResponse);
    }

    protected <R> List<R> getListResponse(URI uri, Class<R> responseClass) {
        return makeRequest(webClient.get(), uri, responseClass)
                .collectList()
                .block();
    }

    private Mono<? extends Throwable> getExceptionFromResponse(Throwable throwable) {
        String errorMessage = String.format("Request failed with message: %s", throwable.getMessage());
        logger.error(errorMessage);
        return Mono.error(throwable);
    }

    private <R> Flux<R> makeRequest(WebClient.RequestHeadersUriSpec webClientRequest, URI uri, Class<R> responseClass) {
        return webClientRequest
                .uri(uri)
                .retrieve()
                .bodyToFlux(responseClass)
                .doOnError(this::getExceptionFromResponse);
    }
}
