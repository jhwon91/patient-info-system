package heuron.patientservice.application;

import heuron.patientservice.application.dto.PatientCommand;
import heuron.patientservice.application.dto.PatientResult;
import heuron.patientservice.domain.Gender;
import heuron.patientservice.domain.model.Patient;
import heuron.patientservice.domain.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class PatientServiceTest {
    @Mock
    private PatientRepository patientRepository;

    @Mock
    private ImageManager imageManager;

    @InjectMocks
    private PatientService patientService;

    private Patient patient;
    private PatientCommand.createPatient command;
    private MockMultipartFile mockFile;

    @BeforeEach
    void setUp() {
        patient = Patient.createPatient("testPatient", 10, Gender.MALE, true);
        command = new PatientCommand.createPatient("testPatient", 10, Gender.MALE, true);

        mockFile = new MockMultipartFile(
                "file", // 파일의 파라미터 이름
                "test-image.jpg", // 실제 파일 이름
                "image/jpeg", // 파일의 확장자 타입
                "test image content".getBytes()
        );
    }

    @Test
    void 환자_생성(){
        //given
        when(patientRepository.save(any(Patient.class))).thenReturn(patient);

        //when
        PatientResult.patientDto result = patientService.createPatient(command);

        //then
        assertThat(result.name()).isEqualTo("testPatient");
        assertThat(result.age()).isEqualTo(10);
        assertThat(result.imagePath()).isNull();
        assertThat(result.imageName()).isNull();
        assertThat(result.imageOriginName()).isNull();
        assertThat(result.imageUploaded()).isEqualTo(false);
    }

    @Test
    void 환자_이미지_업로드() {
        //given
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        when(imageManager.uploadImage(mockFile)).thenReturn("/images/origin.jpg");
        when(patientRepository.save(any(Patient.class))).thenReturn(patient);

        //when
        String result = patientService.uploadPatientImage(1L, mockFile);

        //then
        assertThat(result).isEqualTo("/images/origin.jpg");
    }

    @Test
    void 환자_이미지_업로드_환자없음_예외발생() {
        //given
        when(patientRepository.findById(1L)).thenReturn(Optional.empty());

        //when
        IllegalArgumentException result = assertThrows(
                IllegalArgumentException.class,
                () -> patientService.uploadPatientImage(1L, mockFile)
        );

        //then
        assertThat(result.getMessage()).isEqualTo("환자를 찾을 수 없습니다.");
    }

    @Test
    void 환자_조회() {
        //given
        String filePath = "/images/origin.jpg";
        String fileOriginName = "test-image.jpg";
        String fileName = "origin.jpg";
        patient.saveImage(filePath, fileName, fileOriginName);
        when(patientRepository.findByIdAndImageUploadedTrue(1L)).thenReturn(Optional.of(patient));

        //when
        PatientResult.patientDto result = patientService.getPatient(1L);

        //then
        assertThat(result.name()).isEqualTo("testPatient");
        assertThat(result.age()).isEqualTo(10);
        assertThat(result.imagePath()).isEqualTo(filePath);
        assertThat(result.imageName()).isEqualTo(fileName);
        assertThat(result.imageOriginName()).isEqualTo(fileOriginName);
        assertThat(result.imageUploaded()).isEqualTo(true);
    }

    @Test
    void 환자_조회_이미지없음_예외발생() {
        //given
        when(patientRepository.findByIdAndImageUploadedTrue(1L)).thenReturn(Optional.empty());

        //when
        IllegalArgumentException result = assertThrows(
                IllegalArgumentException.class,
                () -> patientService.getPatient(1L)
        );

        //then
        assertThat(result.getMessage()).isEqualTo("이미지가 업로드가 완료된 환자만 조회 가능합니다.");
    }

    @Test
    void 환자_이미지_조회() {
        //given
        String filePath = "/images/origin.jpg";
        String fileOriginName = "test-image.jpg";
        String fileName = "origin.jpg";
        patient.saveImage(filePath, fileName, fileOriginName);
        when(patientRepository.findByIdAndImageUploadedTrue(1L)).thenReturn(Optional.of(patient));
        when(imageManager.getImage(fileName, patient.getImagePath())).thenReturn("/images/"+fileName);

        //when
        String assertThat = patientService.getPatientImage(1L, fileName);

        //then
        assertThat(assertThat).isEqualTo("/images/"+fileName);
    }

    @Test
    void 환자_이미지_조회_이미지없음_예외발생() {
        //given
        when(patientRepository.findByIdAndImageUploadedTrue(1L)).thenReturn(Optional.empty());

        //when
        IllegalArgumentException result = assertThrows(
                IllegalArgumentException.class,
                () -> patientService.getPatientImage(1L, "origin.jpg")
        );

        //then
        assertThat(result.getMessage()).isEqualTo("이미지가 업로드가 완료된 환자만 조회 가능합니다.");
    }

    @Test
    void 환자_삭제() {
        //given
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));

        //when
        PatientResult.patientDto result = patientService.deletePatient(1L);

        //then
        assertThat(result.name()).isEqualTo("testPatient");
        assertThat(result.age()).isEqualTo(10);
        verify(patientRepository, times(1)).delete(patient);
    }

    @Test
    void 환자_삭제_환자없음_예외발생() {
        //given
        when(patientRepository.findById(1L)).thenReturn(Optional.empty());

        //when
        IllegalArgumentException result = assertThrows(
                IllegalArgumentException.class,
                () -> patientService.deletePatient(1L)
        );

        //then
        assertThat(result.getMessage()).isEqualTo("환자를 찾을 수 없습니다.");
    }
}