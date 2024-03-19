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
	
	public void updateData(AddressRecord data) {
		if(this.street != null) {
			this.street = data.street();
		}
		if(this.neighborhood != null) {
			this.neighborhood = data.neighborhood();
		}
		if(this.zip_code != null) {
			this.zip_code = data.zip_code();
		}
		if(this.city != null) {
			this.city = data.city();
		}
		if(this.state != null) {
			this.state = data.state();
		}
		if(this.complement != null) {
			this.complement = data.complement();
		}
		if(this.number != null) {
			this.number = data.number();
		}
		
	}

}