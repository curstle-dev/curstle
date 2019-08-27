package de.wgkassel.curstle.player;

import de.wgkassel.curstle.enemy.Boss1.Boss;
import de.wgkassel.curstle.enemy.Boss1.BossImage;
import de.wgkassel.curstle.enemy.Boss1.WeaponOfTheBoss;
import de.wgkassel.curstle.enemy.Boss2.Boss2;
import de.wgkassel.curstle.enemy.Endboss.Endboss;
import de.wgkassel.curstle.enemy.Endboss.EndbossImage;
import greenfoot.Actor;
import greenfoot.Greenfoot;


public class MageShot extends Actor {
    private int coolDown = 0;
    private int counter = 1;
    private long wait = System.currentTimeMillis();

    public void act() {
        move(20);
        coolDown++;
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
        } else if (isTouching(Boss2.class)) {
            Boss2.lives--;
            Boss2.lives--;
            Greenfoot.playSound("hit.wav");
            getWorld().removeObject(this);
        } else if (isAtEdge()) {
            getWorld().removeObject(this);
        } else if (coolDown >= 25) {
            getWorld().removeObject(this);
            coolDown = 0;
        } else beMoreOp();
        counter();
        switchImage();

    }

    private void beMoreOp() {
        if (isTouching(WeaponOfTheBoss.class)) {
            removeTouching(WeaponOfTheBoss.class);
            getWorld().removeObject(this);
        }

    }

    private void switchImage() {
        switch (counter) {
            case 1:
                setImage("Fire1.png");
                this.getImage().scale(50, 50);
                break;
            case 2:
                setImage("Fire2.png");
                this.getImage().scale(50, 50);
                break;
            case 3:
                setImage("Fire3.png");
                this.getImage().scale(50, 50);
                break;
            case 4:
                setImage("Fire4.png");
                this.getImage().scale(50, 50);
                break;
        }
    }

    private void counter() {
        if (System.currentTimeMillis() - wait > 100){
            counter++;
            wait = System.currentTimeMillis();
            if (counter == 5){
                counter = 1;
            }
        }
    }
}