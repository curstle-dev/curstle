package de.wgkassel.curstle.enemy.Boss1;

import de.wgkassel.curstle.enemy.BaseBullet;
import de.wgkassel.curstle.player.Sword;
import greenfoot.Actor;

public class WeaponOfTheBoss extends BaseBullet {

    public WeaponOfTheBoss() {
        setImage("Endboss_sword.png");
        speed = 5;
    }


    @Override
    public void act() {
        super.act();
        counter();

    }


    public void counter() {
        if (isTouching(Sword.class)) {
            removeMe = true;
            return;
        }
    }
}


