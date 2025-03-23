package com.pranshihandicraft.admin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.common.pranshihandicraft.entity.User;
import com.pranshihandicraft.admin.user.UserRepository;

public class PransheeUserDetailService implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	
	/**
	 * This method is used to load the user by email
	 * this class UsernameNotFoundException is defined in spring security
	 */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user=userRepository.getUserByEmail(email);
		
		if(user!=null) {
			return new PranshiUserDetails(user);
		}else {
			throw new UsernameNotFoundException("Could not find user with email :"+email);
		}
		
	}

}
