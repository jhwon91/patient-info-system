package heuron.patientservice.domain.repository;

import heuron.patientservice.domain.model.Patient;

import java.util.Optional;

public interface PatientRepository {
    Patient save(Patient patient);
    Optional<Patient> findByIdAndImageUploadedTrue(Long patientId);
    Optional<Patient> findById(Long patientId);
    void delete(Patient patient);
}
