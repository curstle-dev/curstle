package de.wgkassel.curstle.enemy;

import de.wgkassel.curstle.Worlds.Level1.BaseWorld;
import de.wgkassel.curstle.player.Player;
import greenfoot.Actor;

public class Boss2FollowBullet extends Actor {

    boolean allowFollow = true;

    @Override
    public void act() {
        super.act();
        move(5);

        Player player = ((BaseWorld) getWorld()).getPlayer();
        int playerX = player.getX();
        int playerY = player.getY();

        if (allowFollow) {
            turnTowards(playerX, playerY);
            allowFollow = false;
        }
    }
}
