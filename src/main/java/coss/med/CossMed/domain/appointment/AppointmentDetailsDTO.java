package coss.med.CossMed.domain.appointment;

import java.time.LocalDateTime;

public record AppointmentDetailsDTO(Long id, Long doctorId, Long patientId, LocalDateTime date) {

}
