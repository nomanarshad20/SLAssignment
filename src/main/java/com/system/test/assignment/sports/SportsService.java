package com.system.test.assignment.sports;

import java.util.List;

import com.system.test.assignment.dto.SportsDTO;

public interface SportsService {

	SportsDTO createSports(SportsDTO sportsDTO);

	List<SportsDTO> getAllSports();

	List<SportsDTO> findSportsWithMultiplePlayers();

	List<SportsDTO> findSportsWithNoPlayers();

	List<SportsDTO> findSportsByName(String name);

	void deleteSportAndAssociatedPlayers(int sportId);

	SportsDTO updateSportAndPlayers(SportsDTO sportsDTO) throws Exception;

}
