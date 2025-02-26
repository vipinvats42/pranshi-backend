package com.pranshihandicraft.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/users/check_email")
	public String checkDuplicateEmail(@Param("id")Integer id, @Param("emailId") String emailId) {
		System.out.println("emailId: "+emailId);
		return userService.isEmailUnique(id,emailId)? "OK":"Duplicated";
	}
	
	

}
