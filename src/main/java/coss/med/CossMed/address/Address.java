package coss.med.CossMed.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

	private String street;
	private String neighborhood;
	private String zip_code;
	private String city;
	private String state;
	private String complement;
	private String number;

	public Address(AddressRecord body) {
		this.street = body.street();
		this.neighborhood = body.neighborhood();
		this.zip_code = body.zip_code();
		this.city = body.city();
		this.state = body.state();
		this.complement = body.complement();
		this.number = body.number();
	}

}