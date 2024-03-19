package coss.med.CossMed.patient;

import coss.med.CossMed.address.Address;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "patients")
@Entity
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String email;
	private String phone;
	private String cpf;
	
	@Embedded
	private Address address;
	
	public Patient(PatientDataDTO body) {
		this.name = body.name();
		this.email = body.email();
		this.phone = body.phone();
		this.cpf = body.cpf();
		this.address = new Address(body.address());
	}
	
	public void updateData(PatientUpdateDataDTO data) {
		if (data.name() != null) {
			this.name = data.name();
		}
		if (data.phone() != null) {
			this.phone = data.phone();
		}
		if (data.address() != null) {
			this.address.updateData(data.address());
		}
	}
}
