package com.pranshihandicraft.admin.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.common.pranshihandicraft.entity.Role;
import com.common.pranshihandicraft.entity.User;

public class PranshiUserDetails implements UserDetails{

	private  User user;
	public PranshiUserDetails(User user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Role>  roles= user.getRoles();
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		for(Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return authorities;
	}

	@Override
	public String getPassword() {
	
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getFullName();
	}
	
	
     @Override
    public boolean isAccountNonExpired() {
    	
    	return true;
    }
     
    @Override
    public boolean isAccountNonLocked() {
    	
    	return true;
    } 
    
   
    @Override
    public boolean isCredentialsNonExpired() {
    	return true;
    }
    
    @Override
    public boolean isEnabled() {
    	return user.isEnabled();
    }
	
}
