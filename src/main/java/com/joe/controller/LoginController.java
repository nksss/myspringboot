package com.joe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@Controller
public class LoginController {
	
//	@Autowired
//	UsersMapper usersMapper;
	
	@Autowired
	private SimpMessagingTemplate template;

	@RequestMapping("/login")
    public String login(){
        return "login";
    }
//	@PostMapping("login")
//	public String login(@RequestParam(value = "name") String name, @RequestParam(value = "password", defaultValue = "0") String password, HttpServletRequest request) throws Exception {
//		String phone = request.getParameter("phone;
//		String password = request.getParameter("password");
//		String seed = "base64:+f6hEuN648DaOoDhHtDmzI1YvxvTHI/dNsaiCeEdi2I=";
//		Users user = usersMapper.findByName(name);
//		BCryptPasswordEncoder encode = new BCryptPasswordEncoder(10);
//		boolean a = encode.matches(password, user.getPassword());
//		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(name, password);
//
//		Authentication authentication = myAuthenticationManager.authenticate(token);
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//		HttpSession session = request.getSession();
//		session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
//		return SecurityContextHolder.getContext().toString();
//
//	}
	
//	@PostMapping("logout")
//	public boolean logout(HttpServletRequest request) {
//		HttpSession session = request.getSession();
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		ServletContext application = session.getServletContext();
//		HashSet sessions = (HashSet) application.getAttribute("sessions");
//		return sessions.remove(session);
//		return session.getId();
//	}
	
//	@PostMapping("register")
//	public int register(@RequestBody Map<String, String> request) {
//		BCryptPasswordEncoder encode = new BCryptPasswordEncoder(10);
//		String pwd = encode.encode(request.get("password"));
//		Users u = new Users();
//		u.setName(request.get("name"));
//		u.setPassword(pwd);
//		int result = usersMapper.newUser(u);
//		return result;
//		
//	}
	
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
