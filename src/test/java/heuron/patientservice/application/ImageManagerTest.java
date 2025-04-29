package heuron.patientservice.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
class ImageManagerTest {

    @InjectMocks
    private ImageManager imageManager;

    private MockMultipartFile mockFile;

    @BeforeEach
    void setUp() {
        mockFile = new MockMultipartFile(
                "file",
                "test-image.jpg",
                "image/jpeg",
                "test image content".getBytes()
        );
    }

    @Test
    void 이미지_업로드() {

        //when
        String result = imageManager.uploadImage(mockFile);

        //then
        assertThat(result).contains("images/");
    }

    @Test
    void 이미지_업로드_잘못된_파일_형식_예외발생() {
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "test.txt",
                "text/plain",
                "test content".getBytes()
        );

        IllegalArgumentException result = assertThrows(
                IllegalArgumentException.class,
                () -> imageManager.uploadImage(file)
        );

        assertThat(result.getMessage()).isEqualTo("PNG 또는 JPG 파일만 업로드 가능합니다.");
    }

    @Test
    void 이미지_조회_잘못된_파일_이름_예외발생() {
        String filePath = "images/test-image.jpg";

        IllegalArgumentException result = assertThrows(
                IllegalArgumentException.class,
                () -> imageManager.getImage("wrong-image.jpg", filePath)
        );

        assertThat(result.getMessage()).isEqualTo("잘못된 이미지 파일 이름입니다.");
    }

    @Test
    void 파일_이름_추출() {
        String filePath = "images/test-image.jpg";
        String fileName = imageManager.fileNameByFilePath(filePath);
        assertThat(fileName).isEqualTo("test-image.jpg");
    }
}