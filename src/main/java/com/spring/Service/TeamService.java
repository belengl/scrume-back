package com.spring.Service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.spring.CustomObject.TeamDto;
import com.spring.CustomObject.TeamEditDto;
import com.spring.Model.Team;
import com.spring.Model.User;
import com.spring.Model.UserRol;
import com.spring.Repository.TeamRepository;

@Service
@Transactional
public class TeamService extends AbstractService {

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private UserRolService userRolService;

	public TeamDto save(TeamDto teamDto) throws Exception {
		ModelMapper modelMapper = new ModelMapper();
		User principal = this.userService.getUserByPrincipal();
		this.validateUserPrincipal(principal);
		Team teamEntity = modelMapper.map(teamDto, Team.class);
		Team teamDB = this.teamRepository.save(teamEntity);
		UserRol userRol = new UserRol();
		userRol.setAdmin(true);
		userRol.setTeam(teamDB);
		userRol.setUser(principal);
		this.userRolService.save(userRol);
		return modelMapper.map(teamDB, TeamDto.class);
	}

	public TeamEditDto update(TeamEditDto teamEditDto) {
		ModelMapper modelMapper = new ModelMapper();
		User principal = this.userService.getUserByPrincipal();
		Team teamEntity = this.teamRepository.findById(teamEditDto.getId()).orElse(null);
		this.validateTeam(teamEntity);
		this.validateEditPermission(principal, teamEntity);
		this.validateUserPrincipal(principal);
		teamEntity.setName(teamEditDto.getName());
		Team teamDB = this.teamRepository.saveAndFlush(teamEntity);
		return modelMapper.map(teamDB, TeamEditDto.class);
	}

	public void delete(Integer idTeam) {
		User principal = this.userService.getUserByPrincipal();
		Team team = this.teamRepository.findById(idTeam).orElse(null);
		this.validateUserPrincipal(principal);
		this.validateTeam(team);
		this.validateEditPermission(principal, team);
		this.validateDeletePermission(team);
		UserRol userRol = this.userRolService.findByUserAndTeam(principal, team);
		this.userRolService.delete(userRol);
		this.teamRepository.delete(team);
	}
	
	public void deleteVoid(Integer idTeam) {
		this.teamRepository.deleteById(idTeam);
	}
	

	public Team findOne(int teamId) {
		return this.teamRepository.findById(teamId).orElse(null);
	}
	
	private void validateEditPermission(User principal, Team team) {
		if(!this.userRolService.isUserOnTeam(principal, team)) {
			throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "The user must belong to the team");
		}	
		if(!this.userRolService.isAdminOnTeam(principal, team)) {
			throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "The user must be an admin of the team");
		}
	}
	
	private void validateTeam(Team team) {
		if(team == null) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "the team is not in the database");
		}
	}

	private void validateUserPrincipal(User principal){
		if(principal == null) {
			throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "The user must be logged in");
		}
	}

	private void validateDeletePermission(Team team) {
		if(this.userRolService.getNumberOfUsersOfTeam(team) != 1) {
			throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "To delete a team, the only member must be the administrator");
		}
	}

}
