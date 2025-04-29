package heuron.patientservice.presentation;

import heuron.patientservice.application.PatientService;
import heuron.patientservice.global.ApiResponse;
import heuron.patientservice.presentation.dto.PatientRequestDto;
import heuron.patientservice.presentation.dto.PatientResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;

    // 환자 기본 정보 저장
    @PostMapping
    public ApiResponse<PatientResponseDto.createPatient> createPatient(
            @RequestBody PatientRequestDto.createPatient request
    ){
        PatientResponseDto.createPatient createPatientResponse = PatientResponseDto.createPatient.from(patientService.createPatient(request.toCommand()));
        ApiResponse<PatientResponseDto.createPatient> response = new ApiResponse<>(200, "환자 기본 정보가 저장되었습니다.", createPatientResponse);
        return response;
    }

    // 환자 이미지 저장
    @PostMapping("/{patientId}/image")
    public ApiResponse<String> uploadPatientImage(
            @PathVariable Long patientId,
            @RequestParam MultipartFile file
    ) {
        String imageUrl = patientService.uploadPatientImage(patientId, file);
        return new ApiResponse<>(200, "이미지 업로드가 완료되었습니다.", imageUrl);
    }

    // 환자 기본 정보 조회
    @GetMapping("/{patientId}")
    public ApiResponse<PatientResponseDto.getPatient> getPatient(@PathVariable Long patientId) {
        PatientResponseDto.getPatient getPatientResponse = PatientResponseDto.getPatient.from(patientService.getPatient(patientId));
        ApiResponse<PatientResponseDto.getPatient> response = new ApiResponse<>(200, "환자 기본 정보가 조회되었습니다.", getPatientResponse);
        return response;
    }

    // 환자 이미지 조회
    @GetMapping("/{patientId}/images/{fileName}")
    public ApiResponse<String> getPatientImage(
            @PathVariable Long patientId,
            @PathVariable String fileName
    )  {
        String imageUrl = patientService.getPatientImage(patientId, fileName);
        return new ApiResponse<>(200, "환자 이미지가 조회되었습니다.", "http://localhost:8080"+imageUrl);
    }

    // 환자 데이터 삭제
    @DeleteMapping("/{patientId}")
    public ApiResponse<PatientResponseDto.deletePatient> deletePatient(@PathVariable Long patientId) {
        PatientResponseDto.deletePatient deletePatientResponse = PatientResponseDto.deletePatient.from(patientService.deletePatient(patientId));
        ApiResponse<PatientResponseDto.deletePatient> response = new ApiResponse<>(200, "환자 데이터가 삭제되었습니다.", deletePatientResponse);
        return response;
    }
}
