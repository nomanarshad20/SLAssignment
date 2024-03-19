package com.system.test.assignment.sports;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.test.assignment.common.StringConstants;
import com.system.test.assignment.dto.PlayerDTO;
import com.system.test.assignment.dto.SportsDTO;
import com.system.test.assignment.entities.PlayerEntity;
import com.system.test.assignment.entities.SportsEntity;
import com.system.test.assignment.player.PlayerRepositoryInterface;

import jakarta.annotation.PostConstruct;

@Service
public class SportsServiceImpl implements SportsService {

	@Autowired
	SportsRepositoryInterface sportsRepository;

	@Autowired
	PlayerRepositoryInterface playerRepository;

	@Autowired
	private ModelMapper modelMapper;

	@PostConstruct
	public void init() {

		Set<SportsEntity> list = new HashSet<>();

		SportsEntity se4 = new SportsEntity();
		se4.setName("VideoGames");
		sportsRepository.save(se4);

		SportsEntity se = new SportsEntity();
		se.setName("Hockey");
		SportsEntity seSave = sportsRepository.save(se);
		list.add(seSave);

		PlayerEntity pe = new PlayerEntity();
		pe.setName("kashif");
		pe.setEmail("kashif@dfd.com");
		pe.setLevel(10);
		pe.setAge(23);
		pe.setGender(StringConstants.MALE);
		pe.setSports(list);
		PlayerEntity pes = playerRepository.save(pe);

		PlayerEntity pe1 = new PlayerEntity();
		pe1.setName("nasir");
		pe1.setEmail("nasir@dfd.com");
		pe1.setLevel(2);
		pe1.setAge(30);
		pe1.setGender(StringConstants.MALE);
		pe1.setSports(list);
		PlayerEntity pes1 = playerRepository.save(pe1);

		/*
		 * Set<PlayerEntity> list = new HashSet<>(); list.add(pes); list.add(pes1);
		 * se.setPlayers(list); sportsRepository.save(se);
		 */

		System.out.println("***************************Sports init done");
	}

	@Override
	public SportsDTO createSports(SportsDTO sportsDTO) {

		SportsEntity se = new SportsEntity();
		se.setName(sportsDTO.getName());
		SportsEntity res = sportsRepository.save(se);
		return modelMapper.map(res, SportsDTO.class);
	}

	@Override
	public List<SportsDTO> getAllSports() {
		List<SportsEntity> res = sportsRepository.findAll();
		return res.stream().map(x -> modelMapper.map(x, SportsDTO.class)).collect(Collectors.toList());
	}

	@Override
	public List<SportsDTO> findSportsWithMultiplePlayers() {
		List<SportsDTO> listDto = new ArrayList<>();
		List<SportsEntity> res = sportsRepository.findSportsWithMultiplePlayers();
		for (SportsEntity sportsEntity : res) {
			SportsDTO sport = modelMapper.map(sportsEntity, SportsDTO.class);
			Set<PlayerDTO> listPlayers = new HashSet<>();
			for (PlayerEntity player : sportsEntity.getPlayers()) {
				listPlayers.add(new PlayerDTO(player.getId(), player.getName(), player.getLevel(), player.getEmail(),
						player.getGender(), player.getAge()));
			}
			if (listPlayers != null || !listPlayers.isEmpty()) {
				sport.setPlayersList(listPlayers);
			}
			listDto.add(sport);
		}
		return listDto;
	}

	@Override
	public List<SportsDTO> findSportsWithNoPlayers() {
		List<SportsDTO> listDto = new ArrayList<>();
		List<SportsEntity> res = sportsRepository.findSportsWithNoPlayers();
		for (SportsEntity sportsEntity : res) {
			listDto.add(modelMapper.map(sportsEntity, SportsDTO.class));
		}
		return listDto;
	}

	@Override
	public List<SportsDTO> findSportsByName(String name) {
		List<SportsDTO> listDto = new ArrayList<>();
		List<SportsEntity> res = sportsRepository.findSportsByName(name);
		for (SportsEntity sportsEntity : res) {
			SportsDTO sport = modelMapper.map(sportsEntity, SportsDTO.class);
			Set<PlayerDTO> listPlayers = new HashSet<>();
			for (PlayerEntity player : sportsEntity.getPlayers()) {
				listPlayers.add(new PlayerDTO(player.getId(), player.getName(), player.getLevel(), player.getEmail(),
						player.getGender(), player.getAge()));
			}
			if (listPlayers != null || !listPlayers.isEmpty()) {
				sport.setPlayersList(listPlayers);
			}
			listDto.add(sport);
		}
		return listDto;
	}

	@Override
	@Transactional
	public void deleteSportAndAssociatedPlayers(int sportId) {
		SportsEntity sport = sportsRepository.findById(sportId)
				.orElseThrow(() -> new IllegalArgumentException("Sport not found with id: " + sportId));
		for (PlayerEntity player : sport.getPlayers()) {
			playerRepository.delete(player);
		}
		sport.setPlayers(null);
		sportsRepository.delete(sport);
	}

	@Override
	@Transactional
	public SportsDTO updateSportAndPlayers(SportsDTO sportsDTO) throws Exception {
		if (sportsDTO != null) {
			SportsEntity sport = sportsRepository.findById(sportsDTO.getId())
					.orElseThrow(() -> new IllegalArgumentException("Sport not found with id: " + sportsDTO.getId()));
			sport.setName(sportsDTO.getName());
			SportsEntity saved = sportsRepository.save(sport);
			return modelMapper.map(saved, SportsDTO.class);
		} else {
			throw new Exception("sportsDTO is null");
		}
	}

}
