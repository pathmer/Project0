package com.pathmer.repositorytests;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.pathmer.project0.beans.User;
import com.pathmer.project0.repositories.UserRepository;

public class UserRepositoryTests {
	private UserRepository ur = new UserRepository();
	
	
	@Test
	public void getAllUserTest() {
		
		List<User> users = new ArrayList<User>();
		User u1 = new User(1, "employee", "admin", "password", 123456789, "admin", "istrator", "active");
		User u2 = new User(2, "customer", "bobh", "password", 111111111, "bob", "hope", "active");
		User u3 = new User(3, "customer", "marya", "password", 222222222, "mary", "anne", "active");
		User u4 = new User(4, "customer", "jacks", "password", 333333333, "jack", "man", "pending");
		
		users.add(u1);
		users.add(u2);
		users.add(u3);
		users.add(u4);

		System.out.println(users);
		System.out.println(ur.getAll());
		
		Assert.assertEquals(users, ur.getAll());
		
	}
	
	@Test
	public void getUserByIdTest() {
		
		User u2 = new User(2, "customer", "bobh", "password", 111111111, "bob", "hope", "active");
		
		System.out.println(u2);
		System.out.println(ur.getById(2));
		
		Assert.assertEquals(u2, ur.getById(2));
	}
	
	@Test
	public void addUserTest() {
		
		User u5 = new User(5, "customer", "mikec", "password", 444444444, "mike", "caster", "pending");
		
		Assert.assertEquals(u5, ur.add(u5));
		
		System.out.println(u5);
		System.out.println(ur.getById(5));
		
	}
	
	@Test
	public void updateUserTest() {
		
		User u5 = new User(5, "customer", "mikec", "password", 444444444, "mike", "caster", "active");
		
		Assert.assertEquals(u5, ur.update(u5));
		
		System.out.println(u5);
		System.out.println(ur.getById(5));
		
	}
	
	@Test
	public void deleteUserTest() {
		
		User u5 = new User(5, "customer", "mikec", "password", 444444444, "mike", "caster", "active");
		
		Assert.assertEquals(true, ur.delete(u5.getId()));
	
	}
	
	@Test
	public void getByStatusTest() {
		
		List<User> users = new ArrayList<User>();
		User u4 = new User(4, "customer", "jacks", "password", 333333333, "jack", "man", "pending");
		
		users.add(u4);

		System.out.println(users);
		System.out.println(ur.getByUstatus("pending"));
		
		Assert.assertEquals(users, ur.getByUstatus("pending"));
		
	}

	@Test
	public void getUserByUsernameTest() {
		
		User u2 = new User(2, "customer", "bobh", "password", 111111111, "bob", "hope", "active");
		
		System.out.println(u2);
		System.out.println(ur.getByUsername("bobh"));
		
		Assert.assertEquals(u2, ur.getByUsername("bobh"));
	}

}
