package coss.med.CossMed.domain.patient;

public record PatientListDataDTO(
		Long id,
		String name,
		String email,
		String cpf) {
	public PatientListDataDTO (Patient patient) {
		this(patient.getId(), patient.getName(), patient.getEmail(), patient.getCpf());
	}
}
