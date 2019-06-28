package de.wgkassel.curstle;

import greenfoot.Actor;
import greenfoot.Greenfoot;

public class Luther extends Actor {
    int lutherrotation;
    int lutherdelay;
    int lutherRandomness;
    private int jumpCounter = 0;
    private boolean uped = false;

    public Luther() {

    }

    public void act() {
        lutherrotation = Greenfoot.getRandomNumber(100);
        lutherRandomness = Greenfoot.getRandomNumber(10);
        if (lutherRandomness == 0) {
            turn(20);
        }
        lutherdelay++;
        if (lutherRandomness > 0 && lutherRandomness < 6) {
            if (lutherrotation < 50 && lutherrotation > 40) {
                turn(-10);
                lutherdelay = 0;
            }
            if (lutherrotation < 40 && lutherrotation > 30) {
                turn(-20);
                lutherdelay = 0;
            }
            if (lutherrotation < 30 && lutherrotation > 20) {
                turn(-30);
                lutherdelay = 0;
            }
            if (lutherrotation < 20 && lutherrotation > 10) {
                turn(-40);
                lutherdelay = 0;
            }
            if (lutherrotation < 10) {
                turn(-50);
                lutherdelay = 0;
            }
            if (lutherrotation > 50 && lutherrotation < 60) {
                turn(10);
                lutherdelay = 0;
            }
            if (lutherrotation > 60 && lutherrotation < 70) {
                turn(20);
                lutherdelay = 0;
            }
            if (lutherrotation > 70 && lutherrotation < 80) {
                turn(30);
                lutherdelay = 0;
            }
            if (lutherrotation > 80 && lutherrotation < 90) {
                turn(40);
                lutherdelay = 0;
            }
            if (lutherrotation > 90) {
                turn(50);
                lutherdelay = 0;
            }
        }
        if (lutherRandomness > 6) {
            lutherJump();
        }
        if (isAtEdge()) {
            getWorld().removeObject(this);
        }

    }

    public void lutherJump() {
        if (!uped) {
            if (jumpCounter <= 1) {
                this.setLocation(this.getX(), this.getY() - 1);
            }
            if (jumpCounter <= 2) {
                this.setLocation(this.getX(), this.getY() - 1);
            }
            if (jumpCounter <= 4) {
                this.setLocation(this.getX(), this.getY() - 1);
            }
            if (jumpCounter <= 8) {
                this.setLocation(this.getX(), this.getY() - 1);
            }
            if (jumpCounter <= 16) {
                this.setLocation(this.getX(), this.getY() - 1);
            }
            if (jumpCounter <= 32) {
                this.setLocation(this.getX(), this.getY() - 1);
            }
            if (jumpCounter <= 64) {
                this.setLocation(this.getX(), this.getY() - 1);
            }
            if (jumpCounter <= 80) {
                this.setLocation(this.getX(), this.getY() - 1);
            }
            if (jumpCounter >= 81) {
                uped = true;
                jumpCounter = 0;
            }
        }
    }
}
