package de.wgkassel.curstle.enemy;

import greenfoot.Actor;

public class ShotgunShot extends Actor {
    private long counter;
    public ShotgunShot(){
        counter = System.currentTimeMillis();
    }
    @Override
    public void act() {
        super.act();
        move(6);
        if (isAtEdge()) {
            getWorld().removeObject(this);
        }
        else if (System.currentTimeMillis() - counter > 700){
            getWorld().removeObject(this);
        }
    }
}
