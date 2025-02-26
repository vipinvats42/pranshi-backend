package com.common.pranshihandicraft.entity;

import java.util.Objects;

import org.springframework.stereotype.Component;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="roles")
@Getter
@Setter
@RequiredArgsConstructor
public class Role {

	
	public Role(Integer id) {
		this.id= id;
	}
	
	public Role(String name, String description) {
		this.name = name;
		this.description = description;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		return Objects.equals(id, other.id);
	}
	
	

	@Override
	public String toString() {
		return this.name;
	}



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 40, nullable = false,unique = true)
	@NonNull
	private String name;
	
	@Column(length=250,nullable = false)
	@NonNull
	private String description;
	
}
