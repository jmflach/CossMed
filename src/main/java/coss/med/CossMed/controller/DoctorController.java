package coss.med.CossMed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import coss.med.CossMed.doctor.Doctor;
import coss.med.CossMed.doctor.DoctorDataDTO;
import coss.med.CossMed.doctor.DoctorListDataDTO;
import coss.med.CossMed.doctor.DoctorRepository;
import coss.med.CossMed.doctor.DoctorUpdateDataDTO;
import jakarta.validation.Valid;

@RestController
@RequestMapping("doctors")
public class DoctorController {
	
	@Autowired
	private DoctorRepository repository;
	
	@Transactional
	@PostMapping
	public void register(@RequestBody @Valid DoctorDataDTO body) {
		repository.save(new Doctor(body));
	}
	
	@GetMapping
	public Page<DoctorListDataDTO> list(@PageableDefault(size=10, page=0, sort="name") Pageable pagination) {
		return repository.findAll(pagination).map(DoctorListDataDTO::new);
	}
	
	@Transactional
	@PutMapping
	public void update(@RequestBody @Valid DoctorUpdateDataDTO body) {
		var doctor = repository.getReferenceById(body.id());
		doctor.updateData(body);
	}
	
	@Transactional
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
