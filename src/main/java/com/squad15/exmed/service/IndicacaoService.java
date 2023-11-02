package com.squad15.exmed.service;

import com.squad15.exmed.models.Indicacao;
import com.squad15.exmed.models.Usuario;
import com.squad15.exmed.repository.IndicacaoRepository;
import com.squad15.exmed.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class IndicacaoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private IndicacaoRepository indicacaoRepository;

    public boolean indicarUsuario(String codigoIndicacao, Long idUsuarioIndicado) {
        Usuario usuarioIndicador = usuarioRepository.findByCodigoIndicacao(codigoIndicacao);
        if (usuarioIndicador == null) {
            return false;
        }

        Usuario usuarioIndicado = usuarioRepository.findById(idUsuarioIndicado).orElse(null);
        if (usuarioIndicado == null) {
            return false;
        }

        boolean usuarioJaIndicado = indicacaoRepository.existsByIndicado(usuarioIndicado);
        if (usuarioJaIndicado) {
            return false;
        }

        Indicacao indicacao = new Indicacao();
        indicacao.setIndicador(usuarioIndicador);
        indicacao.setIndicado(usuarioIndicado);
        indicacao.setDataIndicacao(new Date());

        indicacaoRepository.save(indicacao);

        return true;
    }

    public Long contarIndicacoesPorCodigo(String codigoIndicacao) {
        Usuario usuario = usuarioRepository.findByCodigoIndicacao(codigoIndicacao);
        if (usuario == null) {
            return 0L;
        }
        return indicacaoRepository.countByIndicador(usuario);
    }
}
