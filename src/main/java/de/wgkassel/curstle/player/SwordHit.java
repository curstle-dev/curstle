package de.wgkassel.curstle.player;

import de.wgkassel.curstle.Worlds.Level1.BaseWorld;
import greenfoot.Actor;

public class SwordHit extends Actor {
private int offset = 50;

    public SwordHit() {
        setImage("swordHit.png");
        getImage().scale(100, 100);
    }

    @Override
    public void act() {
        super.act();
        removeMe();
        followPlayer();
    }

    public void removeMe() {
        if (!Sword.amIThere)
            getWorld().removeObject(this);
    }

    public void followPlayer(){
        switch (Knight.swordDirection) {
            case UP:
                setLocation(Sword.swordX, Sword.swordY - offset);
                this.setRotation(270);
                break;
            case DOWN:
                setLocation(Sword.swordX, Sword.swordY + offset);
                this.setRotation(90);
                break;
            case LEFT:
                setLocation(Sword.swordX - offset, Sword.swordY);
                this.setRotation(180);
                break;
            case RIGHT:
            default:
                setLocation(Sword.swordX + offset, Sword.swordY);
                this.setRotation(0);
                break;
        }
    }
}
