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

import coss.med.CossMed.domain.doctor.Doctor;
import coss.med.CossMed.domain.doctor.DoctorDataDTO;
import coss.med.CossMed.domain.doctor.DoctorDetailsDTO;
import coss.med.CossMed.domain.doctor.DoctorListDataDTO;
import coss.med.CossMed.domain.doctor.DoctorRepository;
import coss.med.CossMed.domain.doctor.DoctorUpdateDataDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("doctors")
@SecurityRequirement(name = "bearer-key")
public class DoctorController {
	
	@Autowired
	private DoctorRepository repository;
	
	@Transactional
	@PostMapping
	public ResponseEntity<DoctorDetailsDTO> register(@RequestBody @Valid DoctorDataDTO body, UriComponentsBuilder uriBuilder) {
		var doctor = new Doctor(body);
		repository.save(doctor);
		
		var uri = uriBuilder.path("/doctors/{id}").buildAndExpand(doctor.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new DoctorDetailsDTO(doctor));
	}
	
	@GetMapping
	public ResponseEntity<Page<DoctorListDataDTO>> list(@PageableDefault(size=10, page=0, sort="name") Pageable pagination) {
		// Get all doctors:
		// return repository.findAll(pagination).map(DoctorListDataDTO::new);
		// Get all active doctors:
		var page = repository.findAllByActiveTrue(pagination).map(DoctorListDataDTO::new);
		return ResponseEntity.ok(page);
	}
	
	@Transactional
	@PutMapping
	public ResponseEntity<DoctorDetailsDTO> update(@RequestBody @Valid DoctorUpdateDataDTO body) {
		var doctor = repository.getReferenceById(body.id());
		doctor.updateData(body);
		
		return ResponseEntity.ok(new DoctorDetailsDTO(doctor));
	}
	
	@Transactional
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		// Delete from DB:
		// repository.deleteById(id);
		
		// Logic delete (set as inactive):
		var doctor = repository.getReferenceById(id);
		doctor.delete();
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DoctorDetailsDTO> detail(@PathVariable Long id) {
		var doctor = repository.getReferenceById(id);
		return ResponseEntity.ok(new DoctorDetailsDTO(doctor));
	}
}
