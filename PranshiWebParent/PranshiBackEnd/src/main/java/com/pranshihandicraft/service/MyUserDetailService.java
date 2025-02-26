package com.pranshihandicraft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.common.pranshihandicraft.entity.User;
import com.pranshihandicraft.admin.security.JWTService;
import com.pranshihandicraft.model.UserPrincipal;
import com.pranshihandicraft.repo.UserRepo;

@Service
public class MyUserDetailService implements UserDetailsService{

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	JWTService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user= userRepo.findByUsername(username);
		
		if(user==null) {
			System.out.println("User not found");
			throw new UsernameNotFoundException("User not found");
		}
		
		return  new UserPrincipal(user);
	}
	
	
	public String verify(User user) {
		
		Authentication authentication=
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getFirstName(), user.getPassword()));
		
		if(authentication.isAuthenticated()) {
			return jwtService.generateToken(user.getFirstName());
		}
	
	  return "failed";
	}

}
