package com.corvanta.jwt.producer.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.corvanta.jwt.producer.model.CaseData;
import com.corvanta.jwt.producer.model.CasePayload;

/**
 * Deprecated this class since it uses Apache-kafka methods not Spring Cloud Stream binder framework
 *
 */
@Deprecated
@RestController
@RequestMapping("/kafka")
public class ServerEventProducerProxy {

    @Autowired
    private KafkaTemplate<String, CasePayload> kafkaTemplate;
    private static final String TOPIC = "TestTopic";
    
    @GetMapping("/")
    public String Home() {
    	System.out.print("---Here --");
    	return "Welcome Publisher Proxy Home!";
    }
    
	/* Publisher will send String(name) in the HTTPRequest (Postman) */
    @GetMapping("/publish/{name}")
    public String post(@PathVariable("name") final String name) {

    	CaseData casedata = new CaseData(123, name);
        kafkaTemplate.send(TOPIC, new CasePayload("jwt123", casedata));
        return "Published JSON successfully";
    }
    
}