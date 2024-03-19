package com.system.test.assignment.sports;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

	private SportsRepositoryInterface sportsRepository;

	private PlayerRepositoryInterface playerRepository;

	private ModelMapper modelMapper;

	@Autowired
	public SportsServiceImpl(SportsRepositoryInterface sportsRepository, PlayerRepositoryInterface playerRepository,
			ModelMapper modelMapper) {
		this.sportsRepository = sportsRepository;
		this.playerRepository = playerRepository;
		this.modelMapper = modelMapper;
	}

	@PostConstruct
	public void init() {
		try {
			Set<SportsEntity> sportsEntityList = new HashSet<>();

			SportsEntity sportsEntity = new SportsEntity();
			sportsEntity.setName("VideoGames");
			sportsRepository.save(sportsEntity);

			SportsEntity sportsEntity2 = new SportsEntity();
			sportsEntity2.setName("Hockey");
			SportsEntity seSave = sportsRepository.save(sportsEntity2);
			sportsEntityList.add(seSave);

			PlayerEntity playerEntity1 = new PlayerEntity();
			playerEntity1.setName("kashif");
			playerEntity1.setEmail("kashif@dfd.com");
			playerEntity1.setLevel(10);
			playerEntity1.setAge(23);
			playerEntity1.setGender(StringConstants.MALE);
			playerEntity1.setSports(sportsEntityList);
			playerRepository.save(playerEntity1);

			PlayerEntity playerEntity2 = new PlayerEntity();
			playerEntity2.setName("nasir");
			playerEntity2.setEmail("nasir@dfd.com");
			playerEntity2.setLevel(2);
			playerEntity2.setAge(30);
			playerEntity2.setGender(StringConstants.MALE);
			playerEntity2.setSports(sportsEntityList);
			playerRepository.save(playerEntity2);
			System.out.println("***************************Sports init done");
		} catch (DataAccessException ex) {
			throw ex;
		}
	}

	@Override
	public SportsDTO createSports(SportsDTO sportsDTO) {
		try {
			SportsEntity se = new SportsEntity();
			se.setName(sportsDTO.getName());
			SportsEntity res = sportsRepository.save(se);
			return modelMapper.map(res, SportsDTO.class);
		} catch (DataAccessException ex) {
			throw ex;
		}
	}

	@Override
	public List<SportsDTO> getAllSports() {
		try {
			List<SportsEntity> res = sportsRepository.findAll();
			return res.stream().map(x -> modelMapper.map(x, SportsDTO.class)).collect(Collectors.toList());
		} catch (DataAccessException ex) {
			throw ex;
		}
	}

	@Override
	public List<SportsDTO> findSportsWithMultiplePlayers() {
		try {
			List<SportsDTO> listDto = new ArrayList<>();
			List<SportsEntity> res = sportsRepository.findSportsWithMultiplePlayers();
			for (SportsEntity sportsEntity : res) {
				SportsDTO sport = modelMapper.map(sportsEntity, SportsDTO.class);
				Set<PlayerDTO> listPlayers = new HashSet<>();
				for (PlayerEntity player : sportsEntity.getPlayers()) {
					listPlayers.add(new PlayerDTO(player.getId(), player.getName(), player.getLevel(),
							player.getEmail(), player.getGender(), player.getAge()));
				}
				if (listPlayers != null || !listPlayers.isEmpty()) {
					sport.setPlayersList(listPlayers);
				}
				listDto.add(sport);
			}
			return listDto;
		} catch (DataAccessException ex) {
			throw ex;
		}
	}

	@Override
	public List<SportsDTO> findSportsWithNoPlayers() {
		try {
			List<SportsDTO> listDto = new ArrayList<>();
			List<SportsEntity> res = sportsRepository.findSportsWithNoPlayers();
			for (SportsEntity sportsEntity : res) {
				listDto.add(modelMapper.map(sportsEntity, SportsDTO.class));
			}
			return listDto;
		} catch (DataAccessException ex) {
			throw ex;
		}
	}

	@Override
	public List<SportsDTO> findSportsByName(String name) {
		try {
			List<SportsDTO> listDto = new ArrayList<>();
			List<SportsEntity> res = sportsRepository.findSportsByName(name);
			for (SportsEntity sportsEntity : res) {
				SportsDTO sport = modelMapper.map(sportsEntity, SportsDTO.class);
				Set<PlayerDTO> listPlayers = new HashSet<>();
				for (PlayerEntity player : sportsEntity.getPlayers()) {
					listPlayers.add(new PlayerDTO(player.getId(), player.getName(), player.getLevel(),
							player.getEmail(), player.getGender(), player.getAge()));
				}
				if (listPlayers != null || !listPlayers.isEmpty()) {
					sport.setPlayersList(listPlayers);
				}
				listDto.add(sport);
			}
			return listDto;
		} catch (DataAccessException ex) {
			throw ex;
		}
	}

	@Override
	@Transactional
	public void deleteSportAndAssociatedPlayers(int sportId) {
		try {
			SportsEntity sport = sportsRepository.findById(sportId)
					.orElseThrow(() -> new IllegalArgumentException("Sport not found with id: " + sportId));
			for (PlayerEntity player : sport.getPlayers()) {
				playerRepository.delete(player);
			}
			sport.setPlayers(null);
			sportsRepository.delete(sport);
		} catch (DataAccessException ex) {
			throw ex;
		}
	}

	@Override
	@Transactional
	public SportsDTO updateSportAndPlayers(SportsDTO sportsDTO) throws Exception {
		try {
			if (sportsDTO != null) {
				SportsEntity sport = sportsRepository.findById(sportsDTO.getId()).orElseThrow(
						() -> new IllegalArgumentException("Sport not found with id: " + sportsDTO.getId()));
				sport.setName(sportsDTO.getName());
				SportsEntity saved = sportsRepository.save(sport);
				return modelMapper.map(saved, SportsDTO.class);
			} else {
				throw new Exception("sportsDTO is null");
			}
		} catch (DataAccessException ex) {
			throw ex;
		}
	}

}
