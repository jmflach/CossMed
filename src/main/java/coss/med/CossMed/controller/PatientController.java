package coss.med.CossMed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import coss.med.CossMed.doctor.DoctorDetailsDTO;
import coss.med.CossMed.patient.Patient;
import coss.med.CossMed.patient.PatientDataDTO;
import coss.med.CossMed.patient.PatientDetailsDTO;
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
	public ResponseEntity<PatientDetailsDTO> register(@RequestBody @Valid PatientDataDTO body, UriComponentsBuilder uriBuilder) {
		var patient = new Patient(body);
		
		// Save the data
		repository.save(patient);
		
		var uri = uriBuilder.path("/patients/{id}").buildAndExpand(patient.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new PatientDetailsDTO(patient));
	}
	
	@GetMapping
	public ResponseEntity<Page<PatientListDataDTO>> listar(@PageableDefault(page = 0, size = 10, sort = {"name"}) Pageable pagination) {
	    var page = repository.findAllByActiveTrue(pagination).map(PatientListDataDTO::new);
	    
	    return ResponseEntity.ok(page);
	}
	
	@Transactional
	@PutMapping
	public ResponseEntity<PatientDetailsDTO> update(@RequestBody @Valid PatientUpdateDataDTO body) {
		var patient = repository.getReferenceById(body.id());
		patient.updateData(body);
		
		return ResponseEntity.ok(new PatientDetailsDTO(patient));
	}
	
	@Transactional
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		var patient = repository.getReferenceById(id);
		
		patient.delete();
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PatientDetailsDTO> detail(@PathVariable Long id) {
		var patient = repository.getReferenceById(id);
		
		return ResponseEntity.ok(new PatientDetailsDTO(patient));
	}

}
