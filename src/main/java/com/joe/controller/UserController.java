package com.joe.controller;

import java.util.HashMap;
import java.util.Map;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.joe.jooq.tables.pojos.Users;
import com.joe.mapper.UsersMapper;

@RestController
public class UserController {
	
	@Autowired
	UsersMapper usersMapper;
	
	@Autowired
	DSLContext dsl;
	
	@Autowired
	private SimpMessagingTemplate template;
	
	
	@GetMapping("api/get_info")
	public Map<String, String> userInfo() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Map<String, String> result = new HashMap();
		if (principal instanceof UserDetails) {
			result.put("name", ((UserDetails)principal).getUsername());
		} else {
			result.put("name", principal.toString());
		}
		
		return result;
	}
	
	@PostMapping("register")
	public int register(@RequestBody Map<String, String> request) {
		BCryptPasswordEncoder encode = new BCryptPasswordEncoder(10);
		String pwd = encode.encode(request.get("password"));
		Users u = new Users();
		u.setUsername(request.get("name"));
		u.setPassword(pwd);
		int result = usersMapper.insertUser(u);
		return result;
		
	}
	
	@MessageMapping("/change-notice")
	@SendTo("/topic/notice")
	public void greeting() {
		time();
	}

	@Scheduled(fixedDelay = 3000L)
	public void time() {
		template.convertAndSend("/topic/notice", "hello");
//		template.convertAndSend("/topic/notice", new Date().toString());
	}
}
