package coss.med.CossMed.domain.appointment.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import coss.med.CossMed.domain.appointment.AppointmentDataDTO;
import coss.med.CossMed.domain.appointment.AppointmentRepository;
import jakarta.validation.ValidationException;

@Component
public class PatientWithOtherAppointmentValidation implements AppointmentValidator {

    @Autowired
    private AppointmentRepository appointmentRepository;
    
    public void validate(AppointmentDataDTO data) {

        var openHours = data.date().withHour(7);
        var closeHours = data.date().withHour(18);

        var havePatientAnotherAppointment = appointmentRepository.existsByPatientIdAndDateBetween(data.patientId(), openHours, closeHours);

        if(havePatientAnotherAppointment) {
            throw new ValidationException("The patient must not have other appointment at the same day.");
        }
    }
}