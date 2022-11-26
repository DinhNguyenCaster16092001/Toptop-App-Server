package com.cp2196g03g2.server.toptop.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cp2196g03g2.server.toptop.dto.MailRequest;
import com.cp2196g03g2.server.toptop.dto.ObjectKey;
import com.cp2196g03g2.server.toptop.dto.PagableObject;
import com.cp2196g03g2.server.toptop.dto.PagingRequest;
import com.cp2196g03g2.server.toptop.dto.ResetPasswordDto;
import com.cp2196g03g2.server.toptop.dto.UserDto;
import com.cp2196g03g2.server.toptop.entity.ApplicationUser;
import com.cp2196g03g2.server.toptop.entity.Video;
import com.cp2196g03g2.server.toptop.exception.InternalServerException;
import com.cp2196g03g2.server.toptop.exception.NotFoundException;
import com.cp2196g03g2.server.toptop.repository.IFriendShipRepository;
import com.cp2196g03g2.server.toptop.repository.IRoleRepository;
import com.cp2196g03g2.server.toptop.repository.IUserRepository;
import com.cp2196g03g2.server.toptop.repository.IVideoRepository;
import com.cp2196g03g2.server.toptop.service.IUserService;
import com.cp2196g03g2.server.toptop.util.GenerateUtils;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {

	private static final long OTP_VALID_DURATION = 5 * 60 * 1000; // 5 minutes

	@Autowired
	private JavaMailSender sender;

	@Autowired
	private IRoleRepository roleRepository;

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private IVideoRepository videoRepository;

	@Autowired
	private IFriendShipRepository friendShipRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	@Transactional
	public List<ApplicationUser> findAll() {
		return userRepository.findAllByOrderByCreatedDateDesc();
	}

	@Override
	@Transactional
	public ApplicationUser findById(String id) {
		ApplicationUser user = userRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Cannot found user have id " + id));
		setMoreValueForUser(user);
		return user;
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
			ApplicationUser user = new ApplicationUser();
			user.setEmail(userDto.getEmail());
			user.setFullName(userDto.getFullName());
			user.setAlias(userDto.getAlias());
			user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
			user.setActive(userDto.isActive());
			user.setAvatar(userDto.getAvatar());
			user.setHistory(userDto.getHistory());
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
	public ApplicationUser update(UserDto userDto) {
		try {
			ApplicationUser userInDb = userRepository.findById(userDto.getId())
					.orElseThrow(() -> new NotFoundException("Cannot found user have id " + userDto.getId()));

			if (userDto.getAlias() == null) {
				userInDb.setAlias(userInDb.getAlias());
			} else {
				userInDb.setAlias(userDto.getAlias().toLowerCase());
			}
			userInDb.setPassword(userInDb.getPassword());
			userInDb.setFullName(userDto.getFullName());
			userInDb.setAvatar(userDto.getAvatar());
			userInDb.setHistory(userDto.getHistory());
			return userRepository.save(userInDb);
		} catch (Exception e) {
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

	@Override
	public PagableObject<ApplicationUser> findAllByPage(PagingRequest request) {
		Sort sort = request.getSortDir().equalsIgnoreCase(Sort.Direction.ASC.name())
				? Sort.by(request.getSortBy()).ascending()
				: Sort.by(request.getSortBy()).descending();

		// create Pageable instance
		Pageable pageable = PageRequest.of(request.getPageNo(), request.getPageSize(), sort);

		boolean status = request.getIsActive() == 1 ? true : false;

		Page<ApplicationUser> users = userRepository.findAllByPage(request.getKeyword(), status, pageable);

		List<ApplicationUser> listOfUsers = users.getContent();

		PagableObject<ApplicationUser> usersPage = new PagableObject<>();
		usersPage.setData(listOfUsers);
		usersPage.setPageNo(request.getPageNo());
		usersPage.setPageSize(request.getPageSize());
		usersPage.setTotalElements(users.getTotalElements());
		usersPage.setTotalPages(users.getTotalPages());
		usersPage.setLast(users.isLast());

		return usersPage;
	}

	@Override
	@Transactional
	public void updateStatusUser(String id, boolean status) {
		try {
			ApplicationUser user = userRepository.findById(id)
					.orElseThrow(() -> new NotFoundException("Not Found User Have ID" + id));
			userRepository.updateStatusUser(user.getId(), status);
		} catch (Exception e) {
			throw new InternalServerException(e.getMessage());
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ApplicationUser user = userRepository.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("cannot found user have email :" + username);
		} else {
			if (user.isActive() == false) {
				throw new AccessDeniedException("User is not active");
			}
			Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
			return new User(user.getEmail(), user.getPassword(), authorities);
		}
	}

	@Override
	public ApplicationUser saveCustomer(UserDto userDto) {
		try {
			if (userDto.getAlias() == null && userDto.getId() == null) {
				userDto.setAlias(generateUserAlias(userDto.getFullName()));
			} else {
				userDto.setAlias(userDto.getAlias().toLowerCase());
			}
			ApplicationUser user = new ApplicationUser();
			user.setEmail(userDto.getEmail());
			user.setFullName(userDto.getFullName());
			user.setAlias(userDto.getAlias());
			user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
			user.setActive(userDto.isActive());
			user.setAvatar(userDto.getAvatar());
			user.setHistory(userDto.getHistory());
			user.setRole(roleRepository.findById(userDto.getRole()).get());
			String OTP = GenerateUtils.getRandomNumberString().toString();
			user.setOTP(OTP);
			ApplicationUser userSaved = userRepository.save(user);
			return userSaved;
		} catch (Exception e) {
			throw new InternalServerException(e.getMessage());
		}
	}

	@Override
	public ApplicationUser activeUserByOtpCode(String otpCode, String id) {
		ApplicationUser user = userRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Cannot found user have email: " + id));
		if (checkingOTP(user, otpCode)) {
			user.setActive(true);
			user.setOTP(null);
			return userRepository.save(user);
		} else {
			throw new InternalServerException("Invalid OTP Code");
		}
	}

	public boolean checkingOTP(ApplicationUser user, String otpCode) {
		if (user.getOTP() == null) {
			return false;
		}

		if (!user.getOTP().equals(otpCode)) {
			return false;
		}
		return true;
	}

	@Override
	public ApplicationUser sendOtpCodeByEmail(String email) {
		ApplicationUser user = userRepository.findByEmail(email);
		if (user != null) {
			String OTP = GenerateUtils.getRandomNumberString().toString();
			user.setOTP(OTP);
			return userRepository.save(user);
		} else {
			throw new NotFoundException("Cannot found user have email : " + email);
		}
	}

	@Override
	@Transactional
	public ApplicationUser resetPassword(ResetPasswordDto dto) {
		ApplicationUser user = userRepository.findById(dto.getId())
				.orElseThrow(() -> new NotFoundException("Cannot found user have id" + dto.getId()));
		user.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
		return userRepository.save(user);
	}

	@Override
	@Transactional
	public ApplicationUser findByEmail(String email) {
		ApplicationUser user = userRepository.findByEmail(email);
		if (user == null) {
			throw new NotFoundException("Cannot found user have email " + email);
		} else {
			setMoreValueForUser(user);
			return user;
		}

	}

	@Override
	public ApplicationUser loginOrRegisterSocial(UserDto userDto) {
		try {
			ApplicationUser userSocial = userRepository.findByEmail(userDto.getEmail());
			if (userSocial != null) {
				return userSocial;
			}
			if (userDto.getAlias() == null && userDto.getId() == null) {
				userDto.setAlias(generateUserAlias(userDto.getFullName()));
			} else {
				userDto.setAlias(userDto.getAlias().toLowerCase());
			}
			ApplicationUser user = new ApplicationUser();
			user.setEmail(userDto.getEmail());
			user.setFullName(userDto.getFullName());
			user.setAlias(userDto.getAlias());
			user.setActive(userDto.isActive());
			user.setAvatar(userDto.getAvatar());
			user.setRole(roleRepository.findById(userDto.getRole()).get());
			return userRepository.save(user);
		} catch (Exception e) {
			throw new InternalServerException(e.getMessage());
		}
	}

	@Override
	public PagableObject<ApplicationUser> findAllCustomer(PagingRequest request) {
		Sort sort = request.getSortDir().equalsIgnoreCase(Sort.Direction.ASC.name())
				? Sort.by(request.getSortBy()).ascending()
				: Sort.by(request.getSortBy()).descending();
		Pageable pageable = PageRequest.of(request.getPageNo(), request.getPageSize(), sort);

		Page<ApplicationUser> users = userRepository.findAllCustomerByPage(pageable, request.getKeyword());

		List<ApplicationUser> listOfUsers = users.getContent();
		
		listOfUsers.forEach(user -> {
			setMoreValueForUser(user);
		});
		PagableObject<ApplicationUser> usersPage = new PagableObject<>();
		usersPage.setData(listOfUsers);
		usersPage.setPageNo(request.getPageNo());
		usersPage.setPageSize(request.getPageSize());
		usersPage.setTotalElements(users.getTotalElements());
		usersPage.setTotalPages(users.getTotalPages());
		usersPage.setLast(users.isLast());

		return usersPage;
	}

	protected Page<ApplicationUser> listToPage(Pageable pageable, List<ApplicationUser> entities) {
		int lowerBound = pageable.getPageNumber() * pageable.getPageSize();
		int upperBound = Math.min(lowerBound + pageable.getPageSize(), entities.size());

		List<ApplicationUser> subList = entities.subList(lowerBound, upperBound);

		return new PageImpl<ApplicationUser>(subList, pageable, subList.size());
	};

	private void setMoreValueForUser(ApplicationUser user) {
		Long follower = friendShipRepository.countFollowerByUserId(user.getId());
		Long following = friendShipRepository.countFollowingByUserId(user.getId());
		Long view = videoRepository.countViewByUserId(user.getId());
		Long heart = videoRepository.countHeartByUserId(user.getId());
		user.setHeart(heart != null ? heart : 0);
		user.setFollowers(follower != null ? follower : 0);
		user.setFollowing(following != null ? following : 0);
	}

	@Override
	public ApplicationUser findByAlias(String alias) {
		return userRepository.findByAlias(alias);
	}

	@Override
	public long getTotalNewCustomerCurrentMonth() {
		return userRepository.getTotalNewUserOfCurrentMonth();
	}
}
