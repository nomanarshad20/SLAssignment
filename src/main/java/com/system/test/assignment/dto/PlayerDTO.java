package com.system.test.assignment.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class PlayerDTO implements Serializable {

	int id;

	String name;

	int level;

	String email;

	String gender;

	int age;

	private Set<SportsDTO> sports = new HashSet<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) throws Exception {
		if (level >= 1 && level <= 10) {
			this.level = level;
		} else {
			throw new Exception("level should be between 1 to 10");
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Set<SportsDTO> getSports() {
		return sports;
	}

	public void setSports(Set<SportsDTO> sports) {
		this.sports = sports;
	}

	public PlayerDTO() {
	}

	public PlayerDTO(int id, String name, int level, String email, String gender, int age) {
		super();
		this.id = id;
		this.name = name;
		this.level = level;
		this.email = email;
		this.gender = gender;
		this.age = age;
	}

}
