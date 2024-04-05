package coss.med.CossMed.domain.doctor;

public record DoctorListDataDTO(
		Long id,
		String name,
		String email,
		String crm,
		Specialty specialty) {
	public DoctorListDataDTO(Doctor doctor) {
		this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
	}
}
