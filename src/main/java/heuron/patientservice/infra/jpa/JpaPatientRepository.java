package heuron.patientservice.infra.jpa;

import heuron.patientservice.domain.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaPatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByIdAndImageUploadedTrue(Long patientId);
}
