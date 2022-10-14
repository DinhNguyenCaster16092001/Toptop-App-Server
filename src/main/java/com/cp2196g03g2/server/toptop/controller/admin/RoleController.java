package com.cp2196g03g2.server.toptop.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cp2196g03g2.server.toptop.entity.Role;
import com.cp2196g03g2.server.toptop.model.ResponeObject;
import com.cp2196g03g2.server.toptop.service.IRoleService;

@Controller
@CrossOrigin
@RequestMapping("/api/v1/management/role")
public class RoleController {

	@Autowired
	private IRoleService roleService;

	@GetMapping
	public ResponseEntity<ResponeObject> findAll() {
		try {
			return ResponseEntity.ok(new ResponeObject(HttpStatus.OK, "Query Data Success", roleService.findAll()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT)
					.body(new ResponeObject(HttpStatus.NO_CONTENT, e.getMessage(), null));
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponeObject> findById(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(new ResponeObject(HttpStatus.OK, "Query Data Success", roleService.findById(id)));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponeObject(HttpStatus.NOT_FOUND, e.getMessage(), null));
		}
	}

	@PostMapping
	public ResponseEntity<ResponeObject> saveRole(@RequestBody Role role) {
		try {
			return ResponseEntity.ok(new ResponeObject(HttpStatus.OK, "Query Data Success", roleService.save(role)));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ResponeObject(HttpStatus.BAD_REQUEST, e.getMessage(), null));
		}
	}

	/*
	 * @PutMapping("/{id}") public ResponseEntity<ResponeObject>
	 * updateRole(@RequestBody Role role, @PathVariable Long id) { try { Role
	 * savedRole = roleService.findById(id); if (savedRole != null) {
	 * 
	 * } } catch (Exception e) { return ResponseEntity.status(HttpStatus.BA)
	 * .body(new ResponeObject(HttpStatus.NO_CONTENT, e.getMessage(), null)); } }
	 */
}
