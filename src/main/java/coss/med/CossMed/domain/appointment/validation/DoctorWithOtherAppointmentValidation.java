package coss.med.CossMed.domain.appointment.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import coss.med.CossMed.domain.appointment.AppointmentDataDTO;
import coss.med.CossMed.domain.appointment.AppointmentRepository;
import jakarta.validation.ValidationException;

@Component
public class DoctorWithOtherAppointmentValidation implements AppointmentValidator {

    @Autowired
    private AppointmentRepository appointmentRepository;
    
    public void validate(AppointmentDataDTO data) {

        System.out.println("Checking doctor " +  data.doctorId() + " date " + data.date());
        var isDoctorBusy = appointmentRepository.existsByDoctorIdAndDate(data.doctorId(), data.date());
        if(isDoctorBusy) {
            throw new ValidationException("The doctor must not have other appointment at the same time.");
        }
    }
}