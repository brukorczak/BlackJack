package org.bruKorczak.player;

import lombok.Data;
import org.bruKorczak.model.Carta;

import java.util.ArrayList;

@Data
public class Jogador {
    private ArrayList<Carta> maoDoJogador;
    private int somaDaMao;
    private int contadorAsDaMao;

    public Jogador() {
        maoDoJogador = new ArrayList<>();
        somaDaMao = 0;
        contadorAsDaMao = 0;
    }

    public void adicionarCarta(Carta carta) {
        somaDaMao += carta.getValor();
        contadorAsDaMao += carta.isAs() ? 1 : 0;
        maoDoJogador.add(carta);
    }

    public int reduzAsDaMao() {
        while (somaDaMao > 21 && contadorAsDaMao > 0) {
            somaDaMao -= 10;
            contadorAsDaMao -= 1;
        }
        return somaDaMao;
    }
}