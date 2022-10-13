package com.cp2196g03g2.server.toptop.service.impl;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cp2196g03g2.server.toptop.entity.Role;
import com.cp2196g03g2.server.toptop.exception.InternalServerException;
import com.cp2196g03g2.server.toptop.repository.IRoleRepository;
import com.cp2196g03g2.server.toptop.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private IRoleRepository roleRepository;

	@Override
	@Transactional
	public Set<Role> findAll() {
		try {
			Set<Role> roles = (Set<Role>) roleRepository.findAll();
			return roles;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public Role findById(Long id) {
		try {
			Optional<Role> role = roleRepository.findById(id);
			if (!role.isPresent()) {
				throw new NoSuchElementException("Cannot found role have id " + id);
			}
			return role.get();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public Role save(Role role) {
		try {
			return roleRepository.save(role);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public Role update(Role role, Long id) {
			Role dbRole = roleRepository.findById(id).get();
			if (dbRole != null) {
				if (dbRole.getId() == role.getId()) {
					return roleRepository.save(role);
				} else {
					throw new InternalServerException("Id Not Matching");
				}
			}
			throw new InternalServerException("Cannot Update Role Have Id " + id);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
			boolean isExist = roleRepository.existsById(id);
			if (isExist)
				roleRepository.deleteById(id);
			else
				throw new RuntimeException("Cannot delete role have id " + id);
	}

}
