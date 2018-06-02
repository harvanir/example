package org.harvan.example.springboot.kafka;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class ReplyingConsumer {
	private ObjectMapper mapper = new ObjectMapper();
	@Value("${server.port}")
	private String serverPort;

	@KafkaListener(id = "sumId", topics = KafkaConstant.TOPIC, groupId = KafkaConstant.TOPIC_GROUP)
	@SendTo
	public String listen(String payload) throws IOException {
		Model request = mapper.readValue(payload, Model.class);

		int sum = request.getFirstNumber() + request.getSecondNumber();
		request.setAdditionalProperty("sum", sum);

//		System.out.println("server.port: " + serverPort);

		return mapper.writeValueAsString(request);
	}

	@PostMapping(value = "/listen2", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String listen2(@RequestBody String request) throws IOException {
		return listen(request);
	}
}