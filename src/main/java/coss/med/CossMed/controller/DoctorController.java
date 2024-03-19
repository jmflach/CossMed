package coss.med.CossMed.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import coss.med.CossMed.doctor.Doctor;
import coss.med.CossMed.doctor.DoctorDataDTO;
import coss.med.CossMed.doctor.DoctorListDataDTO;
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
	
	@GetMapping
	public List<DoctorListDataDTO> list() {
		System.out.println("Executando GET");
		
		List<Doctor> docs = repository.findAll().stream().toList();
		
		System.out.println(docs);
		
		return repository.findAll().stream().map(DoctorListDataDTO::new).toList();
	}
}
