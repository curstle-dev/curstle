package de.wgkassel.curstle.enemy;

import de.wgkassel.curstle.Worlds.Level1.BaseWorld;
import de.wgkassel.curstle.player.Player;
import greenfoot.Actor;

public class BeeBullet extends BaseBullet {


    long whenAmIAway = System.currentTimeMillis();
    boolean allowToShoot = true;

    public BeeBullet() {
        setImage("beeBullet2.png");
        this.getImage().scale(24, 3);
        speed = 5;
    }

    /**
     * do what a BeeBullet does
     */
    @Override
    public void act() {
        super.act();
        turnTowords();
        remove();
    }

    /**
     * Bullet removes after 2 seconds
     */
    public void remove() {
        if (System.currentTimeMillis() - whenAmIAway > 1000) {
            removeMe = true;
        }
    }

    public void turnTowords() {
        Player player = ((BaseWorld) getWorld()).getPlayer();
        int playerX = player.getX();
        int playerY = player.getY();

        if (allowToShoot) {
            turnTowards(playerX, playerY);
            allowToShoot = false;
        }
    }
}
