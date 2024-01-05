package org.bruKorczak.player;

import lombok.Data;
import org.bruKorczak.model.Carta;
@Data
public class Dealer extends Jogador {
    private Carta cartaOculta;

    public void revelarCartaOculta() {
        if (cartaOculta != null) {
            adicionarCarta(cartaOculta);
            cartaOculta = null;
        }
    }
}