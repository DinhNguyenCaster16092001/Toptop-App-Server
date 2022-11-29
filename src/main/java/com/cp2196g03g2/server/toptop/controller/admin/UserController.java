package com.cp2196g03g2.server.toptop.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cp2196g03g2.server.toptop.constant.AppConstants;
import com.cp2196g03g2.server.toptop.dto.BooleanResult;
import com.cp2196g03g2.server.toptop.dto.ObjectKey;
import com.cp2196g03g2.server.toptop.dto.PagableObject;
import com.cp2196g03g2.server.toptop.dto.PagingRequest;
import com.cp2196g03g2.server.toptop.dto.ResetPasswordDto;
import com.cp2196g03g2.server.toptop.dto.UserDto;
import com.cp2196g03g2.server.toptop.entity.ApplicationUser;
import com.cp2196g03g2.server.toptop.model.ChartCloumModel;
import com.cp2196g03g2.server.toptop.service.IUserService;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/management/user")
@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
public class UserController {

	@Autowired
	private IUserService userService;
	/*
	 * @GetMapping public List<ApplicationUser> findAll() { return
	 * userService.findAll(); }
	 */

	@GetMapping
	public PagableObject<ApplicationUser> findAllByPage(
			@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir,
			@RequestParam(value = "keyword", defaultValue = AppConstants.DEFAULT_KEYWORD, required = false) String keyword,
			@RequestParam(value = "active", defaultValue = AppConstants.DEFAULT_STATUS, required = false) int status) {
		PagingRequest request = new PagingRequest(pageNo, pageSize, sortBy, sortDir, keyword, status);
		return userService.findAllByPage(request);
	}

	@GetMapping("/{id}")
	public ApplicationUser findById(@PathVariable String id) {
		return userService.findById(id);
	}

	@PostMapping
	public ApplicationUser saveUser(@RequestBody UserDto userDto) {
		return userService.save(userDto);
	}

	@PutMapping
	public ApplicationUser updateUser(@RequestBody UserDto userDto) {
		return userService.update(userDto);
	}

	@PutMapping("/{id}/active/{status}")
	public void updateUser(@PathVariable(name = "id") String id, @PathVariable(name = "status") boolean status) {
		userService.updateStatusUser(id, status);
	}

	@GetMapping("/alias")
	public BooleanResult existAlias(@RequestParam(value = "target", required = true) String alias, @RequestParam(value = "id", required = false) String id) {
		ObjectKey objectKey = new ObjectKey(alias, id);
		return new BooleanResult(userService.findByAlias(objectKey));
	}

	@GetMapping("/email")
	public BooleanResult existEmail(@RequestParam(value = "target", required = true) String email, @RequestParam(value = "id", required = false) String id) {
		ObjectKey objectKey = new ObjectKey(email, id);
		return new BooleanResult(userService.findByEmail(objectKey));
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
	public void deleteUser(@PathVariable String id) {
		userService.delete(id);
	}
	
	@PutMapping("/password/reset")
	public ApplicationUser resetPassword(@RequestBody ResetPasswordDto resetPasswordDto) {
		return userService.resetPassword(resetPasswordDto);
	}
	
	@GetMapping("/customer")
	public PagableObject<ApplicationUser> sortRankCustomer(@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir){
		PagingRequest request = new PagingRequest(pageNo, pageSize, sortBy, sortDir);
		return userService.findAllCustomer(request);
	}
	
	
	@GetMapping("/reports/customer/month")
	public long getTotalNewUserCurrentMonth() {
		return userService.getTotalNewCustomerCurrentMonth();
	}
	
	@GetMapping("/reports/customer")
	public List<ChartCloumModel> getTotalNewUserCurrentMonth(@RequestParam("year") Integer year) {
		return userService.reportByYear(year);
	}
	
}
