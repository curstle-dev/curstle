package de.wgkassel.curstle.enemy.Endboss;

import greenfoot.Actor;

public class EndbossWeapon extends Actor {

    public EndbossWeapon() {

        setImage("weaponoftheboss.png");
    }

    @Override
    public void act() {
        super.act();
        move(6);
        if (isAtEdge()) {
            getWorld().removeObject(this);
        }
    }
}
