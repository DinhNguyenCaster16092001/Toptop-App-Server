package com.cp2196g03g2.server.toptop.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cp2196g03g2.server.toptop.dto.ObjectKey;
import com.cp2196g03g2.server.toptop.dto.UserDto;
import com.cp2196g03g2.server.toptop.entity.ApplicationUser;
import com.cp2196g03g2.server.toptop.exception.InternalServerException;
import com.cp2196g03g2.server.toptop.exception.NotFoundException;
import com.cp2196g03g2.server.toptop.repository.IRoleRepository;
import com.cp2196g03g2.server.toptop.repository.IUserRepository;
import com.cp2196g03g2.server.toptop.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IRoleRepository roleRepository;
	
	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	@Transactional
	public List<ApplicationUser> findAll() {
		return userRepository.findAll();
	}

	@Override
	@Transactional
	public ApplicationUser findById(String id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Cannot found user have id " + id));
	}

	@Override
	@Transactional
	public ApplicationUser save(UserDto userDto) {
		try {
			if (userDto.getAlias() == null && userDto.getId() == null) {
				userDto.setAlias(generateUserAlias(userDto.getFullName()));
			} else {
				userDto.setAlias(userDto.getAlias().toLowerCase());
			}
			ApplicationUser user = modelMapper.map(userDto, ApplicationUser.class);
			user.setRole(roleRepository.findById(userDto.getRole()).get());
			return userRepository.save(user);
		} catch (Exception e) {
			throw new InternalServerException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public void delete(String id) {
		boolean existUser = userRepository.existsById(id);
		if (existUser) {
			userRepository.deleteById(id);
		} else {
			throw new InternalServerException("Cannot delete with user have id: " + id);
		}
	}

	@Override
	public ApplicationUser update(UserDto userDto, String id) {
		try {
			ApplicationUser userInDb = userRepository.findById(id).orElseThrow(() -> new NotFoundException("Cannot found user have id " + id));
			userDto.setAlias(userDto.getAlias().toLowerCase());
			userDto.setEmail(userInDb.getEmail());
			userInDb = modelMapper.map(userDto, ApplicationUser.class); 
			userInDb.setRole(roleRepository.findById(userDto.getRole()).get());
			return userRepository.save(userInDb);
		}catch (Exception e) {
			throw new InternalServerException(e.getMessage());
		}
	}

	private static String generateUserAlias(String fullname) {
		String formatFullName = fullname.replaceAll("[^a-zA-Z]+", "");
		String randomUUId = UUID.randomUUID().toString().substring(0,
				Math.min(UUID.randomUUID().toString().length(), 5));
		return formatFullName.toLowerCase() + randomUUId;
	}

	@Override
	public boolean findByAlias(ObjectKey objectKey) {
		ApplicationUser user = userRepository.findByAlias(objectKey.getTarget());
		if (user != null) {
			if (user.getId().equals(objectKey.getId())) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

	@Override
	public boolean findByEmail(ObjectKey objectKey) {
		ApplicationUser user = userRepository.findByEmail(objectKey.getTarget());
		if (user != null) {
			if (user.getId().equals(objectKey.getId())) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

}
