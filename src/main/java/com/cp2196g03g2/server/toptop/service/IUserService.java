package com.cp2196g03g2.server.toptop.service;

import java.util.List;

import com.cp2196g03g2.server.toptop.dto.ObjectKey;
import com.cp2196g03g2.server.toptop.dto.PagableObject;
import com.cp2196g03g2.server.toptop.dto.PagingRequest;
import com.cp2196g03g2.server.toptop.dto.ResetPasswordDto;
import com.cp2196g03g2.server.toptop.dto.UserDto;
import com.cp2196g03g2.server.toptop.entity.ApplicationUser;

public interface IUserService {
	List<ApplicationUser> findAll();
	PagableObject<ApplicationUser> findAllByPage(PagingRequest request);
	PagableObject<ApplicationUser> findUserByAliasByPage(String alias,PagingRequest request);
	ApplicationUser findById(String id);
	ApplicationUser save(UserDto userDto);
	ApplicationUser saveCustomer(UserDto userDto);
	ApplicationUser update(UserDto dto);
	ApplicationUser activeUserByOtpCode(String otpCode, String email);
	ApplicationUser sendOtpCodeByEmail(String email);
	ApplicationUser resetPassword(ResetPasswordDto dto);
	ApplicationUser findByEmail(String email);
	ApplicationUser findByAlias(String alias);
	void delete(String id);
	boolean findByAlias(ObjectKey objectKey);
	boolean findByEmail(ObjectKey objectKey);
	void updateStatusUser(String id, boolean status);
	ApplicationUser loginOrRegisterSocial(UserDto userDto);
	PagableObject<ApplicationUser> findAllCustomer(PagingRequest request);
	long getTotalNewCustomerCurrentMonth();
}
