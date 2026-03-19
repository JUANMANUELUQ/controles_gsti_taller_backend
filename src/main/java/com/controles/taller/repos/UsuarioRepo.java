package com.controles.taller.repos;

import com.controles.taller.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepo extends MongoRepository<Usuario, String> {

    @Query("{'nombre':  ?0}")
    Optional<Usuario> encontrarPorNombre(String nombre);

}
