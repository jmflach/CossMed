package coss.med.CossMed.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import coss.med.CossMed.domain.appointment.AppointmentDataDTO;
import coss.med.CossMed.domain.appointment.AppointmentDetailsDTO;
import coss.med.CossMed.domain.appointment.AppointmentScheduler;
import coss.med.CossMed.domain.doctor.Specialty;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class AppointmentControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<AppointmentDataDTO> appointmentDataDTOJson;

    @Autowired
    private JacksonTester<AppointmentDetailsDTO> appointmentDetailsDTOJson;

    @MockBean
    private AppointmentScheduler appointmentScheduler;

    @Test
    @DisplayName("Should return HTTP 400 when informations are invalid.")
    @WithMockUser
    void testMakeAppointment_Scenario1() throws Exception {
        var response = mvc.perform(MockMvcRequestBuilders.post("/appointments"))
                .andReturn().getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Should return HTTP 200 when informations are valid.")
    @WithMockUser
    void testMakeAppointment_Scenario2() throws Exception {

        var date = LocalDateTime.now().plusHours(1);
        var specialty = Specialty.CARDIOLOGY;
        var appointment = new AppointmentDataDTO(2l, 5l, date, specialty);
        var expectedAppointmentDetails = new AppointmentDetailsDTO(null, 2l, 5l, date);

        when(appointmentScheduler.schedule(any())).thenReturn(expectedAppointmentDetails);

        var response = mvc.perform(
                MockMvcRequestBuilders
                        .post("/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(appointmentDataDTOJson.write(
                                appointment).getJson()))
                .andReturn().getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var expectedResponse = appointmentDetailsDTOJson.write(expectedAppointmentDetails).getJson();

        Assertions.assertThat(response.getContentAsString()).isEqualTo(expectedResponse);
    }
}
