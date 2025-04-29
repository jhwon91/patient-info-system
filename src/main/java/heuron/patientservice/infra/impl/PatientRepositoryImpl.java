package heuron.patientservice.infra.impl;

import heuron.patientservice.domain.model.Patient;
import heuron.patientservice.domain.repository.PatientRepository;
import heuron.patientservice.infra.jpa.JpaPatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PatientRepositoryImpl implements PatientRepository {
    private final JpaPatientRepository jpaPatientRepository;

    @Override
    public Patient save(Patient patient) {
        return jpaPatientRepository.save(patient);
    }

    @Override
    public Optional<Patient> findByIdAndImageUploadedTrue(Long patientId) {
        return jpaPatientRepository.findByIdAndImageUploadedTrue(patientId);
    }

    @Override
    public Optional<Patient> findById(Long patientId) {
        return jpaPatientRepository.findById(patientId);
    }

    @Override
    public void delete(Patient patient) {
        jpaPatientRepository.delete(patient);
    }
}
