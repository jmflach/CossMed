package coss.med.CossMed.domain.doctor;

import coss.med.CossMed.domain.address.AddressRecord;
import jakarta.validation.constraints.NotNull;

public record DoctorUpdateDataDTO(
		@NotNull
		Long id,
		String name,
		String phone,
		AddressRecord address) {
}
