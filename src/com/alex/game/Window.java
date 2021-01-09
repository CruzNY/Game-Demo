package com.alex.game;


import javax.swing.JFrame;

public class Window extends JFrame {
    public Window(){
        setTitle("Game Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new GamePanel());
        pack();
        setVisible(true);
        setLocation(null);
    }
}
