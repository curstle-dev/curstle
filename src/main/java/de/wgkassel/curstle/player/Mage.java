package de.wgkassel.curstle.player;

import greenfoot.Greenfoot;

public class Mage extends Player {

    int cooldown = 40;
    int walkAnimation = 0;

    public Mage() {
        setImage("Mage.png");
        this.getImage().scale(120, 130);
    }

    public void doAct() {
        if (cooldown > 39) {
            attack();
        }
        cooldown++;
        looking();
    }

    public void attack() {
        if (Greenfoot.isKeyDown("up")) {
            MageShot mshot1 = new MageShot();
            getWorld().addObject(mshot1, this.getX(), this.getY() - 50);
            setImage("MageBack.png");
            this.getImage().scale(128, 216);
            mshot1.setRotation(-90);
            cooldown = 0;
        }
        if (Greenfoot.isKeyDown("right")) {
            MageShot mshot2 = new MageShot();
            getWorld().addObject(mshot2, this.getX() + 50, this.getY());
            setImage("MageRight.png");
            this.getImage().scale(128, 216);
            mshot2.setRotation(0);
            cooldown = 0;
        }
        if (Greenfoot.isKeyDown("down")) {
            MageShot mshot3 = new MageShot();
            getWorld().addObject(mshot3, this.getX(), this.getY() + 50);
            setImage("Mage.png");
            this.getImage().scale(128, 216);
            mshot3.setRotation(90);
            cooldown = 0;
        }
        if (Greenfoot.isKeyDown("left")) {
            MageShot mshot4 = new MageShot();
            getWorld().addObject(mshot4, this.getX() - 50, this.getY());
            setImage("MageLeft.png");
            this.getImage().scale(128, 216);
            mshot4.setRotation(-180);
            cooldown = 0;
        }
    }

    public void looking() {

        if (Greenfoot.isKeyDown("w")) {
            if (walkAnimation <= 5) {
                setImage("MageBackWalk1.png");
                walkAnimation = walkAnimation + 1;
            } else if (walkAnimation > 5 && walkAnimation <= 10) {
                setImage("MageBackWalk2.png");
                walkAnimation = walkAnimation + 1;
            } else {
                walkAnimation = 0;
            }
            this.getImage().scale(128, 216);
        } else if (Greenfoot.isKeyDown("d")) {
            if (walkAnimation <= 5) {
                setImage("MageRightWalk1.png");
                walkAnimation = walkAnimation + 1;
            } else if (walkAnimation > 5 && walkAnimation <= 10) {
                setImage("MageRightWalk2.png");
                walkAnimation = walkAnimation + 1;
            } else {
                walkAnimation = 0;
            }
            this.getImage().scale(128, 216);
        } else if (Greenfoot.isKeyDown("a")) {
            if (walkAnimation <= 5) {
                setImage("MageLeftWalk1.png");
                walkAnimation = walkAnimation + 1;
            } else if (walkAnimation > 5 && walkAnimation <= 10) {
                setImage("MageLeftWalk2.png");
                walkAnimation = walkAnimation + 1;
            } else {
                walkAnimation = 0;
            }
            this.getImage().scale(128, 216);
        } else if (Greenfoot.isKeyDown("s")) {
            if (walkAnimation <= 5) {
                setImage("MageFrontWalk1.png");
                walkAnimation = walkAnimation + 1;
            } else if (walkAnimation > 5 && walkAnimation <= 10) {
                setImage("MageFrontWalk2.png");
                walkAnimation = walkAnimation + 1;
            } else {
                walkAnimation = 0;
            }
            this.getImage().scale(128, 216);
        } else if (!upPressed && !downPressed && !leftPressed && !rightPressed && !Greenfoot.isKeyDown("right") && !Greenfoot.isKeyDown("left") && !Greenfoot.isKeyDown("up") && !Greenfoot.isKeyDown("down")) {
            setImage("Mage.png");
            this.getImage().scale(128, 216);
        }
    }
}
