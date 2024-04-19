package coss.med.CossMed.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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

import coss.med.CossMed.domain.address.AddressRecord;
import coss.med.CossMed.domain.doctor.Doctor;
import coss.med.CossMed.domain.doctor.DoctorDataDTO;
import coss.med.CossMed.domain.doctor.DoctorDetailsDTO;
import coss.med.CossMed.domain.doctor.DoctorRepository;
import coss.med.CossMed.domain.doctor.Specialty;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class DoctorControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DoctorDataDTO> DoctorDataJson;

    @Autowired
    private JacksonTester<DoctorDetailsDTO> DoctorDetailsJson;

    @MockBean
    private DoctorRepository repository;

    @Test
    @DisplayName("Should return HTTP 400 when informations are invalid.")
    @WithMockUser
    void testRegister_Scenario1() throws Exception {
        var response = mvc.perform(MockMvcRequestBuilders.post("/doctors"))
                .andReturn().getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Should return HTTP 200 when informations are valid.")
    @WithMockUser
    void testRegister_Scenario2() throws Exception {

        var doctorData = new DoctorDataDTO("Doctor", "doctor@email.com", "99999999999", "123456", Specialty.CARDIOLOGY,
                makeAddress());
        var doctor = new Doctor(doctorData);
        var expectedDoctorDetails = new DoctorDetailsDTO(doctor);

        when(repository.save(any())).thenReturn(doctor);

        var response = mvc.perform(
                MockMvcRequestBuilders
                        .post("/doctors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(DoctorDataJson.write(
                                doctorData).getJson()))
                .andReturn().getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        var expectedResponse = DoctorDetailsJson.write(expectedDoctorDetails).getJson();

        Assertions.assertThat(response.getContentAsString()).isEqualTo(expectedResponse);
    }

    private AddressRecord makeAddress() {
        return new AddressRecord(
                "foo",
                "bar",
                "00000000",
                "Marmeleiro",
                "PR",
                null,
                null);
    }
}
