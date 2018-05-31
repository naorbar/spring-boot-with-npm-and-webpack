package com.ca.hr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h3>
 * This tool is ...
 * </h3>
 * It gets as input ...
 * <ol>
 * 	<li>Aaaa</li>
 * 	<li>Bbbb</li>
 * </ol>
 * @author barna10
 */

@RestController
@RequestMapping("/users")
public class HrController {

	@Autowired
	private HrService service;

	@GetMapping
	public @ResponseBody List<User> getUsers() throws Exception {
		return service.getUsers();
	}
		
	
}
