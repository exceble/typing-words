package typewords.ui;// Created by Darius on 20.06.2016.

import typewords.data.DataException;
import typewords.data.WordInterface;
import typewords.data.WordManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class Game extends JPanel{

    JFrame game = new JFrame("Typing-Words");

    public int points = 0, lives = 10;
    public Game test;

    JPanel subPanel = new JPanel();
    JTextField jf = new JTextField("Hello", 42);
    JLabel jPoints = new JLabel("Points: " + points);
    JLabel jLives = new JLabel("Lives: " + lives);

    public String  str = "boo";

    private WordInterface wordProvider;
    int xPos = -50, yPos = 75, ms = 4;

    public Game() {
        wordProvider = new WordManager();
        game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game.setPreferredSize(new Dimension(600,300));
    }

    public void startGame() throws IOException {
        test = new Game();
        game.add(test);

        jPoints.setBackground(Color.GREEN);
        jLives.setBackground(Color.RED);
        jPoints.setOpaque(true);
        jLives.setOpaque(true);

        subPanel.add(jf, 0);
        subPanel.add(jPoints, 1);
        subPanel.add(jLives, 2);
        subPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        game.getContentPane().add(BorderLayout.PAGE_END, subPanel);

        game.pack();
        game.setVisible(true);

        System.out.println(jf.getText());
        System.out.println(jPoints.getText());

        jf.addKeyListener(new KeyListener() {
            String word;

            @Override
            public void keyTyped(KeyEvent e) { }
            @Override
            public void keyPressed(KeyEvent e) { }


            @Override
            public void keyReleased(KeyEvent e) {
                getKey(e);
            }
            
            private void getKey(KeyEvent released) {
                char k = released.getKeyChar();
                System.out.println(released.getKeyChar());
                word = test.str;

                System.out.println(word);

                if(word.charAt(0) == k) {System.out.println("First char released");}
                if(word.charAt(1) == k) {System.out.println("Second char released");}
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setFont(new Font("Times Roman", Font.PLAIN, 18));

        try { Thread.sleep(ms); } catch (InterruptedException e) { e.printStackTrace(); }
        xPos += 1;

        if (xPos >= this.getWidth()) {
            lives-=1;
            jLives.setText("Lives: " + lives);
            System.out.println(jLives.getText());

            xPos = -50;
            yPos = ThreadLocalRandom.current().nextInt(25, 240);
            try {
                str = wordProvider.declareWord();
            } catch (DataException e) {
                e.printStackTrace();
            }
        }

        g2.drawString(str, xPos, yPos);
        repaint();
    }
}

