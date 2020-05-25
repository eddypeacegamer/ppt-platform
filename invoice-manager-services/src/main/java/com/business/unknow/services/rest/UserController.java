package com.business.unknow.services.rest;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.business.unknow.model.dto.services.RoleDto;
import com.business.unknow.model.dto.services.UserDto;
import com.business.unknow.services.services.RoleService;
import com.business.unknow.services.services.UserService;


/**
 * @author eej000f
 *
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService service;

	@Autowired
	private RoleService rolService;
	
	@GetMapping("")
	public ResponseEntity<Page<UserDto>> getClientsByParameters(
			@RequestParam(name = "status", defaultValue = "") String status,
			@RequestParam(name = "email", defaultValue = "") String email,
			@RequestParam(name = "alias", defaultValue = "") String alias,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		return new ResponseEntity<>(service.getAllUsersByParams(status, email, alias, page, size), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getRolesByName(@PathVariable Integer id) {
		return new ResponseEntity<>(service.getUserById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto userDto) {
		return new ResponseEntity<>(service.createUser(userDto), HttpStatus.OK);
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> upadteUser(@PathVariable Integer userId, @RequestBody @Valid UserDto userDto) {
		return new ResponseEntity<>(service.updateUser(userId, userDto), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
		service.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/myInfo")
	public ResponseEntity<UserDto> geMyUserInfo(Authentication authentication) throws IOException {
		return new ResponseEntity<>(service.getUserInfo(authentication), HttpStatus.OK);
	}

	@GetMapping("/{id}/roles")
	public ResponseEntity<List<RoleDto>> getRolesByUserId(@PathVariable Integer id) {
		return new ResponseEntity<>(rolService.getRolesByUserId(id), HttpStatus.OK);
	}

	@DeleteMapping("/{userId}/roles/{id}")
	public ResponseEntity<Void> deleteUserRoles(@PathVariable Integer id, @PathVariable Integer userId) {
		rolService.deleteRoleByUserIdAndId(userId, id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/{userId}/roles")
	public ResponseEntity<RoleDto> insertRoleToUser(@PathVariable Integer userId, @RequestBody @Valid  RoleDto roleDto) {
		return new ResponseEntity<>(rolService.insertNewRole(userId, roleDto), HttpStatus.OK);
	}

}
