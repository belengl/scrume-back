package com.spring.Service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.Model.UserRol;
import com.spring.Repository.UserRolRepository;

@Service
@Transactional
public class UserRolService extends AbstractService {

	@Autowired
	private UserRolRepository userRolRepository;

	public UserRol save(UserRol userRol) throws Exception {
		try {		
			UserRol userRolDB = this.userRolRepository.save(userRol);
			return userRolDB;
		}catch(Exception e) {
			throw new Exception("Error when saving the user rol");
		}
	}
}
