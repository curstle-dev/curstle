package de.wgkassel.curstle.enemy;

import de.wgkassel.curstle.player.Sword;
import greenfoot.Actor;

public class WeaponOfTheBoss extends Actor {

    public WeaponOfTheBoss() {
        setImage("Endboss_sword.png");
    }

    @Override
    public void act() {
        super.act();
        move(5);
        counter();

    }


    public void counter() {
        if (isTouching(Sword.class)) {
            getWorld().removeObject(this);
            return;
        } else if (isAtEdge()) {
            getWorld().removeObject(this);
        }
    }
}


