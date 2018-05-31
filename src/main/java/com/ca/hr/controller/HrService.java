package com.ca.hr.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class HrService {

	public List<User> getUsers() {
		List<User> users = new ArrayList<>();
		users.add(new User("nnn", "nnn@ca.com"));
		users.add(new User("nnn2", "nnn2@ca.com"));
		return users;
	}

}
