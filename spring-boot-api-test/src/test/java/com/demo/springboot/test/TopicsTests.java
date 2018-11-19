package com.demo.springboot.test;

import com.demo.springboot.topic.Topic;
import com.jayway.restassured.RestAssured;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class TopicsTests {

    Logger logger = Logger.getLogger(TopicsTests.class.getName());

    @Test
    public void getAllTopics() {
        logger.info(RestAssured.given()
                .when()
                .get("http://localhost:8080/topics").asString());
    }

    @Test
    public void addTopic() {
        Topic topic = new Topic("POST", "Test");
        logger.info(RestAssured
                .post("http://localhost:8080/topics", topic).asString());
    }

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
