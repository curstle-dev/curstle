package de.wgkassel.curstle.player;


import de.wgkassel.curstle.Worlds.Screens.StartScreen;
import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class Hearts extends Actor {

    int lives;

    public Hearts() {
    }

    @Override
    public void act() {
        super.act();
        update();
    }

    /**
     * creates a heartbar and updates it
     */
    public void update() {
        if (StartScreen.ccc == 0) {
            lives = Player.lives;
        } else if (StartScreen.ccc == 1) {
            lives = Mage.lives;
        }
        GreenfootImage heartBar = new GreenfootImage(220, 18);
        //heartBar.fillRect(0, 0, 220, 18);
        for (int i = 0; i < 10; i++) {
            GreenfootImage heart;
            if (i < lives) {
                heart = new GreenfootImage("heart.png");
            } else {
                heart = new GreenfootImage("emptyheart.png");
            }
            heartBar.drawImage(heart, 22 * i, 0);
        }
        this.setImage(heartBar);
        heartBar.scale(2 * 220, 2 * 18);
    }

}