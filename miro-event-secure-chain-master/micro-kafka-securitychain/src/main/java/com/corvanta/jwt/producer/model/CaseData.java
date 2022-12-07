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
public class CaseData {

	@JsonProperty private int id;
	@JsonProperty private String name;

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + "]";
	}

}
