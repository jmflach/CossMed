package coss.med.CossMed.domain.appointment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coss.med.CossMed.domain.appointment.validation.AppointmentValidator;
import coss.med.CossMed.domain.doctor.Doctor;
import coss.med.CossMed.domain.doctor.DoctorRepository;
import coss.med.CossMed.domain.patient.PatientRepository;
import jakarta.validation.ValidationException;

@Service
public class AppointmentScheduler {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private List<AppointmentValidator> validators;

	public AppointmentDetailsDTO schedule(AppointmentDataDTO data) {

		if (!patientRepository.existsById(data.patientId())) {
			throw new ValidationException("The patient id does not exist.");
		}

		if (data.doctorId() != null && !doctorRepository.existsById(data.doctorId())) {
			throw new ValidationException("The doctor id does not exist.");
		}

		validators.forEach(v -> v.validate(data));

		var doctor = chooseDoctor(data);

		if (doctor == null) {
			throw new ValidationException("There is no doctor available for this date.");
		}

		var patient = patientRepository.getReferenceById(data.patientId());
		var appointment = new Appointment(null, doctor, patient, data.date(), null);

		appointmentRepository.save(appointment);

		return new AppointmentDetailsDTO(appointment);
	}

	private Doctor chooseDoctor(AppointmentDataDTO data) {
		if (data.doctorId() != null) {
			return doctorRepository.getReferenceById(data.doctorId());
		}

		if (data.specialty() == null) {
			throw new ValidationException("The specialty can not be null if doctor id is null.");
		}
		
		return doctorRepository.chooseRandomAvailableDoctor(data.specialty(), data.date());
	}
	
	public void cancel(AppointmentCancelDTO data) {
	    if (!appointmentRepository.existsById(data.appointmentId())) {
	        throw new ValidationException("Appointment id does not exist.");
	    }

	    var appointment = appointmentRepository.getReferenceById(data.appointmentId());
	    appointment.cancel(data.reason());
	}

}
