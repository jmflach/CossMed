package coss.med.CossMed.domain.appointment.validation;

import java.time.DayOfWeek;

import org.springframework.stereotype.Component;

import coss.med.CossMed.domain.appointment.AppointmentDataDTO;
import jakarta.validation.ValidationException;

@Component
public class ClinicOpenHoursValidation implements AppointmentValidator {

    public void validate(AppointmentDataDTO data){
        var appointmentDate = data.date();
        
        var isSunday = appointmentDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var isBeforeOpen = appointmentDate.getHour() < 7;
        var isAfterClose = appointmentDate.getHour() > 18;

        if (isSunday || isBeforeOpen || isAfterClose){
            throw new ValidationException("Appointment outside clinic opening hours.");
        }
    }
}