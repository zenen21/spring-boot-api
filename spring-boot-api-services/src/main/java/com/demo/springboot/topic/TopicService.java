package com.demo.springboot.topic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TopicService {

	private List<Topic> topics = new ArrayList<>(
			Arrays.asList(new Topic("Spring", "Spring Framework"), new Topic("Services", "Spring boot")));

	public List<Topic> getAllTopics() {
		return topics;
	}

	public Topic getTopic(String name) {
		return topics.stream().filter(t -> t.getName().equals(name)).findFirst().get();
	}

	public void addTopic(Topic topic) {
		topics.add(topic);
	}

	public void addTopicFromJsonFile(MultipartFile json) {
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			InputStream inputStream = json.getInputStream();

			final File tempFile = File.createTempFile("", "");
			tempFile.deleteOnExit();
			try (FileOutputStream out = new FileOutputStream(tempFile)) {
				IOUtils.copy(inputStream, out);
			}

			Topic topic = objectMapper.readValue(tempFile, Topic.class);

			System.out.println("car brand = " + topic.getName());
			System.out.println("car doors = " + topic.getDescription());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
