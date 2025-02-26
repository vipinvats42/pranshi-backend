package com.pranshihandicraft.admin.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.common.pranshihandicraft.entity.Role;

@Repository
public interface RolesRepository extends CrudRepository<Role, Integer>{

}
