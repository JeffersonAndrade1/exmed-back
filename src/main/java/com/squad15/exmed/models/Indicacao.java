package com.squad15.exmed.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Indicacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idIndicacao;

    @ManyToOne
    @JoinColumn(name = "indicador_id")
    private Usuario indicador;

    @ManyToOne
    @JoinColumn(name = "indicado_id")
    private Usuario indicado;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataIndicacao;

    public void setIndicador(Usuario indicador) {
        this.indicador = indicador;
    }

    public void setIndicado(Usuario indicado) {
        this.indicado = indicado;
    }


}
