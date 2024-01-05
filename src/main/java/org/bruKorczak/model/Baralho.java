package org.bruKorczak.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

@Data
public class Baralho {
    private ArrayList<Carta> baralho;
    private Random random = new Random();

    public void criarBaralho() {
        baralho = new ArrayList<Carta>();
        String[] valores = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] naipes = {"C", "D", "H", "S"};

        for (int i = 0; i < naipes.length; i++) {
            for (int j = 0; j < valores.length; j++) {
                Carta carta = new Carta(valores[j], naipes[i]);
                baralho.add(carta);
            }
        }
        System.out.println("CRIAR BARALHO:");
        System.out.println(baralho);
    }

    public void embaralharBaralho() {
        Collections.shuffle(baralho, random);
        System.out.println("APÓS EMBARALHAR");
        System.out.println(baralho);
    }
}
