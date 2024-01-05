package org.bruKorczak.Interface;

import lombok.Data;
import org.bruKorczak.model.Baralho;
import org.bruKorczak.model.Carta;
import org.bruKorczak.player.Dealer;
import org.bruKorczak.player.Jogador;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

@Data
public class BlackJack {
    private Dealer dealer;
    private Jogador jogador;
    private Baralho baralho;
    private Carta cartaOculta;

    private static final int LARGURA_TABULEIRO = 600;
    private static final int ALTURA_TABULEIRO = LARGURA_TABULEIRO;
    private static final int LARGURA_CARTA = 110;
    private static final int ALTURA_CARTA = 154;

    JFrame frame = new JFrame("BlackJack");
    JPanel painelDoJogo = new JPanel() {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            try {
                // carta oculta
                Image imagemCartaOculta = new ImageIcon(getClass().getResource("/cards/BACK.png")).getImage();
                if (!btnFicar.isEnabled()) {
                    imagemCartaOculta = new ImageIcon(getClass().getResource(cartaOculta.getCaminhoImagem())).getImage();
                }
                g.drawImage(imagemCartaOculta, 20, 20, LARGURA_CARTA, ALTURA_CARTA, null);

                // mão do dealer
                for (int i = 0; i < dealer.getMaoDoJogador().size(); i++) {
                    Carta carta = dealer.getMaoDoJogador().get(i);
                    Image imagemCarta = new ImageIcon(getClass().getResource(carta.getCaminhoImagem())).getImage();
                    g.drawImage(imagemCarta, LARGURA_CARTA + 25 + (LARGURA_CARTA + 5) * i, 20, LARGURA_CARTA, ALTURA_CARTA, null);
                }

                // mão do jogador
                for (int i = 0; i < jogador.getMaoDoJogador().size(); i++) {
                    Carta carta = jogador.getMaoDoJogador().get(i);
                    Image imagemCarta = new ImageIcon(getClass().getResource(carta.getCaminhoImagem())).getImage();
                    g.drawImage(imagemCarta, 20 + (LARGURA_CARTA + 5) * i, 320, LARGURA_CARTA, ALTURA_CARTA, null);
                }

                if (!btnFicar.isEnabled()) {
                    dealer.setSomaDaMao(dealer.reduzAsDaMao());
                    jogador.setSomaDaMao(jogador.reduzAsDaMao());
                    System.out.println("FICAR: ");
                    System.out.println(dealer.getSomaDaMao());
                    System.out.println(jogador.getSomaDaMao());

                    String mensagem = "";
                    if ((jogador.getSomaDaMao() > 21) && (dealer.getSomaDaMao() > 21)) {
                        mensagem = "BUSTTT!";
                    } else if (jogador.getSomaDaMao() > 21) {
                        mensagem = "Você Perdeu!";
                    } else if (dealer.getSomaDaMao() > 21) {
                        mensagem = "Você Ganhou!";
                    } else if (jogador.getSomaDaMao() == dealer.getSomaDaMao()) {
                        mensagem = "Empate!";
                    } else if (jogador.getSomaDaMao() > dealer.getSomaDaMao()) {
                        mensagem = "Você Ganhou!";
                    } else if (jogador.getSomaDaMao() < dealer.getSomaDaMao()) {
                        mensagem = "Você Perdeu!";
                    }
                    g.setFont(new Font("Arial", Font.PLAIN, 30));
                    g.setColor(Color.white);
                    g.drawString(mensagem, 220, 250);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    JPanel painelDosBotoes = new JPanel();
    JButton btnPedir = new JButton("Pedir");
    JButton btnFicar = new JButton("Ficar");
    JButton btnJogarDnv = new JButton("Jogar + ");
    JButton btnSair = new JButton("Sair");

    private void resetGame() {
        baralho.embaralharBaralho();
        dealer = new Dealer();
        dealer.revelarCartaOculta();
        dealer.getMaoDoJogador().clear();
        dealer.setSomaDaMao(0);

        jogador = new Jogador();
        jogador.getMaoDoJogador().clear();
        jogador.setSomaDaMao(0);

        btnPedir.setEnabled(true);
        btnFicar.setEnabled(true);

        iniciarJogo();
        painelDoJogo.repaint();
    }

    private void btnConfigurar(JButton button, Dimension size, Font font) {
        button.setFocusable(false);
        button.setPreferredSize(size);
        button.setFont(font);
        button.setForeground(Color.yellow);
        button.setBackground(new Color(53, 101, 77));
        button.setBorder(BorderFactory.createLineBorder(Color.yellow.darker(), 3));
        painelDosBotoes.add(button);
    }

    BlackJack() {
        dealer = new Dealer();
        jogador = new Jogador();
        iniciarJogo();

        ImageIcon icon = new ImageIcon(getClass().getResource("/cards/BACK.png"));
        frame.setVisible(true);
        frame.setIconImage(icon.getImage());
        frame.setSize(LARGURA_TABULEIRO, ALTURA_TABULEIRO);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        painelDoJogo.setLayout(new BorderLayout());
        painelDoJogo.setBackground(new Color(53, 101, 77));
        frame.add(painelDoJogo);

        Dimension btnTamanho = new Dimension(100, 30);
        Font btnFont = new Font("Arial", Font.PLAIN, 20);

        btnConfigurar(btnPedir, btnTamanho, btnFont);
        btnConfigurar(btnFicar, btnTamanho, btnFont);
        btnConfigurar(btnJogarDnv, btnTamanho, btnFont);
        btnConfigurar(btnSair, btnTamanho, btnFont);

        frame.add(painelDosBotoes, BorderLayout.SOUTH);
        painelDosBotoes.setBackground(new Color(53, 101, 77));

        btnPedir.addActionListener(e -> {
            Carta carta = baralho.getBaralho().remove(baralho.getBaralho().size() - 1);
            jogador.setSomaDaMao(jogador.getSomaDaMao() + carta.getValor());
            jogador.setContadorAsDaMao(jogador.getContadorAsDaMao() + (carta.isAs() ? 1 : 0));
            jogador.getMaoDoJogador().add(carta);

            if (jogador.reduzAsDaMao() >= 21) {
                btnPedir.setEnabled(false);
            }
            painelDoJogo.repaint();
        });

        btnJogarDnv.addActionListener(e -> {
            resetGame();
            painelDoJogo.repaint();
        });

        btnFicar.addActionListener(e -> {
            btnPedir.setEnabled(false);
            btnFicar.setEnabled(false);

            while (dealer.getSomaDaMao() < 17) {
                Carta carta = baralho.getBaralho().remove(baralho.getBaralho().size() - 1);
                dealer.setSomaDaMao(dealer.getSomaDaMao() + carta.getValor());
                dealer.setContadorAsDaMao(dealer.getContadorAsDaMao() + (carta.isAs() ? 1 : 0));
                dealer.getMaoDoJogador().add(carta);
            }
            painelDoJogo.repaint();
        });

        btnSair.addActionListener(e -> {
            int opcao = JOptionPane.showConfirmDialog(frame,
                    "Deseja realmente sair?", "Confirmação",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (opcao == JOptionPane.YES_OPTION) {
                frame.dispose();
                System.exit(0);
            }
        });

        painelDoJogo.repaint();
    }

    public void iniciarJogo() {
        // baralho
        baralho = new Baralho();
        baralho.criarBaralho();
        baralho.embaralharBaralho();

        // dealer
        dealer.setMaoDoJogador(new ArrayList<>());
        dealer.setSomaDaMao(0);
        dealer.setContadorAsDaMao(0);

        cartaOculta = baralho.getBaralho().remove(baralho.getBaralho().size() - 1);
        dealer.setSomaDaMao(dealer.getSomaDaMao() + cartaOculta.getValor());
        dealer.setContadorAsDaMao(dealer.getContadorAsDaMao() + (cartaOculta.isAs() ? 1 : 0));

        Carta carta = baralho.getBaralho().remove(baralho.getBaralho().size() - 1);
        dealer.setSomaDaMao(dealer.getSomaDaMao() + carta.getValor());
        dealer.setContadorAsDaMao(dealer.getContadorAsDaMao() + (carta.isAs() ? 1 : 0));
        dealer.getMaoDoJogador().add(carta);

        // jogador
        jogador.setMaoDoJogador(new ArrayList<>());
        jogador.setSomaDaMao(0);
        jogador.setContadorAsDaMao(0);

        for (int i = 0; i < 2; i++) {
            carta = baralho.getBaralho().remove(baralho.getBaralho().size() - 1);
            jogador.setSomaDaMao(jogador.getSomaDaMao() + carta.getValor());
            jogador.setContadorAsDaMao(jogador.getContadorAsDaMao() + (carta.isAs() ? 1 : 0));
            jogador.getMaoDoJogador().add(carta);
        }

        System.out.println("JOGADOR: ");
        System.out.println(jogador.getMaoDoJogador());
        System.out.println(jogador.getSomaDaMao());
        System.out.println(jogador.getContadorAsDaMao());
    }
}
