package com.common.pranshihandicraft.entity;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {
	public User(String emailId, String password, String firstName, String lastName) {
		this.emailId=emailId;
		this.password=password;
		this.firstName=firstName;
		this.lastName=lastName;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 128, nullable =false, unique = true)
	private String emailId;
	
	@Column(length = 64, nullable = false)
	private String password;
	
	@Column(name ="first_name",length = 45, nullable = false)
	private String firstName;
	
	@Column(name ="last_name",length = 45, nullable = false)
	private String lastName;
	
	@Column(length = 64)
	private String photos;
	
	private boolean enabled;
	
	@ManyToMany
	@JoinTable(
			name = "users_roles",
			joinColumns = @JoinColumn(name="user_id"),
			inverseJoinColumns = @JoinColumn(name="role_id")
			
			)
	
	private Set<Role> roles= new HashSet();
	
	public void addRole(Role role) {
		this.roles.add(role);
	}
	
	public Set<Role> getRole() {
		return roles;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", emailId=" + emailId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", roles=" + roles + "]";
	}
	
	
	@Transient
	public String getPhotosImagePath() {
		if (id == null || photos == null) return "/images/default.jpg";
		
		return "/user-photos/" + this.id + "/" + this.photos;
	}

}
