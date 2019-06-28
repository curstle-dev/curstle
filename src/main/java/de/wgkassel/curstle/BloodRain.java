package de.wgkassel.curstle;

import greenfoot.Actor;
import greenfoot.Greenfoot;

public class BloodRain extends Actor {

    int z;

    public BloodRain() {
        getImage().setTransparency(0);
    }

    @Override
    public void act() {
        super.act();
        rain();
    }

    public void rain() {
        z = Greenfoot.getRandomNumber(1700);
        Blood blood = new Blood();
        getWorld().addObject(blood, z, 1);
        blood.setRotation(90);
    }
}
