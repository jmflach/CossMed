package coss.med.CossMed.domain.doctor;

import coss.med.CossMed.domain.address.Address;

public record DoctorDetailsDTO(Long id, String name, String email, String crm, String phone, Specialty specialty, Address address) {

	public DoctorDetailsDTO(Doctor doctor) {
		this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getPhone(), doctor.getSpecialty(), doctor.getAddress());
	}
}