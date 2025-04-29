package heuron.patientservice.presentation.dto;

import heuron.patientservice.application.dto.PatientResult;
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
    ){
        public static PatientResponseDto.createPatient from(PatientResult.patientDto dto) {
            return createPatient.builder()
                    .patientId(dto.patientId())
                    .name(dto.name())
                    .age(dto.age())
                    .gender(dto.gender())
                    .disease(dto.disease())
                    .build();
        }
    }

    @Builder
    public record getPatient(
            Long patientId,
            String name,
            Integer age,
            Gender gender,
            Boolean disease,
            String imagePath,
            String imageName,
            Boolean imageUploaded
    ){
        public static PatientResponseDto.getPatient from(PatientResult.patientDto dto) {
            return getPatient.builder()
                    .patientId(dto.patientId())
                    .name(dto.name())
                    .age(dto.age())
                    .gender(dto.gender())
                    .disease(dto.disease())
                    .imagePath(dto.imagePath())
                    .imageName(dto.imageName())
                    .imageUploaded(dto.imageUploaded())
                    .build();
        }
    }

    @Builder
    public record deletePatient(
            Long patientId,
            String name,
            Integer age,
            Gender gender,
            Boolean disease
    ){
        public static PatientResponseDto.deletePatient from(PatientResult.patientDto dto) {
            return deletePatient.builder()
                    .patientId(dto.patientId())
                    .name(dto.name())
                    .age(dto.age())
                    .gender(dto.gender())
                    .disease(dto.disease())
                    .build();
        }
    }
}
