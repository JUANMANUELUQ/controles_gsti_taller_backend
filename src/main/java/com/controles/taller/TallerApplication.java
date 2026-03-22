package com.controles.taller;

import org.springframework.beans.factory.UnsatisfiedDependencyException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TallerApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(TallerApplication.class, args);
		} catch(UnsatisfiedDependencyException e) {
			System.out.println("Por favor verificar si se reemlplazaron los valores "+
					"respectivos del .env, mirar el .env.example\n\n\n");
			e.printStackTrace();
		}
	}

}
