package coss.med.CossMed.domain.appointment;

import java.time.LocalDateTime;

import coss.med.CossMed.domain.doctor.Specialty;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public record AppointmentDataDTO(
		Long doctorId,

		@NotNull Long patientId,

		@NotNull @Future LocalDateTime date,

		Specialty specialty) {

}
