package heuron.patientservice.application.dto;

import heuron.patientservice.domain.Gender;
import heuron.patientservice.domain.model.Patient;
import lombok.Builder;

import java.time.LocalDateTime;

public class PatientResult {
    @Builder
    public record patientDto(
            Long patientId,
            String name,
            Integer age,
            Gender gender,
            Boolean disease,
            String imagePath,
            String imageName,
            String imageOriginName,
            Boolean imageUploaded,
            LocalDateTime createdAt,
            LocalDateTime updatedAt

    ){
        public static patientDto from(Patient patient) {
            return patientDto.builder()
                    .patientId(patient.getId())
                    .name(patient.getName())
                    .age(patient.getAge())
                    .gender(patient.getGender())
                    .disease(patient.getDisease())
                    .imagePath(patient.getImagePath())
                    .imageName(patient.getImageName())
                    .imageOriginName(patient.getImageOriginName())
                    .imageUploaded(patient.getImageUploaded())
                    .build();
        }
    }
}
