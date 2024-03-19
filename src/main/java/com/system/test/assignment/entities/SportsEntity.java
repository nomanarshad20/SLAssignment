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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "sports")
public class SportsEntity implements Serializable {

	private static final long serialVersionUID = -3686134875579958521L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	String name;

	@ManyToMany(mappedBy = "sports")
	Set<PlayerEntity> players;

	public SportsEntity() {
	}

	public Set<PlayerEntity> getPlayers() {
		return players;
	}

	public void setPlayers(Set<PlayerEntity> players) {
		this.players = players;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
