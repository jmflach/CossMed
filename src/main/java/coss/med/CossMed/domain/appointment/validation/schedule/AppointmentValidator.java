package coss.med.CossMed.domain.appointment.validation.schedule;

import coss.med.CossMed.domain.appointment.AppointmentDataDTO;

public interface AppointmentValidator {

    void validate(AppointmentDataDTO data);
}