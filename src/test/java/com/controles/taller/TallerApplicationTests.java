package com.controles.taller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.controles.taller.repos.UsuarioRepo;

@SpringBootTest
class TallerApplicationTests {

	@Autowired
	private UsuarioRepo usuarioRepo;

	@Test
	void contextLoads() {
	}

	private String encryptPassword(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(password);
	}

}
