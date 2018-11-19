package com.demo.springboot.test;

import com.demo.springboot.topic.Topic;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * Test class to validate TopicController
 */
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
                .given()
                .contentType(ContentType.JSON)
                .body(topic)
                .post("http://localhost:8080/topics").asString());
        logger.info(RestAssured.given()
                .when()
                .get("http://localhost:8080/topics").asString());

    }

    @Test
    public void createTopicFromJsonFile() {
        try {
            List<String> lines = Arrays.asList("{\r\n" + "\r\n" + "\"name\" : \"Spring boot\",\r\n" + "\r\n"
                    + "\"description\" : \"Quickstart 2\"\r\n" + "\r\n" + "}");

            Path file = Files.write(Paths.get("json.txt"), lines);
            RestAssured
                    .given()
                    //.body(file.toFile())
                    .contentType("multipart/form-data;boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW")
                    .when()
                    .multiPart("jsonTopic", file.toFile(), "text/plain")
                    .post("http://localhost:8080/topics");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void getPDFFile() {
        //TODO Create controller and service to receive PDF files
        /*Path file = null;
        File file2 = null;
        List<String> lines = Arrays.asList("{\r\n" + "\r\n" + "\"name\" : \"Spring boot\",\r\n" + "\r\n"
                + "\"description\" : \"Quickstart 2\"\r\n" + "\r\n" + "}");

        //file = Files.new(Paths.get("Resume.pdf"));
        file2 = new File(Paths.get("Resume.pdf").toString());
        RestAssured
                .given()
                //.body(file.toFile())
                .contentType("multipart/form-data;boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW")
                .when()
                .multiPart("jsonTopic", file2, "application/pdf")
                .post("http://localhost:8080/topics");*/
    }
}
