package coss.med.CossMed.domain.appointment.validation.cancellation;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import coss.med.CossMed.domain.appointment.AppointmentCancelDTO;
import coss.med.CossMed.domain.appointment.AppointmentRepository;
import jakarta.validation.ValidationException;

@Component("AdvanceTimeValidationCancellation")
public class AdvanceTimeValidation implements CancelAppointmentValidator {

    @Autowired
    private AppointmentRepository repository;

    @Override
    public void validate(AppointmentCancelDTO data) {
        var appointment = repository.getReferenceById(data.appointmentId());
        var advanceTime = Duration.between(LocalDateTime.now(), appointment.getDate()).toHours();

        if (advanceTime < 24) {
            throw new ValidationException("Advance time to appointment cancellation must be at least 24 hours.");
        }
    }
}