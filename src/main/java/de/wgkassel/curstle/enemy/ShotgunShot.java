package de.wgkassel.curstle.enemy;

import greenfoot.Actor;

public class ShotgunShot extends Actor {
    private long counter;

    int speed;

    public ShotgunShot(int x) {
        counter = System.currentTimeMillis();
        speed = x;
    }

    @Override
    public void act() {
        super.act();
        move(speed);
        if (isAtEdge()) {
            getWorld().removeObject(this);
        } else if (System.currentTimeMillis() - counter > 700) {
            getWorld().removeObject(this);
        }
    }
}
