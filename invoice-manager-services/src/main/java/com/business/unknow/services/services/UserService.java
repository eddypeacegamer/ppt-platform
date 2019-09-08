package com.business.unknow.services.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.business.unknow.model.UserDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.entities.User;
import com.business.unknow.services.mapper.UserMapper;
import com.business.unknow.services.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private UserMapper mapper;

	public UserDto getUserById(Integer id) throws InvoiceManagerException {
		Optional<User> user = repository.findById(id);
		if (user.isPresent()) {
			return mapper.getUserDtoFromentity(user.get());
		} else {
			throw new InvoiceManagerException("Role not found", String.format("User with the id %d not found", id),
					HttpStatus.NOT_FOUND.value());
		}

	}
}
