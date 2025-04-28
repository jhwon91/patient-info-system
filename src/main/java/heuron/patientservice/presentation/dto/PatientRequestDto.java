package heuron.patientservice.presentation.dto;

import heuron.patientservice.domain.Gender;
import lombok.Builder;

public class PatientRequestDto {
    @Builder
    public record createPatient(
            String name,
            Integer age,
            Gender gender,
            Boolean disease
    ){}
}
