package coss.med.CossMed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import coss.med.CossMed.doctor.Doctor;
import coss.med.CossMed.doctor.DoctorDataDTO;
import coss.med.CossMed.doctor.DoctorRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("doctors")
public class DoctorController {
	
	@Autowired
	private DoctorRepository repository;
	
	@Transactional
	@PostMapping
	public void register(@RequestBody @Valid DoctorDataDTO body) {
		System.out.println(body);
		
		repository.save(new Doctor(body));
	}
}
