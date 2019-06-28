package de.wgkassel.curstle.enemy;

import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.GreenfootImage;

public class BossBar extends Actor {

    private int startLives = 30; //lives of the Boss
    private int barWidth = 330; // Width of the Bar
    private int barHeight = 60; // Height of the Bar
    private int lives = 30;

    public BossBar() {
    }

    /**
     * do what a Bossbar does
     */
    @Override
    public void act() {
        super.act();
        update();
        if (lives == 0) {
            getWorld().removeObject(this);
        }
    }

    /**
     * creates the Bar and updates the filling of it
     */
    public void update() {
        lives = Boss.lives;
        if (lives != 0) {
            int pixelsPerLive = barWidth / startLives; //pixels per life
            setImage(new GreenfootImage(barWidth + 2, barHeight + 2));
            GreenfootImage image = getImage();
            image.setColor(Color.WHITE);
            image.drawRect(0, 0, barWidth + 1, barHeight + 1);
            image.setColor(new Color(11, 91, 36));
            image.fillRect(1, 1, lives * pixelsPerLive, barHeight);

        }
    }


}

