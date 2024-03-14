package coss.med.CossMed.doctor;

import coss.med.CossMed.address.AddressRecord;

public record DoctorDataDTO(String name, String email, String crm, Specialty specialty, AddressRecord address) {

}
