package com.pranshihandicraft.admin;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.common.pranshihandicraft.entity.Role;
import com.common.pranshihandicraft.entity.User;
import com.pranshihandicraft.admin.user.RolesRepository;
import com.pranshihandicraft.admin.user.UserRepository;

import jakarta.transaction.Transactional;



@Service
@Transactional
public class UserService {
	public static final int USERS_PER_PAGE = 4;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RolesRepository roleRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public User getByEmail(String email) {
		return userRepo.getUserByEmail(email);
	}
			
		
	private void encodePassword(User user) {
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
	}
	
	public User save(User user) {
		boolean isUpdatingUser= (user.getId() != null);
		
		if(isUpdatingUser) {
		   User existingUser = userRepo.findById(user.getId()).get();
		   
		   if(user.getPassword().isEmpty()) {
			   user.setPassword(existingUser.getPassword());
		   }else {
			   encodePassword(user);
		   }
		   
		}else {
			 encodePassword(user);
		}
		
		return userRepo.save(user);
		
	}
	
	public List<Role> listRoles() {
		return (List<Role>) roleRepo.findAll();
	}
	
	public List<User> listAll() {
		return (List<User>) userRepo.findAll();
	}
	
	public boolean isEmailUnique(Integer id,String email) {
		User userByEmail = userRepo.getUserByEmail(email);
		if(userByEmail == null) return true;
		
		boolean isCreatingNew =(id==null);
		
		if(isCreatingNew) {
			if(userByEmail!=null) return false;
		}else {
			if(userByEmail.getId()!=id) {
				return false;
			}
		}
	     
		
		return true;
		
		
	}


	public User get(Integer id) throws UserNotFoundEception {
		try {
		return userRepo.findById(id).get();
		}catch (Exception e) {
		 throw new UserNotFoundEception("Could not found any user with ID"+ id);	
		}
	}


	public void delete(Integer id) throws UsernameNotFoundException{
		Long countById=userRepo.countById(id);
		
		if(countById == null || countById ==0) {
			throw new  UsernameNotFoundException("Could not found any user wth the ID :"+countById);
		}
		
		userRepo.deleteById(id);
	}
	
	
	public void updateUserEnabledStatus(Integer id, boolean enabled) {
	 userRepo.updateUserEnabledStatus(id, enabled);	
	}
	
	
	public Page<User> listByPage(int pageNumber, String sortField, String sortOrder, String keyword){
		Sort sort = Sort.by(sortField);
		
		sort = sortOrder.equals("asc")? sort.ascending() :sort.descending();
		Pageable pageable = PageRequest.of(pageNumber-1, USERS_PER_PAGE,sort);
		
		if(keyword!=null) {
			return userRepo.findAll(keyword, pageable);
		}
		
		return userRepo.findAll(pageable);
		
	}
	
}