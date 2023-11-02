package com.squad15.exmed.repository;


import com.squad15.exmed.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByLogin(String login);
    List<Usuario> findBySexo(char sexo);
    List<Usuario> findByIdade(int idade);
    Usuario findByCodigoIndicacao(String codigoIndicacao);
}