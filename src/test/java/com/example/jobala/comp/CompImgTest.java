package com.example.jobala.comp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Base64;

@DataJpaTest
public class CompImgTest {

    @Test
    public void Base64Test() {
        String str = "userImage (1).png";
        String encodedStr = Base64.getEncoder().encodeToString(str.getBytes());

        System.out.println("encoded string: " + encodedStr);

        byte[] decodedBytes = Base64.getDecoder().decode(encodedStr);
        String decodedStr = new String(decodedBytes);

        System.out.println("decoded string: " + decodedStr);
    }

    @Test
    public void Base64Url() {
        String url = "https://localhost:8080/upload";
        String encoded = Base64.getUrlEncoder().encodeToString(url.getBytes());
        System.out.println("encoded url: " + encoded);

        byte[] decodedBytes = Base64.getUrlDecoder().decode(encoded);
        String decodedStr = new String(decodedBytes);

        System.out.println("decoded string: " + decodedStr);
    }
}
