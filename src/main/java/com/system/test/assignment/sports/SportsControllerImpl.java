package com.system.test.assignment.sports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.system.test.assignment.common.ResponseDTO;
import com.system.test.assignment.dto.SportsDTO;

@RestController
@RequestMapping("/api/sports")
public class SportsControllerImpl {

	private SportsService sportsService;

	@Autowired
	public SportsControllerImpl(SportsService sportsService) {
		this.sportsService = sportsService;
	}

	@RequestMapping(value = "/all/list", method = RequestMethod.GET)
	public ResponseEntity<Object> getAllSports() {
		try {
			return ResponseEntity.ok()
					.body(ResponseDTO.success(sportsService.getAllSports(), "sports get all successfuly"));
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<Object> createSports(@RequestBody String json) {
		try {
			return ResponseEntity.ok().body(ResponseDTO.success(null, "sports Created successfuly"));
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@RequestMapping(value = "/multiplayer/list", method = RequestMethod.GET)
	public ResponseEntity<Object> findSportsWithMultiplePlayers() {
		try {
			return ResponseEntity.ok().body(ResponseDTO.success(sportsService.findSportsWithMultiplePlayers(),
					"sports  multiplayer get successfuly"));
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@RequestMapping(value = "/noplayer/list", method = RequestMethod.GET)
	public ResponseEntity<Object> findSportsWithNoPlayers() {
		try {
			return ResponseEntity.ok().body(ResponseDTO.success(sportsService.findSportsWithNoPlayers(),
					"sports  multiplayer get successfuly"));
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@RequestMapping(value = "/list/{name}", method = RequestMethod.GET)
	public ResponseEntity<Object> findSportsByName(@PathVariable String name) {
		try {
			return ResponseEntity.ok()
					.body(ResponseDTO.success(sportsService.findSportsByName(name), "sports By Name successfuly"));
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@RequestMapping(value = "/{sportId}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteSportAndAssociatedPlayers(@PathVariable int sportId) {
		try {
			sportsService.deleteSportAndAssociatedPlayers(sportId);
			return ResponseEntity.ok().body(ResponseDTO.success(null, "sports deleted successfuly"));
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<Object> updateSportAndPlayers(@RequestBody SportsDTO sportsDTOJson) {
		try {
			// System.out.println( sportsDTOJson);
			// SportsDTO sportsDTO =modelMapper.map(sportsDTOJson, SportsDTO.class);
			System.out.println(sportsDTOJson.toString());

			return ResponseEntity.ok().body(ResponseDTO.success(sportsService.updateSportAndPlayers(sportsDTOJson),
					"sports updated successfuly"));
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

}
