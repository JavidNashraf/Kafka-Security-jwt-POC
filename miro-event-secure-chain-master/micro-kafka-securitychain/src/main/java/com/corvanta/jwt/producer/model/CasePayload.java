package com.corvanta.jwt.producer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CasePayload {
	
	@JsonProperty private String  jwt;
	@JsonProperty private CaseData casedata; 
	
	@Override
	public String toString() {
		return "Book [id=" + casedata.getId() + ", name=" + casedata.getName() + ", jwt=" + jwt + "]";
	}

}
