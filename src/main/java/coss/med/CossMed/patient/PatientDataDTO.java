package coss.med.CossMed.patient;

import coss.med.CossMed.address.Address;

public record PatientDataDTO(String name, String email, String telephone, String cpf, Address address) {

}
