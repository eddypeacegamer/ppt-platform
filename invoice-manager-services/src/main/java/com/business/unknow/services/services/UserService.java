package com.business.unknow.services.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.business.unknow.model.UserDto;
import com.business.unknow.model.menu.MenuItem;
import com.business.unknow.model.security.UserDetails;
import com.business.unknow.services.entities.User;
import com.business.unknow.services.mapper.UserMapper;
import com.business.unknow.services.repositories.RoleRepository;
import com.business.unknow.services.repositories.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private RoleRepository rolRepository;

//	@Autowired
//	private ResourceLoader resourceLoader;

	@Autowired
	private UserMapper mapper;

	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	private ObjectMapper objMapper = new ObjectMapper();

	public UserDto getUserById(Integer id) {
		User entity = repository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("user no existe %d", id)));
		return mapper.getUserDtoFromentity(entity);
	}

	public UserDto createUser(UserDto userDto) {
		Optional<User> entity = repository.findByEmail(userDto.getEmail());
		userDto.setActivo(false);
		if (entity.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					String.format("user ya  existe %s", userDto.getEmail()));
		} else {
			User user = repository.save(mapper.getUserEntityFroDto(userDto));
			return mapper.getUserDtoFromentity(user);
		}
	}
	
	public void deleteUser(Integer id) {
		User entity = repository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("user no existe %d", id)));
		rolRepository.findByUserId(id).stream().forEach(a->rolRepository.delete(a));
		repository.delete(entity);
	}

	public UserDto getUserInfo(Authentication auth) throws IOException {
		UserDto user = new UserDto();

		String stringDetails = objMapper.writeValueAsString(auth.getAuthorities());
		List<UserDetails> details = objMapper.readValue(stringDetails, new TypeReference<List<UserDetails>>() {
		});
		String email = details.get(0).getAttributes().getEmail();
		log.info("Looking roles from : {}", email);
		Optional<User> userInfo = repository.findByEmail(email);
		user.setEmail(email);
		user.setName(details.get(0).getAttributes().getName());
		user.setUrlPicture(details.get(0).getAttributes().getPicture());
		if (userInfo.isPresent()) {
			user.setActivo(userInfo.get().getActivo());
			user.setRoles(userInfo.get().getRoles().stream().map(r -> r.getRole()).collect(Collectors.toList()));
		} else {
			user.setActivo(false);
		}
		return setMenuItems(user);
	}

	public UserDto setMenuItems(UserDto user) throws IOException {
		List<MenuItem> menu = new ArrayList<>();
		menu.add(getMenuFromResource("dashboard"));
		menu.add(getMenuFromResource("division"));
		for (String role : user.getRoles()) {
			menu.add(getMenuFromResource(role.toLowerCase()));
		}
		user.setMenu(menu);
		return user;
	}

	private MenuItem getMenuFromResource(String fileName) throws IOException {
		InputStream is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(String.format("menus/%s.json", fileName));
		if (is != null) {
			return objMapper.readValue(is, MenuItem.class);
		} else {
			log.error("menus/{}.json not found.", fileName);
			return new MenuItem();
		}
	}
	
}
