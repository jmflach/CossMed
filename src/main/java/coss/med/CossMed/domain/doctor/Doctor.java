package coss.med.CossMed.domain.doctor;

import coss.med.CossMed.domain.address.Address;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "doctors")
@Entity(name = "Doctor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String phone;
	private String crm;

	@Enumerated(EnumType.STRING)
	private Specialty specialty;

	@Embedded
	private Address address;

	private Boolean active;
	
	public Doctor(DoctorDataDTO body) {
		this.name = body.name();
		this.email = body.email();
		this.phone = body.phone();
		this.crm = body.crm();
		this.specialty = body.specialty();
		this.address = new Address(body.address());
		this.active = true;
	}
	
	public void updateData(DoctorUpdateDataDTO data) {
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
	
	public void delete() {
		this.active = false;
	}

}
