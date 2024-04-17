package coss.med.CossMed.domain.appointment.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import coss.med.CossMed.domain.appointment.AppointmentDataDTO;
import coss.med.CossMed.domain.patient.PatientRepository;
import jakarta.validation.ValidationException;

@Component
public class ActivePatientValidation implements AppointmentValidator {
   
    @Autowired
    private PatientRepository patientRepository;
    
    public void validate(AppointmentDataDTO data) {

        System.out.println("---------------------------------- indo");
        var isPatientActive = patientRepository.findActiveById(data.patientId());
        System.out.println("---------------------------------- vindo " + isPatientActive);

        if(!isPatientActive) {
            throw new ValidationException("The patient must not be innactive.");
        }
    }
}