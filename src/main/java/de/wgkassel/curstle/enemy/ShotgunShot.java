package de.wgkassel.curstle.enemy;

import greenfoot.Actor;

public class ShotgunShot extends BaseBullet {
    private long counter;

    public ShotgunShot(int x) {
        counter = System.currentTimeMillis();
        speed = x;
    }

    @Override
    public void act() {
        super.act();
        if (System.currentTimeMillis() - counter > 700) {
            getWorld().removeObject(this);
        }
    }
}
