package com.spring.CustomObject;


import java.util.Date;

import com.spring.Model.Box;

import lombok.Data;

@Data
public class UserDto {
	private Integer id;
	
	private String username;
	
	private String password;
	
	private String name;
	
	private String surnames;
	
    private String nick;

    private String gitUser;

	private String photo;

    private Date endingBoxDate;

    private Box box;


	
	
}
