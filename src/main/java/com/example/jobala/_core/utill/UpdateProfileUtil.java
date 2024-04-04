package com.example.jobala._core.utill;

import com.example.jobala._core.errors.apiException.ApiException400;
import com.example.jobala._user.UserRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

@Component
public class UpdateProfileUtil {

    public String fileUpdate(String imgFilename, String imgTitle) throws IOException {
        //베이스 64로 들어오는 문자열을 바이트로 디코딩하기
        byte[] decodedBytes = Base64.getDecoder().decode(imgFilename.getBytes());
        String imageUUID = UUID.randomUUID() + "_" + imgTitle;

        // 이미지 파일의 저장 경로 설정
        Path imgPath = Paths.get("./image/" + imageUUID);
        Files.write(imgPath, decodedBytes);
        String webImgPath = imgPath.toString().replace("\\", "/");
        return webImgPath.substring(webImgPath.lastIndexOf("/") + 1);
    }
}
