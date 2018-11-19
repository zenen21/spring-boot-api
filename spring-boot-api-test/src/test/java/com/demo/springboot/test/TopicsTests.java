package com.demo.springboot.test;

import com.jayway.restassured.RestAssured;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class TopicsTests {

    @Test
    public void createTopicFromJsonFile() {
        List<String> lines = Arrays.asList("{\r\n" + "\r\n" + "\"name\" : \"Spring boot\",\r\n" + "\r\n"
                + "\"description\" : \"Quickstart 2\"\r\n" + "\r\n" + "}");

        Path file;
        try {
            file = Files.write(Paths.get("json.txt"), lines);
            RestAssured
                    .given()
                    .body(file.toFile())
                    .contentType("multipart/form-data;boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW")
                    .when()
                    .post("http://localhost:8080/topics");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
