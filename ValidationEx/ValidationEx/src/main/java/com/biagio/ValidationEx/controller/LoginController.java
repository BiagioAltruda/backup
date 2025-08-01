package com.biagio.ValidationEx.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biagio.ValidationEx.model.LoginRequest;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/todolist/login")
public class LoginController {

	private Map<String, String> users = new HashMap<>(); //Contains user data
	
	private Set<String> validSessions = new HashSet<>(); //Contains session data
	
	public LoginController() {
		 users.put("alice", "1234");
	     users.put("bob", "abcd");
	}
	
	@PostMapping
	public String login(@RequestBody LoginRequest request) {
		String username = request.getUsername();
		String password = request.getPassword();
		
		if(users.containsKey(username) && users.get(username).equals(password)) {
			String token = UUID.randomUUID().toString();
			validSessions.add(token);
			return token;
		}
		return "LOGIN_FAILED";
	}
	@GetMapping("/profilo")
	public String profile (@RequestHeader(value = "Login-Token", required = false) String token) {
		
		if(token !=null && validSessions.contains(token))
			return "Access granted";
		
		return "Access Denied";
	}
	@PostMapping("/logout")
	public String logout(@RequestHeader(value ="Login-Token", required=false) String token) {
		validSessions.remove(token);
		
		return "Logout successfull";
	}
}
