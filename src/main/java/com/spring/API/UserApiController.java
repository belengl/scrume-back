package com.spring.API;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.CustomObject.RegisterDto;
import com.spring.CustomObject.UserOfATeamByWorspaceDto;
import com.spring.Model.User;
import com.spring.Service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserApiController extends AbstractApiController {

	@Autowired
	private UserService userService;

	@GetMapping("/list-by-workspace/{idWorkspace}")
	public Collection<UserOfATeamByWorspaceDto> listUsersOfATeamByWorkspace(@PathVariable int idWorkspace) {
		super.logger.info("GET /api/user/list-by-workspace/" + idWorkspace);
		return this.userService.listUsersOfATeamByWorkspace(idWorkspace);
	}
	
	@GetMapping("/{idUser}")
	public RegisterDto get(@PathVariable Integer idUser) {
		super.logger.info("GET /api/user");
		return this.userService.get(idUser);
	}
	
	@GetMapping("/find-by-authorization")
	@CrossOrigin(origins = "*", methods = { RequestMethod.GET })
	public User getUserByAuthorization(@RequestHeader("authorization") String auth) {
		super.logger.info("GET /api/login/isAValidUser");
		return userService.getByAuthorization(auth);
	}
	
	@PostMapping
	public RegisterDto save(@RequestBody RegisterDto registerDto) {
		super.logger.info("POST /api/user");
		return this.userService.save(registerDto);
	}
	
	@PutMapping("/{idUser}")
	public RegisterDto update(@PathVariable Integer idUser, @RequestBody RegisterDto registerDto) {
		super.logger.info("UPDATE /api/user");
		return this.userService.update(registerDto, idUser);
	}

}
