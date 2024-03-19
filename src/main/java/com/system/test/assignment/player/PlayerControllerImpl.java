package com.system.test.assignment.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.system.test.assignment.common.FiltersDTO;
import com.system.test.assignment.common.ResponseDTO;

@RestController
@RequestMapping("/api/player")
public class PlayerControllerImpl {

	@Autowired
	PlayerService playerService;

	@RequestMapping(value = "/all/list", method = RequestMethod.GET)
	public ResponseEntity<Object> getAllPlayers() {

		try {
			return ResponseEntity.ok()
					.body(ResponseDTO.success(playerService.getAllPlayers(), "sports get all successfuly"));
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@RequestMapping(value = "/gender/{gender}/level/{level}/age/{age}", method = RequestMethod.GET)
	public ResponseEntity<Object> getPlayersByProperties(@PathVariable String gender, @PathVariable int level,
			@PathVariable int age) {
		try {
			return ResponseEntity.ok().body(ResponseDTO.success(
					playerService.getPlayersByProperties(gender, level, age), "players Filtered  successfuly"));
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<Object> createSports(Pageable pageable) {
		try {
			return ResponseEntity.ok().body(ResponseDTO.success(playerService.getFilteredPlayersList(pageable),
					"players Filtered successfuly"));
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@RequestMapping(value = "/list/catagory/name/{sportsname}", method = RequestMethod.GET)
	public ResponseEntity<Object> findPlayersWithSportsCategory(@PathVariable String sportsname, Pageable pageable) {
		try {
			return ResponseEntity.ok().body(ResponseDTO.success(
					playerService.findPlayersWithSportsCategory(sportsname, pageable), "players Filtered successfuly"));
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@RequestMapping(value = "/list/nosport", method = RequestMethod.GET)
	public ResponseEntity<Object> findPlayersWithNoSports() {
		try {
			return ResponseEntity.ok()
					.body(ResponseDTO.success(playerService.findPlayersWithNoSports(), "players Filtered successfuly"));
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	
	
	
}
