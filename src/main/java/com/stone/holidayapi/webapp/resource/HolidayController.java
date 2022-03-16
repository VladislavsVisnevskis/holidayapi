package com.stone.holidayapi.webapp.resource;

import com.stone.holidayapi.service.HolidayService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@RestController
@RequestMapping("/holidayapi")
public class HolidayController implements HolidayInterface {

    private final HolidayService holidayService;

    public HolidayController(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @Override
    @GetMapping(value = "/{countryCode}/{date}")
    public ResponseEntity<String> getHolidayItemByCountryAndYear(@PathVariable @NotBlank @Size(max = 2, min = 2) String countryCode,
                                                                 @PathVariable @NotBlank @Size(max = 8, min = 8) String date) {
        try {
            Integer.parseInt(date);
        }
        catch (NumberFormatException e){
            return ResponseEntity.badRequest().body("Incorrect date input");
        }
        return ResponseEntity.ok(holidayService.getHolidayItemByCountryAndYear(countryCode, date));
    }

    @Override
    @GetMapping(value = "/{countryCode}/{year}/count")
    public ResponseEntity<String> getHolidayCount(@PathVariable @NotBlank @Size(max = 2, min = 2) String countryCode, Short year) {
        return ResponseEntity.ok(holidayService.getHolidayCountByYear(countryCode, year));
    }
}
