package heuron.patientservice.application;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class ImageManager {

    private static final String UPLOAD_DIR = "images/";

    @PostConstruct
    public void init() {
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            try {
                Files.createDirectories(uploadPath);
            } catch (IOException e) {
                throw new RuntimeException("업로드 디렉토리 생성 실패", e);
            }
        }
    }

    public String uploadImage(MultipartFile file) {
        if (!file.getContentType().equals("image/png") && !file.getContentType().equals("image/jpeg")) {
            throw new IllegalArgumentException("PNG 또는 JPG 파일만 업로드 가능합니다.");
        }

        String fileOriginName = file.getOriginalFilename();
        String fileExtension = "";
        if (fileOriginName != null && fileOriginName.contains(".")) {
            fileExtension = fileOriginName.substring(fileOriginName.lastIndexOf("."));
        }

        String fileName = UUID.randomUUID() + fileExtension;
        Path filePath = Paths.get(UPLOAD_DIR, fileName);
        try {
            file.transferTo(filePath);
        } catch (IOException e) {
            throw new IllegalArgumentException("이미지 파일 저장 실패");
        }


        return filePath.toString();
    }

    public String getImage(String fileName, String storedFilePath) {
        String storedFileName = fileNameByFilePath(storedFilePath);
        if (!storedFileName.equals(fileName)) {
            throw new IllegalArgumentException("잘못된 이미지 파일 이름입니다.");
        }

        Path filePath = Paths.get(UPLOAD_DIR, fileName);
        if (!Files.exists(filePath)) {
            throw new IllegalArgumentException("이미지 파일을 찾을 수 없습니다.");
        }

        return "/images/" + fileName;
    }

    public void deleteImage(String storedFilePath){
        Path filePath = Paths.get(storedFilePath);
        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new IllegalArgumentException("이미지 파일 삭제 실패");
        }
    }

    public String fileNameByFilePath(String filePath){
        return filePath.substring(filePath.lastIndexOf("/") + 1);
    }
}
