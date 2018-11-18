package com.demo.springboot.topic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class TopicController {

	@Autowired
	private TopicService topicService;

	@RequestMapping("/topics")
	public List<Topic> allTopics() {
		return topicService.getAllTopics();
	}

	@RequestMapping("/topics/{name}")
	public Topic getTopic(@PathVariable String name) {
		return topicService.getTopic(name);

	}

	@RequestMapping(method = RequestMethod.POST, value = "/topics")
	public void addTopic(@RequestBody Topic topic) {
		topicService.addTopic(topic);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/topics",
			consumes = "multipart/form-data",
			produces = {"application/json", "application/xml" })
	public void addTopicFromJsonFile(@RequestBody MultipartFile jsonTopic) {
		topicService.addTopicFromJsonFile(jsonTopic);
	}

}
