package org.bruKorczak.Interface;

import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Data
public class TelaBoasVindas {
    private JFrame frame;
    private JPanel painel;
    private JButton btnContinuar;

    public TelaBoasVindas() {
        ImageIcon icon = new ImageIcon(getClass().getResource("/cards/BACK.png"));
        // Inicia a janela
        frame = new JFrame("Blackjack");
        frame.setIconImage(icon.getImage());
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        //inicia layout
        painel = new JPanel(null);
        frame.add(painel);

        //background
        ImageIcon backgroundImage = new ImageIcon(getClass().getResource("/img/bj1.png"));
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        painel.add(backgroundLabel);

        //btn continuar
        btnContinuar = new JButton("START!!");
        btnContinuar.setFont(new Font("Arial", Font.PLAIN, 30));
        btnContinuar.setBackground(new Color(255, 255, 255, 150));
        btnContinuar.setBorder(BorderFactory.createLineBorder(Color.yellow.darker(), 3));
        btnContinuar.setFocusPainted(false);
        int btnWidth = 200;
        int btnHeight = 40;
        btnContinuar.setBounds((frame.getWidth() - btnWidth) / 2, (frame.getHeight() - btnHeight) / 2, btnWidth, btnHeight);
        btnContinuar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new BlackJack();
            }
        });
        painel.add(btnContinuar);
        frame.setVisible(true);
    }
}
