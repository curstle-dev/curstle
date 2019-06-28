package de.wgkassel.curstle;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class CurstleTitle extends Actor {
    /**
     * This is the class displaying the name of the game.
     */

    public CurstleTitle() {
        GreenfootImage tt = new GreenfootImage(1300, 300);
        tt.drawImage(new GreenfootImage("curstle.png"), 0, 0);
        setImage(tt);
    }
}
