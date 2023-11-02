package com.squad15.exmed.controllers;

import com.squad15.exmed.config.DadosTokenJWT;
import com.squad15.exmed.dto.DadosAutenticacao;
import com.squad15.exmed.models.Usuario;
import com.squad15.exmed.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<DadosTokenJWT> cadastrar(
            @RequestBody @Valid DadosAutenticacao dados) {
        usuarioService.cadastrar(dados);

        return null;
    }

    @GetMapping
    public List<Usuario> listarUsuarios(){
        return usuarioService.listarUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.buscarUsuarioPorId(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/porSexo/{sexo}")
    public ResponseEntity<List<Usuario>> buscarUsuariosPorSexo(@PathVariable char sexo) {
        List<Usuario> usuarios = usuarioService.buscarUsuariosPorSexo(sexo);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/porIdade/{idade}")
    public ResponseEntity<List<Usuario>> buscarUsuariosPorIdade(@PathVariable int idade) {
        List<Usuario> usuarios = usuarioService.buscarUsuariosPorIdade(idade);
        return ResponseEntity.ok(usuarios);
    }
}