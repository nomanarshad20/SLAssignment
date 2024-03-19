package com.system.test.assignment.player;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.system.test.assignment.common.StringConstants;
import com.system.test.assignment.dto.PlayerDTO;
import com.system.test.assignment.entities.PlayerEntity;

import jakarta.annotation.PostConstruct;

@Service
public class PlayerServiceImpl implements PlayerService {

	private PlayerRepositoryInterface playerRepository;

	private ModelMapper modelMapper;

	@Autowired
	public PlayerServiceImpl(PlayerRepositoryInterface playerRepository, ModelMapper modelMapper) {
		this.playerRepository = playerRepository;
		this.modelMapper = modelMapper;
	}

	@PostConstruct
	public void init() {
		try {
			PlayerEntity playerEntity1 = new PlayerEntity();
			playerEntity1.setName("ali");
			playerEntity1.setEmail("ali@dfd.com");
			playerEntity1.setLevel(10);
			playerEntity1.setAge(23);
			playerEntity1.setGender(StringConstants.MALE);
			playerRepository.save(playerEntity1);

			PlayerEntity playerEntity2 = new PlayerEntity();
			playerEntity2.setName("majid");
			playerEntity2.setEmail("majid@dfd.com");
			playerEntity2.setLevel(1);
			playerEntity2.setAge(20);
			playerEntity2.setGender(StringConstants.MALE);
			playerRepository.save(playerEntity2);

			PlayerEntity playerEntity3 = new PlayerEntity();
			playerEntity3.setName("sara");
			playerEntity3.setEmail("sara@dfd.com");
			playerEntity3.setLevel(8);
			playerEntity3.setAge(22);
			playerEntity3.setGender(StringConstants.FEMALE);
			playerRepository.save(playerEntity3);

			PlayerEntity playerEntity4 = new PlayerEntity();
			playerEntity4.setName("jhon");
			playerEntity4.setEmail("jhon@dfd.com");
			playerEntity4.setLevel(7);
			playerEntity4.setAge(34);
			playerEntity4.setGender(StringConstants.MALE);
			playerRepository.save(playerEntity4);
			System.out.println("***************************player init done");
		} catch (DataAccessException ex) {
			throw ex;
		}
	}

	@Override
	public PlayerDTO createPlayer(PlayerDTO playerDTO) {
		try {
			PlayerEntity playerEntity = new PlayerEntity();
			playerEntity.setName(playerDTO.getName());
			playerEntity.setEmail(playerDTO.getEmail());
			playerEntity.setLevel(playerDTO.getLevel());
			playerEntity.setAge(playerDTO.getAge());
			playerEntity.setGender(playerDTO.getGender());
			PlayerEntity response = playerRepository.save(playerEntity);
			return modelMapper.map(response, PlayerDTO.class);
		} catch (DataAccessException ex) {
			throw ex;
		}
	}

	@Override
	public List<PlayerDTO> getAllPlayers() {
		try {
			List<PlayerEntity> response = playerRepository.findAll();
			return response.stream().map(x -> modelMapper.map(x, PlayerDTO.class)).collect(Collectors.toList());
		} catch (DataAccessException ex) {
			throw ex;
		}
	}

	@Override
	public List<PlayerDTO> getPlayersByProperties(String gender, int level, int age) {
		try {
			this.modelMapper.getConfiguration().setPreferNestedProperties(false);
			List<PlayerEntity> response = playerRepository.getPlayerByGenderAndLevelAndAge(gender, level, age);
			return response.stream().map(x -> modelMapper.map(x, PlayerDTO.class)).collect(Collectors.toList());
		} catch (DataAccessException ex) {
			throw ex;
		}
	}

	@Override
	public Page<PlayerDTO> getFilteredPlayersList(Pageable pageable) {
		try {
			this.modelMapper.getConfiguration().setPreferNestedProperties(false);
			Page<PlayerEntity> response = playerRepository.findAll(pageable);
			List<PlayerDTO> dtoList = response.getContent().stream().map(x -> modelMapper.map(x, PlayerDTO.class))
					.collect(Collectors.toList());
			return new PageImpl<>(dtoList, pageable, response.getTotalElements());
		} catch (DataAccessException ex) {
			throw ex;
		}
	}

	@Override
	public List<PlayerDTO> findPlayersWithNoSports() {
		try {
			List<PlayerEntity> response = playerRepository.findPlayersWithNoSports();
			return response.stream().map(x -> modelMapper.map(x, PlayerDTO.class)).collect(Collectors.toList());
		} catch (DataAccessException ex) {
			throw ex;
		}
	}

	@Override
	public Page<PlayerDTO> findPlayersWithSportsCategory(String sportsname, Pageable pageable) {
		try {
			Page<PlayerEntity> response;
			if (sportsname == null) {
				response = playerRepository.findAll(pageable);
			} else {
				response = playerRepository.findBySportsCategory(sportsname, pageable);
			}
			List<PlayerDTO> dtoList = response.getContent().stream().map(x -> modelMapper.map(x, PlayerDTO.class))
					.collect(Collectors.toList());
			return new PageImpl<>(dtoList, pageable, response.getTotalElements());
		} catch (DataAccessException ex) {
			throw ex;
		}

	}

}
