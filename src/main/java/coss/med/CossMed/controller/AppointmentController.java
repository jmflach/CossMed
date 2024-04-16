package coss.med.CossMed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import coss.med.CossMed.domain.appointment.AppointmentDataDTO;
import coss.med.CossMed.domain.appointment.AppointmentDetailsDTO;
import coss.med.CossMed.domain.appointment.AppointmentScheduler;
import jakarta.validation.Valid;

@RestController
@RequestMapping("appointments")
public class AppointmentController {

	@Autowired
	private AppointmentScheduler scheduler;
	
    @PostMapping
    @Transactional
    public ResponseEntity<AppointmentDetailsDTO> makeAppointment(@RequestBody @Valid AppointmentDataDTO data) {
    	
    	scheduler.schedule(data);
        System.out.println(data);
        return ResponseEntity.ok(new AppointmentDetailsDTO(null, null, null, null));
    }

}
