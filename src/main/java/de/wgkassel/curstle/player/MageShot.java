package de.wgkassel.curstle.player;

import de.wgkassel.curstle.enemy.Boss1.Boss;
import de.wgkassel.curstle.enemy.Boss1.BossImage;
import de.wgkassel.curstle.enemy.Boss1.WeaponOfTheBoss;
import de.wgkassel.curstle.enemy.Endboss.Endboss;
import de.wgkassel.curstle.enemy.Endboss.EndbossImage;
import greenfoot.Actor;
import greenfoot.Greenfoot;


public class MageShot extends Actor {
    private int cooldown = 0;

    public MageShot() {
        setImage("MageShot.png");
        this.getImage().scale(32, 32);

    }

    public void act() {
        move(20);
        cooldown++;
        if (isTouching(EndbossImage.class) && !Endboss.invisible) {
            Endboss.live--;
            Endboss.live--;
            Greenfoot.playSound("hit.wav");
            getWorld().removeObject(this);
        } else if (isTouching(BossImage.class)) {
            Boss.lives--;
            Boss.lives--;
            Greenfoot.playSound("hit.wav");
            getWorld().removeObject(this);
        } else if (isAtEdge()) {
            getWorld().removeObject(this);
        } else if (cooldown >= 25) {
            getWorld().removeObject(this);
            cooldown = 0;
        } else bemoreOp();
    }

    public void bemoreOp() {
        if (isTouching(WeaponOfTheBoss.class)) {
            removeTouching(WeaponOfTheBoss.class);
            getWorld().removeObject(this);
        }

    }
}