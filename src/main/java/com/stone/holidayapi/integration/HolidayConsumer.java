package com.stone.holidayapi.integration;

import com.stone.holidayapi.model.HolidayItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

import static org.springframework.web.util.UriComponentsBuilder.fromHttpUrl;

@Component
public class HolidayConsumer extends AbstractRestConsumer {

    @Autowired
    public HolidayConsumer(@Value("${date-nager.base-url}") String baseUrl) {
        super(baseUrl + "/api");
    }

    public List<HolidayItem> getHolidayItems(String countryCode, Short year) {
        URI uri = fromHttpUrl(baseUrl + "/v3/PublicHolidays/{year}/{countryCode}")
                .build(year, countryCode);
        return getListResponse(uri, HolidayItem.class);
    }
}
