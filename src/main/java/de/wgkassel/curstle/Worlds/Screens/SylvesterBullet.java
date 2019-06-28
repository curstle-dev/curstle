package de.wgkassel.curstle.Worlds.Screens;

import greenfoot.Actor;

public class SylvesterBullet extends Actor {

    long whenAmIAway = System.currentTimeMillis();
    boolean REMOVE = false;

    public SylvesterBullet(int y) {

        switch (y) {
            case 1:
                setImage("SylvesterRed.png");
                this.getImage().scale(12, 12);
                break;
            case 2:
                setImage("SylvesterBlue.png");
                this.getImage().scale(12, 12);
                break;
            case 3:
                setImage("SylvesterGreen.png");
                this.getImage().scale(12, 12);
                break;
            case 4:
                setImage("SylvesterCyan.png");
                this.getImage().scale(12, 12);
                break;
            case 0:
                setImage("SylvesterYellow.png");
                this.getImage().scale(12, 12);
                break;
        }
    }

    @Override
    public void act() {
        super.act();
        move(7);
        if (isAtEdge() || REMOVE) {
            getWorld().removeObject(this);
        }

        remove();
    }


    /**
     * Bullet removes after 2 seconds
     */
    public void remove() {
        if (System.currentTimeMillis() - whenAmIAway > 1000) {
            REMOVE = true;
        }
    }
}


