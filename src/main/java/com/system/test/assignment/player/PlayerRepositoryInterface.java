package com.system.test.assignment.player;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.system.test.assignment.entities.PlayerEntity;

public interface PlayerRepositoryInterface extends JpaRepository<PlayerEntity, Integer> {

//	SELECT * FROM Player WHERE gender = 'male' AND level = 3 AND age = 20 
	public List<PlayerEntity> getPlayerByGenderAndLevelAndAge(String gender, int level, int age);

	@Query("SELECT p FROM PlayerEntity p WHERE p.sports IS EMPTY")
	List<PlayerEntity> findPlayersWithNoSports();

	@Query("SELECT p FROM PlayerEntity p JOIN p.sports s WHERE s.name = :sportsname")
	Page<PlayerEntity> findBySportsCategory(@Param("sportsname") String sportsname, Pageable pageable);
}
