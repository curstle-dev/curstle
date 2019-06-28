package de.wgkassel.curstle.enemy;

import de.wgkassel.curstle.Worlds.Level1.BaseWorld;
import de.wgkassel.curstle.player.Player;
import greenfoot.Actor;

public class BeeBullet extends Actor {


    long whenAmIAway = System.currentTimeMillis();
    boolean REMOVE = false;
    boolean allowToShoot = true;

    public BeeBullet() {

        /**
         * set the image of the BeeBullet and scales it
         */
        setImage("beeBullet2.png");
        this.getImage().scale(24, 3);
    }

    /**
     * do what a BeeBullet does
     */
    @Override
    public void act() {
        super.act();
        move(5);
        Player player = ((BaseWorld) getWorld()).getPlayer();
        int playerX = player.getX();
        int playerY = player.getY();

        if (allowToShoot) {
            turnTowards(playerX, playerY);
            allowToShoot = false;
        }


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
