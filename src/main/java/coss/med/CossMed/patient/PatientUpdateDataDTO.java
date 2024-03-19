package coss.med.CossMed.patient;

import coss.med.CossMed.address.AddressRecord;
import jakarta.validation.constraints.NotNull;

public record PatientUpdateDataDTO(
		@NotNull
		Long id,
		String name,
		String phone,
		AddressRecord address) {
}
