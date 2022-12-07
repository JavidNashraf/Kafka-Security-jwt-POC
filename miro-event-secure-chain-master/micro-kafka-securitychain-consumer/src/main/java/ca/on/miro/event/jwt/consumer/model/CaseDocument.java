package ca.on.miro.event.jwt.consumer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CaseDocument {
	
	@JsonProperty private int id;
	@JsonProperty private String document;
	

}
