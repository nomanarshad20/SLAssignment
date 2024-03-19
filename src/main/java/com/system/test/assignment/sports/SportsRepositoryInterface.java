package com.system.test.assignment.sports;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.system.test.assignment.entities.SportsEntity;

@Repository
public interface SportsRepositoryInterface extends JpaRepository<SportsEntity, Integer> {

	@Query("SELECT s FROM SportsEntity s WHERE SIZE(s.players) >= 2")
	List<SportsEntity> findSportsWithMultiplePlayers();

	@Query("SELECT s FROM SportsEntity s WHERE SIZE(s.players) = 0")
	List<SportsEntity> findSportsWithNoPlayers();

	List<SportsEntity> findSportsByName(String name);

}
