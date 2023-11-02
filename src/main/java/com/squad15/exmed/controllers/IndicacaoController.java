package com.squad15.exmed.controllers;

import com.squad15.exmed.service.IndicacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/indicacoes")
public class IndicacaoController {

    @Autowired
    private IndicacaoService indicacaoService;

    @PostMapping("/indicar")
    public ResponseEntity<String> indicarUsuario(
            @RequestParam("codigoIndicacao") String codigoIndicacao,
            @RequestParam("idUsuarioIndicado") Long idUsuarioIndicado) {

        boolean indicacaoBemSucedida = indicacaoService.indicarUsuario(codigoIndicacao, idUsuarioIndicado);

        if (indicacaoBemSucedida) {
            return ResponseEntity.ok("Indicação bem-sucedida.");
        } else {
            return ResponseEntity.badRequest().body("Código de indicação inválido ou usuário não encontrado.");
        }
    }

    @GetMapping("/contar")
    public ResponseEntity<Long> contarIndicacoes(
            @RequestParam("codigoIndicacao") String codigoIndicacao) {
        Long contagem = indicacaoService.contarIndicacoesPorCodigo(codigoIndicacao);
        return ResponseEntity.ok(contagem);
    }
}
