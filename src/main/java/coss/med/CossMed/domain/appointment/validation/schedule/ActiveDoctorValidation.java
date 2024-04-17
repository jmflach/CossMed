package coss.med.CossMed.domain.appointment.validation.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import coss.med.CossMed.domain.appointment.AppointmentDataDTO;
import coss.med.CossMed.domain.doctor.DoctorRepository;
import jakarta.validation.ValidationException;

@Component
public class ActiveDoctorValidation implements AppointmentValidator {

    @Autowired
    private DoctorRepository doctorRepository;
    
    public void validate(AppointmentDataDTO data) {

        if(data.doctorId() == null){
            return;
        }

        var isDoctorActive = doctorRepository.findActiveById(data.doctorId());

        if(!isDoctorActive) {
            throw new ValidationException("The appointment doctor must not be innactive.");
        }
    }
}