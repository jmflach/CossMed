package coss.med.CossMed.domain.appointment.validation.cancellation;

import coss.med.CossMed.domain.appointment.AppointmentCancelDTO;

public interface CancelAppointmentValidator {

    void validate(AppointmentCancelDTO data);
}