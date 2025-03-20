package io.github.pinjie.reaspring.controller;

import io.github.pinjie.reaspring.dto.UserDto;
import io.github.pinjie.reaspring.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthService authService;

	@Autowired
	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping("/register")
	public Map<String, String> Register(@RequestBody UserDto userDto){
		String token = authService.Register(userDto);
		Map<String, String> reponse = new HashMap<>();
		reponse.put("token", token);
		return reponse;
	}

	@PostMapping("/Login")
	public Map<String, String> Login(@RequestBody UserDto userDto){
		String token = authService.Login(userDto);
		Map<String, String> reponse = new HashMap<>();
		reponse.put("token", token);
		return reponse;
	}
}
