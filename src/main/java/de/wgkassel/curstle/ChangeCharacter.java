package de.wgkassel.curstle;

import de.wgkassel.curstle.RoomContent.SilentObjects;
import greenfoot.GreenfootImage;

public class ChangeCharacter extends SilentObjects {
    public ChangeCharacter() {
        GreenfootImage arrow = new GreenfootImage(16, 16);
        arrow.drawImage(new GreenfootImage("arrowStartscreen.png"), 0, 0);
        arrow.scale(80, 80);
        this.setImage(arrow);
    }
}
