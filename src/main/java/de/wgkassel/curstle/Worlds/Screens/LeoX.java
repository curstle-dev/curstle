package de.wgkassel.curstle.Worlds.Screens;

import greenfoot.Actor;

public class LeoX extends Actor {
    private int jumpCounter = 0;
    private boolean uped = false;

    public LeoX() {
        getImage().scale(220, 220);
    }

    public void act() {
        turn(10);
        papstJump();
        if (isAtEdge()) {
            getWorld().removeObject(this);
        }
    }

    public void papstJump() {
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
