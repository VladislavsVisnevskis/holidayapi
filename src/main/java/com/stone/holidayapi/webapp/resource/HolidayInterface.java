package com.stone.holidayapi.webapp.resource;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public interface HolidayInterface {

    @ApiOperation(value = "Determines if the provided date is a holiday in the specified country", nickname = "getHolidayItemByCountryAndYear", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation", response = String.class),
            @ApiResponse(code = 404, message = "Not found")
    })
    ResponseEntity<String> getHolidayItemByCountryAndYear(@PathVariable String countryCode, @PathVariable String date);

    @ApiOperation(value = "Counts the number of holidays in country in certain year", nickname = "getHolidayItemByCountryAndYear", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation", response = String.class),
            @ApiResponse(code = 404, message = "Not found")
    })
    ResponseEntity<String> getHolidayCount(@PathVariable String countryCode, @PathVariable Short year);
}