package com.spring.Service;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.spring.CustomObject.FindByNickDto;
import com.spring.CustomObject.RegisterDto;
import com.spring.CustomObject.UserForWorkspaceDto;
import com.spring.CustomObject.UserOfATeamByWorspaceDto;
import com.spring.Model.Box;
import com.spring.Model.Team;
import com.spring.Model.User;
import com.spring.Model.UserAccount;
import com.spring.Model.Workspace;
import com.spring.Repository.UserRepository;
import com.spring.Security.UserAccountService;
import com.spring.Utiles.Utiles;

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
	
	@Autowired
	private BoxService boxService;
	
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
		return this.userRepository.findById(userId).orElse(null);
	}
	
	public RegisterDto get(Integer idUser) {
		RegisterDto registerDto = new RegisterDto();
		User userDB = this.findOne(idUser);
		UserAccount userAccountDB = this.userAccountService.findOne(userDB.getUserAccount().getId());
		registerDto.setName(userDB.getName());
		registerDto.setCreatedAt(userAccountDB.getCreatedAt());
		registerDto.setEndingBoxDate(userDB.getEndingBoxDate());
		registerDto.setGitUser(userDB.getGitUser());
		registerDto.setIdBox(userDB.getBox().getId());
		registerDto.setLastPasswordChangeAt(userAccountDB.getLastPasswordChangeAt());
		registerDto.setNick(userDB.getNick());
		registerDto.setPassword(userAccountDB.getPassword());
		registerDto.setPhoto(userDB.getPhoto());
		registerDto.setSurnames(userDB.getSurnames());
		registerDto.setUsername(userAccountDB.getUsername());
		return registerDto;
	}
	
	public RegisterDto save(RegisterDto registerDto) {
		Box box = this.boxService.findOne(registerDto.getIdBox());
		UserAccount userAccountDB = new UserAccount(registerDto.getUsername(), registerDto.getPassword(), registerDto.getCreatedAt(), registerDto.getLastPasswordChangeAt(), null);
		User userDB = new User(registerDto.getName(), registerDto.getSurnames(), registerDto.getNick(), registerDto.getGitUser(), registerDto.getPhoto(), registerDto.getEndingBoxDate(), box);
		UserAccount userAccountEntity = this.userAccountService.save(userAccountDB);
		userDB.setUserAccount(userAccountEntity);
		this.userRepository.save(userDB);
		return registerDto;
	}
	
	public RegisterDto update(RegisterDto registerDto, Integer idUser) {
		User userDB = this.findOne(idUser); //User from DB
		UserAccount userAccountDB = this.userAccountService.findOne(userDB.getUserAccount().getId()); //UserAccount from userDB from DB
		UserAccount userAccountEntity = this.userAccountService.update(userAccountDB.getId(), registerDto);
		userDB.setBox(this.boxService.findOne(registerDto.getIdBox()));
		userDB.setEndingBoxDate(registerDto.getEndingBoxDate());
		userDB.setGitUser(registerDto.getGitUser());
		userDB.setName(registerDto.getName());
		userDB.setNick(registerDto.getNick());
		userDB.setPhoto(registerDto.getPhoto());
		userDB.setSurnames(registerDto.getSurnames());
		userDB.setUserAccount(userAccountEntity);
		this.userRepository.saveAndFlush(userDB);
		return registerDto;
	}
	
	public void flush() {
		userRepository.flush();
	}
	
	public Collection<UserForWorkspaceDto> findByNickStartsWith(FindByNickDto findByNickDto){
		List<User> users = this.userRepository.findByNickStartsWith(findByNickDto.getWord());
		Team team = this.teamService.findOne(findByNickDto.getTeam());
		Collection<Integer> idUsers = findByNickDto.getUsers();
		idUsers.addAll(this.userRolService.findIdUsersByTeam(team));
		users = users.stream().filter(u -> !idUsers.contains(u.getId())).collect(Collectors.toList());
		if(users.size() > 5) {
			users = users.subList(0,  4);
		}
		ModelMapper mapper = new ModelMapper();
		Type listType = new TypeToken<List<UserForWorkspaceDto>>() {
		}.getType();
		return mapper.map(users, listType);
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
}
