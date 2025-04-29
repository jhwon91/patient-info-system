package heuron.patientservice.presentation.dto;

import heuron.patientservice.application.dto.PatientCommand;
import heuron.patientservice.domain.Gender;
import lombok.Builder;

public class PatientRequestDto {
    @Builder
    public record createPatient(
            String name,
            Integer age,
            Gender gender,
            Boolean disease
    ){
        public PatientCommand.createPatient toCommand(){
            return PatientCommand.createPatient.builder()
                    .name(name)
                    .age(age)
                    .gender(gender)
                    .disease(disease)
                    .build();
        }
    }
}
