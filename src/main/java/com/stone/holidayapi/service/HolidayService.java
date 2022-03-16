package com.stone.holidayapi.service;

import com.stone.holidayapi.integration.HolidayConsumer;
import com.stone.holidayapi.model.HolidayItem;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

@Service
public class HolidayService {

    private final HolidayConsumer holidayConsumer;

    public HolidayService(HolidayConsumer holidayConsumer) {
        this.holidayConsumer = holidayConsumer;
    }

    public String getHolidayItemByCountryAndYear(String countryCode, String date) {
        short day = Short.parseShort((date.substring(0, 2)));
        short month = Short.parseShort((date.substring(2, 4)));
        short year = Short.parseShort((date.substring(4)));
        LocalDate localDate = null;
        try {
            localDate = LocalDate.of(year, month, day);
        } catch (DateTimeException e) {
            return "Incorrect date input";
        }
        List<HolidayItem> holidayItems = holidayConsumer.getHolidayItems(countryCode, year);
        LocalDate dateToCompare = localDate;
        HolidayItem holiday = holidayItems.stream()
                .filter(holidayItem -> holidayItem.getDate()
                        .compareTo(dateToCompare) == 0)
                .findFirst()
                .orElse(null);

        if (holiday == null) {
            return "Not a holiday";
        }
        return "Date " + holiday.getDate() + " in country " + countryCode.toUpperCase() + " is holiday: " + holiday.getName();
    }

    public String getHolidayCountByYear(String countryCode, Short year) {
        List<HolidayItem> holidayItems = holidayConsumer.getHolidayItems(countryCode, year);
        return "Holiday count in " + countryCode.toUpperCase() + " in " + year + " is: " + holidayItems.size();
    }
}
