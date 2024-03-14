package coss.med.CossMed.patient;

import coss.med.CossMed.address.AddressRecord;

public record PatientDataDTO(String name, String email, String telephone, String cpf, AddressRecord address) {

}
