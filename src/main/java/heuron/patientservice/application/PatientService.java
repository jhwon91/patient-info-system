package heuron.patientservice.application;

import heuron.patientservice.application.dto.PatientCommand;
import heuron.patientservice.application.dto.PatientResult;
import heuron.patientservice.domain.model.Patient;
import heuron.patientservice.domain.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;

    @Transactional
    public PatientResult.patientDto createPatient(PatientCommand.createPatient command){

        Patient newPatient =patientRepository.save(
                Patient.createPatient(command.name(), command.age(), command.gender(), command.disease())
        );

        return PatientResult.patientDto.from(newPatient);
    }

    @Transactional(readOnly = true)
    public PatientResult.patientDto getPatient(Long patientId) {
        Patient patient = patientRepository.findByIdAndImageUploadedTrue(patientId)
                .orElseThrow(() -> new IllegalArgumentException("이미지가 업로드가 완료된 환자만 조회 가능합니다."));

        return PatientResult.patientDto.from(patient);
    }

    @Transactional
    public PatientResult.patientDto deletePatient(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("환자를 찾을 수 없습니다."));

        //TODO: 이미지 삭제

        patientRepository.delete(patient);

        return PatientResult.patientDto.from(patient);
    }


}
