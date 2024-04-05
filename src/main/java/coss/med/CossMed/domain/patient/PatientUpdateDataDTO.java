package coss.med.CossMed.domain.patient;

import coss.med.CossMed.domain.address.AddressRecord;
import jakarta.validation.constraints.NotNull;

public record PatientUpdateDataDTO(
		@NotNull
		Long id,
		String name,
		String phone,
		AddressRecord address) {
}
