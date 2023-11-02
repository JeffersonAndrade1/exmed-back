package com.squad15.exmed.utils;

import java.util.UUID;

public class GerarCodIndicacao {
    public static String gerarCodigoIndicacao() {
        UUID uuid = UUID.randomUUID();
        String codigo = "IND-" + uuid.toString();
        return codigo;
    }
}
