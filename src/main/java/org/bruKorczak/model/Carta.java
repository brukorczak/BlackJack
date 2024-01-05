package org.bruKorczak.model;

import lombok.Data;

@Data
public class Carta {
    private String valor;
    private String naipe;

    Carta(String valor, String naipe) {
        this.valor = valor;
        this.naipe = naipe;
    }

    public String toString() {
        return valor + "-" + naipe;
    }

    public int getValor() {
        if ("AJQK".contains(valor)) {
            if (valor.equals("A")) {
                return 11;
            }
            return 10;
        }
        return Integer.parseInt(valor);
    }

    public boolean isAs() {
        return valor.equals("A");
    }

    public String getCaminhoImagem() {
        return "/cards/" + toString() + ".png";
    }
}