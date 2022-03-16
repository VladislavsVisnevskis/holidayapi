package com.stone.holidayapi.webapp.resource;

import com.stone.holidayapi.service.HolidayService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = HolidayController.class)
class HolidayControllerTest {

    @MockBean
    HolidayService holidayService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCountHolidaysInYear() throws Exception {
        var countryCode = "LV";
        short year = 2022;
        var serviceResponse = "Holiday count in LV in 2022 is: 1";

        when(holidayService.getHolidayCountByYear(countryCode, year)).thenReturn(serviceResponse);

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/holidayapi/LV/2022/count"))
                .andExpect(status().isOk())
                .andExpect(content().string(serviceResponse));
    }

    @Test
    void shouldVerifyIfDateIsHolidaySuccessfully() throws Exception {
        var countryCode = "LV";
        var date = "01012022";
        var serviceResponse = "Date 2022-01-01 in country LV is holiday: New Year";

        when(holidayService.getHolidayItemByCountryAndYear(countryCode, date)).thenReturn(serviceResponse);

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/holidayapi/LV/01012022"))
                .andExpect(status().isOk())
                .andExpect(content().string(serviceResponse));
    }

    @Test
    void shouldVerifyIfDateIsHolidayWrongDateFormat() throws Exception {
        var expectedBody = "Incorrect date input";

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/holidayapi/LV/OI012022"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(expectedBody));
    }

}