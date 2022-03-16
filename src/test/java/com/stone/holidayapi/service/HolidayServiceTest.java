package com.stone.holidayapi.service;

import com.stone.holidayapi.integration.HolidayConsumer;
import com.stone.holidayapi.model.HolidayItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyShort;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class HolidayServiceTest {

    @Mock
    HolidayConsumer holidayConsumer;
    @InjectMocks
    HolidayService service;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetHolidayCountByYear() {
        var countryCode = "LV";
        var year = 2022;
        var name = "New Year";
        var holidayItem = HolidayItem.builder()
                .name(name)
                .build();
        when(holidayConsumer.getHolidayItems(anyString(), anyShort())).thenReturn(Collections.singletonList(holidayItem));

        var actual = service.getHolidayCountByYear(countryCode, (short) year);

        verify(holidayConsumer, times(1)).getHolidayItems(any(), any());
        assertThat(actual).isEqualTo("Holiday count in LV in 2022 is: 1");
    }

    @Test
    void testGetHolidayItemByCountryAndYearIsHoliday() {
        var countryCode = "LV";
        var year = "01012022";
        var name = "New Year";
        var holidayItem = HolidayItem.builder()
                .name(name)
                .date(LocalDate.of(2022, 1, 1))
                .build();
        when(holidayConsumer.getHolidayItems(anyString(), anyShort())).thenReturn(Collections.singletonList(holidayItem));

        var actual = service.getHolidayItemByCountryAndYear(countryCode, year);

        verify(holidayConsumer, times(1)).getHolidayItems(any(), any());
        assertThat(actual).isEqualTo("Date 2022-01-01 in country LV is holiday: New Year");
    }

    @Test
    void testGetHolidayItemByCountryAndYearNotHoliday() {
        var countryCode = "LV";
        var year = "02012022";
        var name = "New Year";
        var holidayItem = HolidayItem.builder()
                .name(name)
                .date(LocalDate.of(2022, 1, 1))
                .build();
        when(holidayConsumer.getHolidayItems(anyString(), anyShort())).thenReturn(Collections.singletonList(holidayItem));

        var actual = service.getHolidayItemByCountryAndYear(countryCode, year);

        verify(holidayConsumer, times(1)).getHolidayItems(any(), any());
        assertThat(actual).isEqualTo("Not a holiday");
    }

    @Test
    void testGetHolidayItemByCountryAndYearWrongDateFormat() {
        var countryCode = "LV";
        var year = "32012022";

        var actual = service.getHolidayItemByCountryAndYear(countryCode, year);

        verify(holidayConsumer, times(0)).getHolidayItems(any(), any());
        assertThat(actual).isEqualTo("Incorrect date input");
    }
}