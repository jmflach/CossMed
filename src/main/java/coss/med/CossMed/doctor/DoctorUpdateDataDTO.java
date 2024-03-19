package coss.med.CossMed.doctor;

import coss.med.CossMed.address.AddressRecord;
import jakarta.validation.constraints.NotNull;

public record DoctorUpdateDataDTO(
		@NotNull
		Long id,
		String name,
		String phone,
		AddressRecord address) {
}
