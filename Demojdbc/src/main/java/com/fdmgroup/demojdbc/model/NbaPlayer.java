package com.fdmgroup.demojdbc.model;

public class NbaPlayer {
	private int Id;
	private String FirstName;
	private String LastName;
	private int CareerPoints;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public int getCareerPoints() {
		return CareerPoints;
	}
	public void setCareerPoints(int carrerPoints) {
		this.CareerPoints = carrerPoints;
	}

	
}
