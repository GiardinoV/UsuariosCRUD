package com.apiResrt.apiRest.Repositories;

import com.apiResrt.apiRest.Models.UsuarioModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends CrudRepository<UsuarioModel, Long> {
    ArrayList<UsuarioModel> findByPrioridad(Integer prioridad);
    ArrayList<UsuarioModel> findByEmail(String email);
}
