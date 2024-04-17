package coss.med.CossMed.domain.patient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PatientRepository extends JpaRepository<Patient, Long>{
	Page<Patient> findAllByActiveTrue(Pageable pagination);

	@Query("""
			SELECT m.active FROM Patient m
			WHERE
			m.id = :patientId
			""")
    Boolean findActiveById(Long patientId);
}
