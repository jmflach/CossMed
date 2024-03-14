package coss.med.CossMed.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import coss.med.CossMed.doctor.DoctorDataDTO;

@RestController
@RequestMapping("doctors")
public class DoctorController {
	
	@PostMapping
	public void register(@RequestBody DoctorDataDTO body) {
		System.out.println(body);
	}
}
