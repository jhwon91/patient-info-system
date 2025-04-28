package heuron.patientservice.presentation;

import heuron.patientservice.application.PatientService;
import heuron.patientservice.domain.Gender;
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
        PatientResponseDto.createPatient response = PatientResponseDto.createPatient.builder()
                .patientId(1L)
                .name("testName")
                .age(10)
                .gender(Gender.MALE)
                .disease(true)
                .build();
        return new ApiResponse<>(200, "환자 기본 정보가 저장되었습니다.", response);
    }

    // 환자 이미지 업로드
    @PostMapping("/{patientId}/image")
    public ApiResponse<String> uploadPatientImage(
            @PathVariable Long patientId,
            @RequestParam("file") MultipartFile file
    ){
        String imageUrl = "...";
        return new ApiResponse<>(200, "이미지 업로드가 완료되었습니다.", imageUrl);
    }

    // 환자 기본 정보 조회
    @GetMapping("/{patientId}")
    public ApiResponse<PatientResponseDto.getPatient> getPatient(@PathVariable Long patientId) {
        PatientResponseDto.getPatient response = PatientResponseDto.getPatient.builder()
                .patientId(1L)
                .name("testName")
                .age(10)
                .gender(Gender.MALE)
                .disease(true)
                .imageUrl("...")
                .build();
        return new ApiResponse<>(200, "환자 기본 정보가 조회되었습니다.", response);
    }

    // 환자 이미지 조회
    @GetMapping("/{patientId}/images/{fileName}")
    public ApiResponse<String> getPatientImage(
            @PathVariable Long patientId,
            @PathVariable String fileName) {
        String imageUrl = "...";
        return new ApiResponse<>(200, "환자 이미지가 조회되었습니다.", imageUrl);
    }

    // 5. 환자 데이터 삭제
    @DeleteMapping("/{patientId}")
    public ApiResponse<Void> deletePatient(@PathVariable Long patientId) {
        return new ApiResponse<>(200, "환자 데이터가 삭제되었습니다.", null);
    }

}
