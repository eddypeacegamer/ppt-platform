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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.business.unknow.model.dto.services.UserDto;
import com.business.unknow.model.menu.MenuItem;
import com.business.unknow.services.entities.User;
import com.business.unknow.services.mapper.UserMapper;
import com.business.unknow.services.repositories.RoleRepository;
import com.business.unknow.services.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private RoleRepository rolRepository;

	@Autowired
	private UserMapper mapper;

	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	private ObjectMapper objMapper = new ObjectMapper();
	
	
	public Page<UserDto> getAllUsers(int page, int size){
		Page<User> result = repository.findAll(PageRequest.of(page, size, Sort.by("fechaActualizacion").descending()));
		
		return  new PageImpl<>(mapper.getUsersDtoFromEntities(result.getContent()), result.getPageable(),
				result.getTotalElements());
	}

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
		OidcUser oidcUser =(OidcUser)auth.getPrincipal();
		if(oidcUser!=null && oidcUser.getAttributes()!=null && oidcUser.getEmail()!=null) {
			log.info("Looking roles from : {}", oidcUser.getEmail());
			Optional<User> userInfo = repository.findByEmail(oidcUser.getEmail());
			user.setEmail(oidcUser.getEmail());
			user.setName(oidcUser.getAttributes().get("name").toString());
			user.setUrlPicture(oidcUser.getAttributes().get("picture").toString());
			if (userInfo.isPresent()) {
				user.setActivo(userInfo.get().isActivo());
				user.setRoles(userInfo.get().getRoles().stream().map(r -> r.getRole()).collect(Collectors.toList()));
			} else {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, String.format("%s no es un usuario autorizado", oidcUser.getEmail()));
			}
			return setMenuItems(user);
		}
		log.error("Usuario sin credenciales intenta acceder a la plataforma.");
		throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, String.format("%s no es un usuario autorizado", "anonymous"));
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
