package coss.med.CossMed.domain.doctor;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	Page<Doctor> findAllByActiveTrue(Pageable pagination);

	@Query("""
			SELECT m FROM Doctor m
			WHERE
			m.active = true
			AND
			m.specialty = :specialty
			AND
			m.id not in(
				SELECT a.doctor.id FROM Appointment a
				WHERE
				a.date = :date
			)
			ORDER BY rand()
			LIMIT 1
			""")
	Doctor chooseRandomAvailableDoctor(Specialty specialty, LocalDateTime date);
}
