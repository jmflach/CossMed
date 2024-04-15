package coss.med.CossMed.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import coss.med.CossMed.domain.appointment.AppointmentDataDTO;
import coss.med.CossMed.domain.appointment.AppointmentDetailsDTO;
import jakarta.validation.Valid;

@RestController
@RequestMapping("appointments")
public class AppointmentController {

    @PostMapping
    @Transactional
    public ResponseEntity<AppointmentDetailsDTO> makeAppointment(@RequestBody @Valid AppointmentDataDTO dados) {
        System.out.println(dados);
        return ResponseEntity.ok(new AppointmentDetailsDTO(null, null, null, null));
    }

}
