package coss.med.CossMed.domain.doctor;

import coss.med.CossMed.domain.address.AddressRecord;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DoctorDataDTO(
		@NotBlank
		String name,
		@NotBlank
		@Email
		String email,
		@NotBlank
		String phone,
		@NotBlank
		@Pattern(regexp = "\\d{4,6}")
		String crm, 
		@NotNull
		Specialty specialty,
		@NotNull
		@Valid
		AddressRecord address) {
}
