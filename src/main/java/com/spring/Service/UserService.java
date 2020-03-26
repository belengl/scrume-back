package com.spring.Service;

import java.lang.reflect.Type;
import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.spring.CustomObject.FindByNickDto;
import com.spring.CustomObject.UserDto;
import com.spring.CustomObject.UserForWorkspaceDto;
import com.spring.CustomObject.UserOfATeamByWorspaceDto;
import com.spring.Model.Team;
import com.spring.Model.User;
import com.spring.Model.UserAccount;
import com.spring.Model.Workspace;
import com.spring.Repository.UserRepository;
import com.spring.Security.UserAccountService;

@Service
@Transactional
public class UserService extends AbstractService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserAccountService userAccountService;

	@Autowired
	private UserRolService userRolService;

	@Autowired
	private TeamService teamService;

	@Autowired
	private WorkspaceService workspaceService;

	public Collection<UserOfATeamByWorspaceDto> listUsersOfATeamByWorkspace(Integer idWorkspace) {
		ModelMapper mapper = new ModelMapper();
		User principal = this.getUserByPrincipal();
		Workspace workspace = this.workspaceService.findOne(idWorkspace);
		this.validateWorkspace(workspace);
		Team team = workspace.getSprint().getProject().getTeam();
		this.validateUserPrincipalTeam(principal, team);
		Collection<User> users = this.userRolService.findUsersByTeam(team);

		Type listType = new TypeToken<Collection<UserOfATeamByWorspaceDto>>() {
		}.getType();
		return mapper.map(users, listType);
	}

	public User getUserByPrincipal() {
		UserAccount userAccount = UserAccountService.getPrincipal();
		return this.userRepository.findByUserAccount(userAccount.getUsername()).orElse(null);
	}

	public User findOne(int userId) {
		return this.userRepository.findById(userId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "The requested user doesn´t exists"));
	}

	public UserDto get(Integer idUser) {
		UserDto userDto = new UserDto();
		User userDB = this.findOne(idUser);
		validateUser(userDB);
		userDto.setId(userDB.getId());
		userDto.setName(userDB.getName());
		userDto.setGitUser(userDB.getGitUser());
		userDto.setNick(userDB.getNick());
		userDto.setPhoto(userDB.getPhoto());
		userDto.setSurnames(userDB.getSurnames());
		userDto.setIdUserAccount(userDB.getUserAccount().getId());
		return userDto;
	}

	public UserDto save(UserDto userDto) {
		ModelMapper mapper = new ModelMapper();
		User userEntity = mapper.map(userDto, User.class);
		System.out.println(userEntity.toString());
		User userDB = new User();
		userDB.setGitUser(userEntity.getGitUser());
		userDB.setName(userEntity.getName());
		userDB.setNick(userEntity.getNick());
		userDB.setPhoto(userEntity.getPhoto());
		userDB.setSurnames(userEntity.getSurnames());
		System.out.println("Entro 1");
		UserAccount userAccountDB = this.userAccountService.findOne(userDto.getIdUserAccount());
		System.out.println("Entro 2");
		userDB.setUserAccount(userAccountDB);
		System.out.println("Entro 3");
		validateUser(userDB);
		this.userRepository.saveAndFlush(userDB);
		return userDto;
	}

	public UserDto update(UserDto userDto, Integer idUser) {
		ModelMapper mapper = new ModelMapper();
		User userEntity = mapper.map(userDto, User.class);
		User userDB = this.findOne(idUser); // User from DB
		UserAccount userAccountDB = this.userAccountService.findOne(userDB.getUserAccount().getId()); // UserAccount
																										// from userDB
																										// from DB
		userDB.setGitUser(userEntity.getGitUser());
		userDB.setName(userEntity.getName());
		userDB.setNick(userEntity.getNick());
		userDB.setPhoto(userEntity.getPhoto());
		userDB.setSurnames(userEntity.getSurnames());
		userDB.setUserAccount(userAccountDB);
		validateUser(userDB);
		this.userRepository.saveAndFlush(userDB);
		return mapper.map(userDB, UserDto.class);
	}

	public void flush() {
		userRepository.flush();
	}

	public Collection<UserForWorkspaceDto> findByNickStartsWith(FindByNickDto findByNickDto) {
		List<User> users = this.userRepository.findByNickStartsWith(findByNickDto.getWord());
		Team team = this.teamService.findOne(findByNickDto.getTeam());
		Collection<Integer> idUsers = findByNickDto.getUsers();
		idUsers.addAll(this.userRolService.findIdUsersByTeam(team));
		users = users.stream().filter(u -> !idUsers.contains(u.getId())).collect(Collectors.toList());
		if (users.size() > 5) {
			users = users.subList(0, 4);
		}
		ModelMapper mapper = new ModelMapper();
		Type listType = new TypeToken<List<UserForWorkspaceDto>>() {
		}.getType();
		return mapper.map(users, listType);
	}

	private void validateUser(User user) {
		if (user == null) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "The user is null");
		}
	}

	private void validateUserPrincipalTeam(User principal, Team team) {
		if (principal == null) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "The user must be logged in");
		}
		if (!this.userRolService.isUserOnTeam(principal, team)) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "The user must belong to the team");
		}
	}

	private void validateWorkspace(Workspace workspace) {
		if (workspace == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The workspace is not in the database");
		}
	}

	public User getByAuthorization(String string) {
		User res;
		Base64.Decoder dec = Base64.getDecoder();
		String auth;
		String decodedAuth;
		String username;
		try {
			auth = string.split(" ")[1];
			decodedAuth = new String(dec.decode(auth));
			username = decodedAuth.split(":")[0];
			res = this.userRepository.findUserByUserName(username)
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authorized"));
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The user has not been found");
		}
		return res;
	}

}
