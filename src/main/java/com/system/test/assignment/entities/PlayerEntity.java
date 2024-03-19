package com.system.test.assignment.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "player")
public class PlayerEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	String name;

	@Column(nullable = false)
	int level;

	@Column(nullable = false)
	String email;

	@Column(nullable = false)
	String gender;

	@Column(nullable = false)
	int age;
	
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "player_sports", joinColumns = @JoinColumn(name = "player_id"), inverseJoinColumns = @JoinColumn(name = "sport_id"))
	private Set<SportsEntity> sports = new HashSet<>();

	
	
	public PlayerEntity() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<SportsEntity> getSports() {
		return sports;
	}

	public void setSports(Set<SportsEntity> sports) {
		this.sports = sports;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
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

	@Override
	public String toString() {
		return "PlayerEntity [id=" + id + ", name=" + name + ", level=" + level + ", email=" + email + ", gender="
				+ gender + ", age=" + age + ", sports=" + sports + "]";
	}

}
