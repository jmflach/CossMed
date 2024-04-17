package coss.med.CossMed.domain.appointment.validation;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import coss.med.CossMed.domain.appointment.AppointmentDataDTO;
import jakarta.validation.ValidationException;

@Component
public class AdvanceTimeValidation implements AppointmentValidator {

    public void validate(AppointmentDataDTO data) {
        var appointmentDate = data.date();
        var advanceTime = Duration.between(LocalDateTime.now(), appointmentDate).toMinutes();

        if (advanceTime < 30) {
            throw new ValidationException("Advance time to appointment must be at least 30 minutes.");
        }
    }
}