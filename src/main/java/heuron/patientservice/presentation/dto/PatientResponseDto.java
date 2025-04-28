package heuron.patientservice.presentation.dto;

import heuron.patientservice.domain.Gender;
import lombok.Builder;

public class PatientResponseDto {

    @Builder
    public record createPatient(
            Long patientId,
            String name,
            Integer age,
            Gender gender,
            Boolean disease
    ){}

    @Builder
    public record getPatient(
            Long patientId,
            String name,
            Integer age,
            Gender gender,
            Boolean disease,
            String imageUrl
    ){}
}
