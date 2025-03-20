package io.github.pinjie.reaspring.service;

import io.github.pinjie.reaspring.config.JwtUtil;
import io.github.pinjie.reaspring.dto.UserDto;
import io.github.pinjie.reaspring.entity.User;
import io.github.pinjie.reaspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

	private final UserRepository userRepository;

	private final JwtUtil jwtUtil;

	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Autowired
	public AuthService(UserRepository userRepository, JwtUtil jwtUtil) {
		this.userRepository = userRepository;
		this.jwtUtil = jwtUtil;
	}

	public String Register(UserDto userDto){
		if (userRepository.findByUsername(userDto.getUsername()).isPresent()){
			throw new RuntimeException("Username already exists!");
		}

		User user = new User();
		user.setUsername(userDto.getUsername());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());

		userRepository.save(user);

		return jwtUtil.generateToken(user);
	}

	public String Login(UserDto userDto){
		Optional<User> userOptional = userRepository.findByUsername(userDto.getUsername());

		if(userOptional.isPresent() && passwordEncoder.matches(userDto.getPassword(), userOptional.get().getPassword())){
			User user = userOptional.get();  // ✅ 取得 User
			return jwtUtil.generateToken(user); // ✅ 改用 User 物件（符合 UserDetails）
		}

		throw new RuntimeException("Invalid credentials!");
	}
}
