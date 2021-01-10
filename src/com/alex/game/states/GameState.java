package com.alex.game.states;

import com.alex.game.util.KeyHandler;
import com.alex.game.util.MouseHandler;

import java.awt.*;

public abstract class GameState {

    private GameStateManager gms;

    public GameState(GameStateManager gms){
        this.gms = gms;
    }
    public abstract void update();
    public abstract void input(MouseHandler mouse, KeyHandler key);
    public abstract void render(Graphics2D g);

}
