package com.example.jobala.guest;

import com.example.jobala._user.User;
import com.example.jobala._user.UserResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Base64;

@DataJpaTest
public class GuestJPARepositoryTest {

    @Autowired
    private GuestService guestService;

//    @Test
//    public void profileUpdate_test() {
//    // given
//        String name = "이름";
//        String password = "비밀번호";
//        String phone = "010-1111-1111";
//        String email = "이메일@nate.com";
//        String imgTitle = "이미지 제목";
//        String imgFilename = "iVBORw0KGgoAAAANSUhEUgAAATYAAACjCAYAAAAAYDLtAAAAAXNSR0IArs4c6QAAIABJREFUeF7tXQd4VFXa/m6ZnsmkV9ITSICAICg1dEWpAtIRQUBcFXBZ7KKrFFFR5F/Xsq6gFFGKJAGkhxqQXqQlIQkJIYX0MvWW//nOzGBkEQIkAZJz92ETZ+495T3nvvn6YYBeFAGKAEWggSHANLD50OlQBCgCFAGgxEY3AUWAItDgEKDE1uC..."; // B
//        GuestRequest.GuestProfileUpdateDTO guestProfileUpdateDTO = new GuestRequest.GuestProfileUpdateDTO();
//        guestProfileUpdateDTO.setEmail(email);
//        guestProfileUpdateDTO.setName(name);
//        guestProfileUpdateDTO.setPassword(password);
//        guestProfileUpdateDTO.setPhone(phone);
//        guestProfileUpdateDTO.setImgFilename(imgFilename);
//        guestProfileUpdateDTO.setImgTitle(imgTitle);
//
//        User sessionUser = User.builder()
//                .id(1)
//                .address("서울특별시 강남구")
//                .username("cos1")
//                .email("actor1@nate.com")
//                .password("1234")
//                .name("이병헌")
//                .phone("01011112223")
//                .role(0)
//                .build();
//        int userId = 1;
//    //when
//        UserResponse.GuestProfile respDTO = guestService.guestUpdateProfile(guestProfileUpdateDTO, sessionUser);
//    //then
//        System.out.println(respDTO);
//
//    }

    @Test
    public void base64_test(){
        // given
        String testText = "Base64 Encode Decode Test";
        byte[] testToByte = testText.getBytes();

        //자바 8 기본 Base64 Encoder Decoder
        Base64.Encoder encode = Base64.getEncoder();
        Base64.Decoder decode = Base64.getDecoder();

        //Base64 인코딩
        byte[] encodeByte = encode.encode(testToByte);

        //Base64 디코딩
        byte[] decodeByte = decode.decode(encodeByte);

        System.out.println("인코딩 전: "+ testText);
        System.out.println("인코딩: "+ new String(encodeByte));
        System.out.println("디코딩: "+ new String(decodeByte));


        // when


        // then

    }
}
