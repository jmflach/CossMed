package coss.med.CossMed.domain.appointment;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    boolean existsByDoctorIdAndDateAndCancellationReasonIsNull(Long doctorId, LocalDateTime date);

    boolean existsByPatientIdAndDateBetweenAndCancellationReasonIsNull(Long patientId, LocalDateTime openHours, LocalDateTime closeHours);

}