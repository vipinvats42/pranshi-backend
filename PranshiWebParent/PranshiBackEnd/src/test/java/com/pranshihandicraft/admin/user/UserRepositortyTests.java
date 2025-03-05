package com.pranshihandicraft.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

import com.common.pranshihandicraft.entity.Role;
import com.common.pranshihandicraft.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositortyTests {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	 private TestEntityManager testEntityManager;
	
	@Test
	public void testCreateNewUserWithOneRole() {
		Role roleAdmin=testEntityManager.find(Role.class, 2);
		User userNamHM = new User("name@codejava.net","nam2020","Name","lastname");
		userNamHM.addRole(roleAdmin);
		
		
		User savedUser=userRepository.save(userNamHM);
		assertThat(savedUser.getId()).isGreaterThan(0);
				
	}
	
	
	@Test
	public void testCreateNewUserWithTwoRole() {
		User userRavi = new User("astha@gmail.com","ravi2020","Ravi","Kumar");
		Role roleEditor=new Role(4);
		Role roleAssistant = new Role(5);
		userRavi.addRole(roleEditor);
		userRavi.addRole(roleAssistant);
		
		User savedUser = userRepository.save(userRavi);
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	
	
	@Test
	public void testListAllUsers() {
		Iterable<User> listUsers = userRepository.findAll();
		listUsers.forEach(user->System.out.println(user));
		
	}
	
	@Test
	public void testGetUserById() {
		User userNam = userRepository.findById(1).get();
		System.out.println(userNam);
		assertThat(userNam).isNotNull();
	}
	
	@Test
	public void testUpdateUserDetails() {
		User userNam= userRepository.findById(1).get();
		userNam.setEnabled(true);
		userNam.setEmailId("vipinvats42@gmail.com");
		userRepository.save(userNam);
	}
	
	
	@Test
	public void testUpdateUserRoles() {
		User userRavi = userRepository.findById(2).get();
		Role roleEditor = new Role(3);
		Role salesPerson = new Role(2);
		
		userRavi.getRole().remove(roleEditor);
		userRavi.addRole(salesPerson);
		
		userRepository.save(userRavi);
		
	}
	
	
	@Test
	public void testDeleteUser() {
		Integer userId =2;
		userRepository.deleteById(userId);
	}
	
	@Test
	public void testGetUserByEmail(){
		String email ="astha@gmail.com";
		User user = userRepository.getUserByEmail(email);
		assertThat(user).isNotNull();
		
	}
	
	@Test
	public void testCountById() {
		Integer id=1;
		Long countById= userRepository.countById(id);
		
		assertThat(countById).isNotNull().isGreaterThan(0);
		
	}
	
	
	@Test
	public void testDisableUser() {
		Integer id = 16;
		userRepository.updateUserEnabledStatus(id, false);
	}
	
	@Test
	public void testEnableUser() {
		Integer id = 24;
		userRepository.updateUserEnabledStatus(id, true);
	}
	
	@Test
	public void testListFirstPage() {
		int pageNumber = 0;
		int pageSize = 4;
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<User> page=userRepository.findAll(pageable);
		
		
		List<User> listUser=page.getContent();
		listUser.forEach(user->System.out.println(user));
		
		assertThat(listUser.size()).isEqualTo(pageSize);
		
	}
	
	
	@Test
	public void testSearchUser() {
		String keyword="bruce";
		
		int pageNumber = 0;
		int pageSize = 4;
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<User> page=userRepository.findAll(keyword,pageable);
		
		
		List<User> listUser=page.getContent();
		listUser.forEach(user->System.out.println(user));
		
		assertThat(listUser.size()).isGreaterThan(0);
		
	}

}
