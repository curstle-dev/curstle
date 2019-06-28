package de.wgkassel.curstle.player;

import greenfoot.Greenfoot;

public class Mage extends Player {

    int cooldown = 40;


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
            setImage("Mage_back.png");
            this.getImage().scale(120, 130);
            mshot1.setRotation(-90);
            cooldown = 0;
        }
        if (Greenfoot.isKeyDown("right")) {
            MageShot mshot2 = new MageShot();
            getWorld().addObject(mshot2, this.getX() + 50, this.getY());
            setImage("Mage_right.png");
            this.getImage().scale(120, 130);
            mshot2.setRotation(0);
            cooldown = 0;
        }
        if (Greenfoot.isKeyDown("down")) {
            MageShot mshot3 = new MageShot();
            getWorld().addObject(mshot3, this.getX(), this.getY() + 50);
            setImage("Mage.png");
            this.getImage().scale(120, 130);
            mshot3.setRotation(90);
            cooldown = 0;
        }
        if (Greenfoot.isKeyDown("left")) {
            MageShot mshot4 = new MageShot();
            getWorld().addObject(mshot4, this.getX() - 50, this.getY());
            setImage("Mage_left.png");
            this.getImage().scale(120, 130);
            mshot4.setRotation(-180);
            cooldown = 0;
        }
    }

    public void looking() {

        if (Greenfoot.isKeyDown("w")) {
            setImage("Mage_back.png");
            this.getImage().scale(120, 130);
        } else if (Greenfoot.isKeyDown("d")) {
            setImage("Mage_right.png");
            this.getImage().scale(120, 130);
        } else if (Greenfoot.isKeyDown("a")) {
            setImage("Mage_left.png");
            this.getImage().scale(120, 130);
        } else if (Greenfoot.isKeyDown("s")) {
            setImage("Mage.png");
            this.getImage().scale(120, 130);
        }
    }
}
