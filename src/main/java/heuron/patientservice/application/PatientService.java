package heuron.patientservice.application;

import heuron.patientservice.application.dto.PatientCommand;
import heuron.patientservice.application.dto.PatientResult;
import heuron.patientservice.domain.model.Patient;
import heuron.patientservice.domain.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
    private static final String UPLOAD_DIR = "images/";

    @Transactional
    public PatientResult.patientDto createPatient(PatientCommand.createPatient command){

        Patient newPatient =patientRepository.save(
                Patient.createPatient(command.name(), command.age(), command.gender(), command.disease())
        );

        return PatientResult.patientDto.from(newPatient);
    }

    @Transactional
    public String uploadPatientImage(Long patientId, MultipartFile file) throws IOException {
        if (!file.getContentType().equals("image/png") && !file.getContentType().equals("image/jpeg")) {
            throw new IllegalArgumentException("PNG 또는 JPG 파일만 업로드 가능합니다.");
        }

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("환자를 찾을 수 없습니다."));

        String fileOriginName = file.getOriginalFilename();

        String fileExtension = "";
        if (fileOriginName.contains(".")) {
            fileExtension = fileOriginName.substring(fileOriginName.lastIndexOf("."));
        }

        String fileName = UUID.randomUUID() + fileExtension;
//        String fileName = "1" + fileExtension;
        Path filePath = Paths.get(UPLOAD_DIR, fileName);
        file.transferTo(filePath);

        patient.saveImage(filePath.toString(), fileName, fileOriginName);
        patientRepository.save(patient);
        return filePath.toString();
    }

    @Transactional(readOnly = true)
    public PatientResult.patientDto getPatient(Long patientId) {
        Patient patient = patientRepository.findByIdAndImageUploadedTrue(patientId)
                .orElseThrow(() -> new IllegalArgumentException("이미지가 업로드가 완료된 환자만 조회 가능합니다."));

        return PatientResult.patientDto.from(patient);
    }

    @Transactional(readOnly = true)
    public String getPatientImage(Long patientId, String fileName){
        Patient patient = patientRepository.findByIdAndImageUploadedTrue(patientId)
                .orElseThrow(() -> new IllegalArgumentException("이미지 업로드가 완료된 환자만 조회 가능합니다."));

        String storedFileName = patient.getImagePath().substring(patient.getImagePath().lastIndexOf("/") + 1);
        if (!storedFileName.equals(fileName)) {
            throw new IllegalArgumentException("잘못된 이미지 파일 이름입니다.");
        }

        Path filePath = Paths.get(UPLOAD_DIR, fileName);
        if (!Files.exists(filePath)) {
            throw new IllegalArgumentException("이미지 파일을 찾을 수 없습니다.");
        }

        return "/images/" + fileName;
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
