package coss.med.CossMed.doctor;

public record DoctorListDataDTO(
		String name,
		String email,
		String crm,
		Specialty specialty) {
	public DoctorListDataDTO(Doctor doctor) {
		this(doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
	}
}
