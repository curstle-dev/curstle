package de.wgkassel.curstle.Worlds.Screens;

import greenfoot.Actor;

public class Blood extends Actor {
    long whenAmIAway = System.currentTimeMillis();
    boolean REMOVE = false;

    public Blood() {
        setImage("BloodDrop.png");
    }

    @Override
    public void act() {
        super.act();
        move(4);
        remove();
        if (isAtEdge()) {
            getWorld().removeObject(this);
        }
    }

    public void remove() {
        if (System.currentTimeMillis() - whenAmIAway > 1000) {
            REMOVE = true;
        }
    }
}
