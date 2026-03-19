package com.controles.taller;

import com.controles.taller.model.Rol;
import com.controles.taller.model.Usuario;
import com.controles.taller.repos.UsuarioRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class TallerApplicationTests {

	@Autowired
	private UsuarioRepo usuarioRepo;

	@Test
	void contextLoads() {
	}

	@Test
	void guardarRegistros() {
		Usuario usuario1 = Usuario.builder()
				.id("6706047ac127c9d5e7e16cc0")
				.nombre("Juan")
				.rol(Rol.ADMIN)
				.clave(new BCryptPasswordEncoder().encode("ABCD1234"))
				.build();
		Usuario usuario2 = Usuario.builder()
				.id("6706047ac127c9d5e7e16cc1")
				.nombre("Miguel")
				.rol(Rol.EMPLOYEE)
				.clave(new BCryptPasswordEncoder().encode("ABCD1234"))
				.build();
		usuarioRepo.save(usuario1);
		usuarioRepo.save(usuario2);
	}

	private String encryptPassword(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(password);
	}

}
