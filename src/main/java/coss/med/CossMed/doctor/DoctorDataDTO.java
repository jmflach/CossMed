package coss.med.CossMed.doctor;

import coss.med.CossMed.address.Address;

public record DoctorDataDTO(String name, String email, String crm, Specialty specialty, Address address) {

}
