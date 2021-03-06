package com.alex.game.states;

import com.alex.game.graphics.Font;
import com.alex.game.graphics.Sprite;
import com.alex.game.util.KeyHandler;
import com.alex.game.util.MouseHandler;
import com.alex.game.util.Vector2f;

import java.awt.*;

public class PlayState extends GameState{
    private Font font;
    public PlayState(GameStateManager gms) {
        super(gms);
        font = new Font("font/ZeldaFont.png",16,16);
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
        Sprite.drawArray(g,font, "a your mom", new Vector2f(100,100), 32 , 32,32 ,0);
    }
}
