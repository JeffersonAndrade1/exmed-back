package com.squad15.exmed.service;


import com.squad15.exmed.dto.DadosAutenticacao;
import com.squad15.exmed.models.Usuario;

import com.squad15.exmed.repository.UsuarioRepository;
import com.squad15.exmed.utils.GerarCodIndicacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Transactional
    public void cadastrar(DadosAutenticacao dadosAutenticacao){
        Usuario usuario = new Usuario();
        usuario.setSenha(encoder.encode(dadosAutenticacao.senha()));
        usuario.setLogin(dadosAutenticacao.login());
        usuario.setDataCadastro(new Date());
        String codIndicacao = GerarCodIndicacao.gerarCodigoIndicacao();
        usuario.setCodigoIndicacao(codIndicacao);
        usuarioRepository.save(usuario);
    }
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public void deletarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public List<Usuario> buscarUsuariosPorSexo(char sexo) {
        return usuarioRepository.findBySexo(sexo);
    }

    public List<Usuario> buscarUsuariosPorIdade(int idade) {
        return usuarioRepository.findByIdade(idade);
    }


}

