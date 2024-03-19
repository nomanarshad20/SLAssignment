package com.system.test.assignment.dto;

import java.io.Serializable;
import java.util.Set;

public class SportsDTO implements Serializable {

	int id;

	String name;

	Set<PlayerDTO> playersList;

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

	public Set<PlayerDTO> getPlayersList() {
		return playersList;
	}

	public void setPlayersList(Set<PlayerDTO> playersList) {
		this.playersList = playersList;
	}

	@Override
	public String toString() {
		return "SportsDTO [id=" + id + ", name=" + name + ", playersList=" + playersList + "]";
	}

	
	public SportsDTO() {}
}
