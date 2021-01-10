package com.alex.game.states;

import com.alex.game.util.KeyHandler;
import com.alex.game.util.MouseHandler;

import java.awt.*;

public class PlayState extends GameState{

    public PlayState(GameStateManager gms) {
        super(gms);
    }

    @Override
    public void update() {

    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        if(key.up.down){
            System.out.println("W is pressed");
        }
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.RED);
        g.fillRect(100,200,64,64);
    }
}
