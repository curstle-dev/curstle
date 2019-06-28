package de.wgkassel.curstle.Worlds.Screens;

import de.wgkassel.curstle.Worlds.Screens.StartScreen;
import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

public class PlayButton extends Actor {

    /**
     * Just a simple button to start the game.
     */
    public PlayButton() {
        GreenfootImage startb = new GreenfootImage(400, 90);
        startb.drawImage(new GreenfootImage("startbutton.png"), 0, 0);
        setImage(startb);
    }

    @Override
    public void act() {
        super.act();
        if (Greenfoot.mouseClicked(this) || Greenfoot.isKeyDown("enter")) {
            ((StartScreen) getWorld()).startGame();
        }
    }
}