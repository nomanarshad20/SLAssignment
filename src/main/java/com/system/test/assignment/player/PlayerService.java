package com.system.test.assignment.player;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.system.test.assignment.dto.PlayerDTO;

public interface PlayerService {

	PlayerDTO createPlayer(PlayerDTO playerDTO);

	List<PlayerDTO> getAllPlayers();

	List<PlayerDTO> getPlayersByProperties(String gender, int level, int age);

	Page<PlayerDTO> getFilteredPlayersList(Pageable pageable );

	List<PlayerDTO> findPlayersWithNoSports();

	Page<PlayerDTO> findPlayersWithSportsCategory(String sportsname, Pageable pageable);

}
