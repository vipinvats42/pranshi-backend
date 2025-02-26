package com.pranshihandicraft.admin.user;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.common.pranshihandicraft.entity.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer> , CrudRepository<User, Integer>{

	@Query("SELECT u FROM User u WHERE u.emailId=:email")
	public User getUserByEmail(@Param("email") String email);
	
	
	public Long countById(Integer id);
	
	
	// 1 and 2 are for the parameter in the query
	@Query("UPDATE User u SET u.enabled = ?2 WHERE u.id = ?1")
	@Modifying
	public void updateUserEnabledStatus(Integer id, boolean enabled);
	
}
