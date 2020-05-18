package com.springboot.withdatabase.example.topic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {
	
	@Autowired
	private TopicRepository topicRepo;
	
	public List<Topic> getAllTopics() {
		//Here we get the data as iterable from find all, then we iterate through each at put in an array
		List<Topic> topics = new ArrayList<>();
		//topics::add is method reference
		topicRepo.findAll().forEach(topics::add);
		return topics;
	}
	
	public Optional<Topic> getTopic(String id) {
		return topicRepo.findById(id);
	}
	
	public void addTopic(Topic topic) {
		topicRepo.save(topic);
	}
	
	public void updateTopic(String id, Topic topic) {
		topicRepo.save(topic);
	}
	
	public void deleteTopic(String id) {
		topicRepo.deleteById(id);
	}
	
}
