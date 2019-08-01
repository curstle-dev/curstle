package de.wgkassel.curstle.enemy.Boss2;

import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.GreenfootImage;

public class Boss2Bar extends Actor {
    private int lives = 40;

    @Override
    public void act() {
        super.act();
        update();
        remove();
    }

    private void update() {
        lives = Boss2.lives;
        if (lives != 0) {
            int startLives = 40;
            int barWidth = 600;
            int pixelsPerLive = barWidth / startLives;
            int barHeight = 60;
            setImage(new GreenfootImage(barWidth + 2, barHeight + 2));
            GreenfootImage image = getImage();
            image.setColor(Color.BLACK);
            image.drawRect(0, 0, barWidth + 1, barHeight + 1);
            image.setColor(new Color(21, 7, 66));
            image.fillRect(1, 1, lives * pixelsPerLive, barHeight);
        }
    }

    private void remove() {
        if (lives <= 0) {
            getWorld().removeObject(this);
        }
    }
}
