package com.alex.game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable{
    public static int width;
    public static int height;

    private Thread thread;
    private BufferedImage img;
    private Graphics2D g;
    private boolean running = false;

    public GamePanel(int width, int height){
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocus();
    }
    // Called when the JPanel is created
    public void addNotify(){
        super.addNotify();

        if(thread == null){
            thread = new Thread(this, "Game Thread");
            thread.start();
        }
    }
    @Override
    public void run() {
        init();
        while(running){
            update();
            render();
            draw();

        }
    }
    public void init(){
        running = true;
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g = (Graphics2D) img.getGraphics();
    }
    public void update(){
    }
    public void render(){
    }
    public void draw(){
    }

}
