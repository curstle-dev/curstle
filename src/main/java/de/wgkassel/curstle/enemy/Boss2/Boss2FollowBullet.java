package de.wgkassel.curstle.enemy.Boss2;

import de.wgkassel.curstle.Worlds.Level1.BaseWorld;
import de.wgkassel.curstle.player.Player;
import greenfoot.Actor;

public class Boss2FollowBullet extends Actor {

    boolean allowFollow = true;

    public Boss2FollowBullet() {
        setImage("Horn2.png");
        this.getImage().scale(146, 100);
    }

    @Override
    public void act() {
        super.act();
        move(5);

        followPlayer();
        remove();
    }

    public void followPlayer() {
        Player player = ((BaseWorld) getWorld()).getPlayer();
        int playerX = player.getX();
        int playerY = player.getY();

        if (allowFollow) {
            turnTowards(playerX, playerY);
            allowFollow = false;
        }
    }

    public void remove() {
        if (isAtEdge()) {
            getWorld().removeObject(this);
        }
    }
}
