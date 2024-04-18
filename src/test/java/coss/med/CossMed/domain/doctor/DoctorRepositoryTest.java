package coss.med.CossMed.domain.doctor;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import coss.med.CossMed.domain.address.AddressRecord;
import coss.med.CossMed.domain.appointment.Appointment;
import coss.med.CossMed.domain.patient.Patient;
import coss.med.CossMed.domain.patient.PatientDataDTO;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Should return null when the only doctor is not available at the given date.")
    void testChooseRandomAvailableDoctor_Scenario1() {
        // Given
        var nextMonday10AM = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

        var doctor = registerDoctor("Doctor", "doctor@email.com", "123456", Specialty.CARDIOLOGY);
        var patient = registerPatient("Patient", "patient@email.com", "99999999999");
        makeAppointment(doctor, patient, nextMonday10AM);

        // When
        var availableDoctor = doctorRepository.chooseRandomAvailableDoctor(Specialty.CARDIOLOGY, nextMonday10AM);
        
        // Assert
        Assertions.assertThat(availableDoctor).isNull();
    }

    @Test
    @DisplayName("Should return doctor when he is available at the given date.")
    void testChooseRandomAvailableDoctor_Scenario2() {
        var nextMonday10AM = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

        var doctor = registerDoctor("Doctor", "doctor@email.com", "123456", Specialty.CARDIOLOGY);

        var availableDoctor = doctorRepository.chooseRandomAvailableDoctor(Specialty.CARDIOLOGY, nextMonday10AM);
        Assertions.assertThat(availableDoctor).isEqualTo(doctor);
    }

    private void makeAppointment(Doctor doctor, Patient patient, LocalDateTime date) {
        em.persist(new Appointment(null, doctor, patient, date, null));
    }

    private Doctor registerDoctor(String name, String email, String crm, Specialty specialty) {
        var doctor = new Doctor(makeDoctor(name, email, crm, specialty));
        em.persist(doctor);
        return doctor;
    }

    private Patient registerPatient(String name, String email, String cpf) {
        var patient = new Patient(makePatient(name, email, cpf));
        em.persist(patient);
        return patient;
    }

    private DoctorDataDTO makeDoctor(String name, String email, String crm, Specialty specialty) {
        return new DoctorDataDTO(
                name,
                email,
                "46999999999",
                crm,
                specialty,
                makeAddress());
    }

    private PatientDataDTO makePatient(String name, String email, String cpf) {
        return new PatientDataDTO(
                name,
                email,
                "46999999999",
                cpf,
                makeAddress());
    }

    private AddressRecord makeAddress() {
        return new AddressRecord(
                "foo",
                "bar",
                "00000000",
                "Marmeleiro",
                "PR",
                null,
                null);
    }
}