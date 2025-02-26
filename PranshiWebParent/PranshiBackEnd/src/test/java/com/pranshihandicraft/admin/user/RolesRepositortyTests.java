package com.pranshihandicraft.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.common.pranshihandicraft.entity.Role;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RolesRepositortyTests {
	
	@Autowired
	private RolesRepository rolesRepository;
	
	@Test
	public void testCreateFirstRole() {
		Role role = new Role("Admin","manage everything ");
		Role saveRole=rolesRepository.save(role);
		assertThat(saveRole.getId()).isGreaterThan(0);
	}
	
	
	@Test
	public void testCreateRestRoles() {
		Role roleSalesPerson = new Role("Salesperson","manage product price, customers, "
				+ "shipping , orders and sales report");
		Role roleEditorPerson = new Role("Editor","manage categories, brands, "
				+ "products , articles and menus");
		Role roleShipper = new Role("Shipper","view products , view orders and update order status");
		Role roleAssistant = new Role("Assistant","manage questions and reviews");
		
		rolesRepository.saveAll(List.of(roleSalesPerson,roleEditorPerson,roleShipper,roleEditorPerson));
		
		
	}

}
