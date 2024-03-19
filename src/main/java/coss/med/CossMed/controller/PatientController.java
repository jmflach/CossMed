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

import coss.med.CossMed.patient.Patient;
import coss.med.CossMed.patient.PatientDataDTO;
import coss.med.CossMed.patient.PatientListDataDTO;
import coss.med.CossMed.patient.PatientRepository;
import coss.med.CossMed.patient.PatientUpdateDataDTO;
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
	
	@GetMapping
	public Page<PatientListDataDTO> listar(@PageableDefault(page = 0, size = 10, sort = {"name"}) Pageable pagination) {
	    return repository.findAllByActiveTrue(pagination).map(PatientListDataDTO::new);
	}
	
	@Transactional
	@PutMapping
	public void update(@RequestBody @Valid PatientUpdateDataDTO body) {
		var patient = repository.getReferenceById(body.id());
		
		patient.updateData(body);
	}
	
	@Transactional
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		var patient = repository.getReferenceById(id);
		
		patient.delete();
	}

}
