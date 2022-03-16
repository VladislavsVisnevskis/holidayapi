package com.stone.holidayapi.integration;

import com.stone.holidayapi.model.HolidayItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class HolidayConsumerTest {

    private static final String URL = "https://date.nager.at";
    @Mock
    private WebClient webClient;
    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;
    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;
    @Mock
    private WebClient.RequestBodyUriSpec requestBodyUriSpec;
    @Mock
    private WebClient.RequestBodySpec requestBodySpec;
    @Mock
    private WebClient.ResponseSpec responseSpec;
    @Mock
    private WebClient.Builder builder;
    @InjectMocks
    private final HolidayConsumer consumer = new HolidayConsumer(URL);

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetHolidayItems() {
        String countryCode = "LV";
        LocalDate date = LocalDate.of(2022, 1, 1);
        HolidayItem holidayItem = HolidayItem.builder()
                .date(date)
                .build();
        mockGetList(singletonList(holidayItem));

        var actual = consumer.getHolidayItems(countryCode, (short) date.getYear());

        verify(webClient).get();
        assertThat(actual).isNotNull();
        assertThat(actual.size()).isEqualTo(1);
        assertThat(actual.get(0).getDate().compareTo(date)).isEqualTo(0);
    }

    private void mockGetList(List<HolidayItem> listResponse) {
        when(webClient.mutate()).thenReturn(builder);
        when(builder.exchangeStrategies(any(ExchangeStrategies.class))).thenReturn(builder);
        when(builder.build()).thenReturn(webClient);
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(ArgumentMatchers.<URI>notNull())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToFlux(any(Class.class))).thenReturn(Flux.just(listResponse.toArray()));
    }
}