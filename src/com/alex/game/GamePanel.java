package com.alex.game;

import com.alex.game.states.GameStateManager;
import com.alex.game.util.KeyHandler;
import com.alex.game.util.MouseHandler;

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
    private KeyHandler key;
    private MouseHandler mouse;
    private GameStateManager gsm;

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
        // Game Loop
        final double GAME_HERTZ = 60.0;
        final double TBU = 1000000000 / GAME_HERTZ; // TIME BEFORE UPDATE
        final double MUBR = 5; // MUST UPDATE BEFORE RENDER
        final double TARGET_FPS = 60;
        final double  TTBR = 1000000000 / TARGET_FPS; // TOTOAL TIME BEFORE RENDER
        double lastUpdateTime = System.nanoTime();
        int frameCounter = 0;
        int lastSecondTime = (int) (lastUpdateTime / 1000000000);
        int oldFrameCounter = 0;
        double lastRenderTime;
        while(running){
            double now = System.nanoTime();
            int updateCount = 0;
            while(((now - lastUpdateTime)> TBU) && (updateCount<MUBR)){
                update();
                lastUpdateTime += TBU;
                input(mouse, key);
                updateCount++;
            }
            if(now - lastUpdateTime> TBU){
                lastUpdateTime = now - TBU;
            }
            input(mouse, key);
            render();
            draw();
            lastRenderTime = now;
            frameCounter++;
            int thisSecond = (int)(lastUpdateTime / 1000000000);
            if(thisSecond > lastSecondTime){
                if(frameCounter != oldFrameCounter){
                    System.out.println("new sceond: " + thisSecond + " " +frameCounter);
                    oldFrameCounter = frameCounter;
                }
                frameCounter = 0;
                lastSecondTime = thisSecond;
            }
            while(now - lastRenderTime < TTBR && now - lastUpdateTime < TBU){
                Thread.yield();
                try{
                    Thread.sleep(1);
                }catch(Exception e){
                    System.out.println("ERROR: Yielding Thread");
                }
                now = System.nanoTime();
            }
        }
    }
    public void init(){
        running = true;
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g = (Graphics2D) img.getGraphics();
        mouse = new MouseHandler();
        key = new KeyHandler(this);
        gsm = new GameStateManager();
    }
    public void update(){
        gsm.update();
    }
    public void render(){
        if(g != null){
            g.setColor(new Color( 66,134,244));
            g.fillRect(0,0,width,height);
            gsm.render(g);
        }
    }
    public void draw(){
        // Calling method in JPanel
        Graphics g2 = (Graphics) this.getGraphics();
        g2.drawImage(img, 0, 0,width,height,null);
        g2.dispose();
    }
    public void input(MouseHandler mouse, KeyHandler key){
        gsm.input(mouse,key);
    }
}
