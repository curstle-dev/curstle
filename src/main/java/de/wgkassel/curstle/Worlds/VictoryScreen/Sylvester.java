package de.wgkassel.curstle.Worlds.VictoryScreen;

import de.wgkassel.curstle.Worlds.VictoryScreen.SylvesterBullet;
import greenfoot.Actor;
import greenfoot.Greenfoot;

public class Sylvester extends Actor {

    long shotPause = System.currentTimeMillis();
    int z;
    int y;
    int pause = 500;
    boolean triggert = true;

    public Sylvester() {
        getImage().setTransparency(0);
    }

    @Override
    public void act() {
        super.act();
        attack();
    }

    public void attack() {
        int x = 0;
        if (System.currentTimeMillis() - shotPause > pause) {
            if (triggert) {
                z = Greenfoot.getRandomNumber(1700);
                y = Greenfoot.getRandomNumber(5);
                triggert = false;
                pause = 200;
            } else {
                pause = 500;
                triggert = true;
            }
            for (int i = 0; i <= 30; i++) {
                doShoot(x);
                x = x + 12;
            }
        }
    }

    /**
     * shoot
     *
     * @param rotationOffset
     */
    public void doShoot(int rotationOffset) {
        SylvesterBullet sylvesterBullet = new SylvesterBullet(y);
        this.getWorld().addObject(sylvesterBullet, z, this.getY());
        int rotation = sylvesterBullet.getRotation();
        sylvesterBullet.setRotation(rotation + rotationOffset);
        shotPause = System.currentTimeMillis();
    }


}
