package coss.med.CossMed.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import coss.med.CossMed.patient.PatientDataDTO;

@RestController
@RequestMapping("patients")
public class PatientController {

	@PostMapping
	public void register(@RequestBody PatientDataDTO body) {
		System.out.println(body);
	}

}
