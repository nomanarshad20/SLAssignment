package com.system.test.assignment.player;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.system.test.assignment.common.StringConstants;
import com.system.test.assignment.dto.PlayerDTO;
import com.system.test.assignment.entities.PlayerEntity;
import com.system.test.assignment.entities.SportsEntity;

import jakarta.annotation.PostConstruct;

@Service
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	PlayerRepositoryInterface playerRepository;

	@Autowired
	private ModelMapper modelMapper;

	@PostConstruct
	public void init() {

		PlayerEntity pe = new PlayerEntity();
		pe.setName("ali");
		pe.setEmail("ali@dfd.com");
		pe.setLevel(10);
		pe.setAge(23);
		pe.setGender(StringConstants.MALE);
		playerRepository.save(pe);

		PlayerEntity pe1 = new PlayerEntity();
		pe1.setName("majid");
		pe1.setEmail("majid@dfd.com");
		pe1.setLevel(1);
		pe1.setAge(20);
		pe1.setGender(StringConstants.MALE);
		playerRepository.save(pe1);

		PlayerEntity pe2 = new PlayerEntity();
		pe2.setName("sara");
		pe2.setEmail("sara@dfd.com");
		pe2.setLevel(8);
		pe2.setAge(22);
		pe2.setGender(StringConstants.FEMALE);
		playerRepository.save(pe2);

		PlayerEntity pe3 = new PlayerEntity();
		pe3.setName("jhon");
		pe3.setEmail("jhon@dfd.com");
		pe3.setLevel(7);
		pe3.setAge(34);
		pe3.setGender(StringConstants.MALE);
		playerRepository.save(pe3);
		System.out.println("***************************player init done");

	}

	@Override
	public PlayerDTO createPlayer(PlayerDTO playerDTO) {
		PlayerEntity pe = new PlayerEntity();
		pe.setName(playerDTO.getName());
		pe.setEmail(playerDTO.getEmail());
		pe.setLevel(playerDTO.getLevel());
		pe.setAge(playerDTO.getAge());
		pe.setGender(playerDTO.getGender());
		PlayerEntity res = playerRepository.save(pe);
		return modelMapper.map(res, PlayerDTO.class);
	}

	@Override
	public List<PlayerDTO> getAllPlayers() {
		List<PlayerEntity> res = playerRepository.findAll();
		return res.stream().map(x -> modelMapper.map(x, PlayerDTO.class)).collect(Collectors.toList());
	}

	@Override
	public List<PlayerDTO> getPlayersByProperties(String gender, int level, int age) {
		this.modelMapper.getConfiguration().setPreferNestedProperties(false);
		List<PlayerEntity> res = playerRepository.getPlayerByGenderAndLevelAndAge(gender, level, age);
		return res.stream().map(x -> modelMapper.map(x, PlayerDTO.class)).collect(Collectors.toList());
	}

	@Override
	public Page<PlayerDTO> getFilteredPlayersList(Pageable pageable) {
		this.modelMapper.getConfiguration().setPreferNestedProperties(false);
		Page<PlayerEntity> res = playerRepository.findAll(pageable);
		List<PlayerDTO> dtoList = res.getContent().stream().map(x -> modelMapper.map(x, PlayerDTO.class))
				.collect(Collectors.toList());
		return new PageImpl<>(dtoList, pageable, res.getTotalElements());
	}

	@Override
	public List<PlayerDTO> findPlayersWithNoSports() {
		List<PlayerEntity> res = playerRepository.findPlayersWithNoSports();
		return res.stream().map(x -> modelMapper.map(x, PlayerDTO.class)).collect(Collectors.toList());
	}

	@Override
	public Page<PlayerDTO> findPlayersWithSportsCategory(String sportsname, Pageable pageable) {
		Page<PlayerEntity> res;
		if (sportsname == null) {
			res = playerRepository.findAll(pageable);
		} else {
			res = playerRepository.findBySportsCategory(sportsname, pageable);
		}
		List<PlayerDTO> dtoList = res.getContent().stream().map(x -> modelMapper.map(x, PlayerDTO.class))
				.collect(Collectors.toList());
		return new PageImpl<>(dtoList, pageable, res.getTotalElements());

	}

}
