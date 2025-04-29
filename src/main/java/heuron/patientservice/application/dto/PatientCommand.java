package heuron.patientservice.application.dto;

import heuron.patientservice.domain.Gender;
import lombok.Builder;

import java.util.List;

public class PatientCommand {
    @Builder
    public record createPatient(
            String name,
            Integer age,
            Gender gender,
            Boolean disease
    ){ }
}
