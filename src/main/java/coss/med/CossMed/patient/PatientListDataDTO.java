package coss.med.CossMed.patient;

public record PatientListDataDTO(
		String name,
		String email,
		String cpf) {
	public PatientListDataDTO (Patient patient) {
		this(patient.getName(), patient.getEmail(), patient.getCpf());
	}
}
