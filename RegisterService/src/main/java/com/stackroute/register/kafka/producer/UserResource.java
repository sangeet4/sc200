package com.stackroute.register.kafka.producer;

import com.stackroute.register.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserResource {

    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;
    private Environment env;
    String topic_name = env.getProperty("kafka-producer.topic");
      public void putIntoTopic(User user){
          kafkaTemplate.send(topic_name,user);

      }
}
