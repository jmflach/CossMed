package coss.med.CossMed.domain.appointment;

import jakarta.validation.constraints.NotNull;

public record AppointmentCancelDTO(
		@NotNull Long appointmentId,

		@NotNull CancellationReason reason) {
}
