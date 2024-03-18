package coss.med.CossMed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import coss.med.CossMed.patient.Patient;
import coss.med.CossMed.patient.PatientDataDTO;
import coss.med.CossMed.patient.PatientRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("patients")
public class PatientController {

	@Autowired
	private PatientRepository repository;
	
	@Transactional
	@PostMapping
	public void register(@RequestBody @Valid PatientDataDTO body) {
		System.out.println(body);
		
		// Save the data
		repository.save(new Patient(body));
	}

}
