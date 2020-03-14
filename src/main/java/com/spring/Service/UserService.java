package com.spring.Service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.Model.User;
import com.spring.Model.UserAccount;
import com.spring.Repository.UserRepository;
import com.spring.Security.UserAccountService;

@Service
@Transactional
public class UserService extends AbstractService {

	@Autowired
	private UserRepository userRepository;

	public User getUserByPrincipal() {
		UserAccount userAccount = UserAccountService.getPrincipal();
		User user =  this.userRepository.findByUserAccount(userAccount.getUsername()).orElse(null);
		return user;
	}
	
	public User findOne(int userId) {
		return this.userRepository.getOne(userId);
	}
}