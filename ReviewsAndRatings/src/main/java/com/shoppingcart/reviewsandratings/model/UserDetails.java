package com.shoppingcart.reviewsandratings.model;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDetails {
	@JsonProperty("firstName") private String firstName;
	@JsonProperty("lastName") private String lastName;
	@JsonCreator
	public UserDetails(@JsonProperty("firstName") String firstName, @JsonProperty("lastName") String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public @JsonProperty("firstName") String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public @JsonProperty("lastName") String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
}
