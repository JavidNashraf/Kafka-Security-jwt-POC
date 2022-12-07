package ca.on.miro.event.jwt.consumer.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import ca.on.miro.event.jwt.consumer.model.CaseData;
import ca.on.miro.event.jwt.consumer.model.CaseDocument;
import ca.on.miro.event.jwt.consumer.model.CasePayload;

@EnableBinding(Sink.class)
@RestController
public class EventConsumerProxy {

	private static List<CaseData> bookList = new ArrayList<>();
	
	@Autowired
    private RestTemplate restTemplate;
	
	private Logger logger = LoggerFactory.getLogger(EventConsumerProxy.class);
	
	@StreamListener("input")
	public void consumerMessage(CasePayload casePayload) {
		logger.info("Consume payload : "+casePayload);
		
		//
		// Remote secure chain API calls
		//
		CaseDocument caseDocument = null; 
		try {
			String theUrl = "http://localhost:8093/cases/"+casePayload.getCasedata().getId();
			HttpHeaders headers = createHttpJwtHeaders(casePayload.getJwt());
		    HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		    
	        ResponseEntity<CaseDocument> response = restTemplate.exchange(theUrl, HttpMethod.GET, entity, CaseDocument.class);
	        caseDocument = response.getBody();
	        
	        System.out.println("Result - Chained ==>>"+ response.toString() );	
		}catch (Exception e) {
			System.out.println(e.getMessage());	
		}
		
		CaseData theCase = casePayload.getCasedata();
		if( caseDocument != null){
			theCase.setName(casePayload.getCasedata().getName()+"/"+caseDocument.getDocument());
		}
		bookList.add(theCase);
	}
	
	private HttpHeaders createHttpJwtHeaders(String jwt)
	{
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.AUTHORIZATION, jwt);
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

	    
	    return headers;
	}
	
	/* Consumer will get JSON object in the HTTPRequest (Postman) */
	@GetMapping("/consume")
	public String publishEvent(){
		return "Book payload : "+bookList;
	}


}
