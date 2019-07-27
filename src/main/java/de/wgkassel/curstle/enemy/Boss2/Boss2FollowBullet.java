package de.wgkassel.curstle.enemy.Boss2;

import de.wgkassel.curstle.Worlds.Level1.BaseWorld;
import de.wgkassel.curstle.enemy.BaseBullet;
import de.wgkassel.curstle.player.Player;
import greenfoot.Actor;

public class Boss2FollowBullet extends BaseBullet {

    boolean allowFollow = true;

    public Boss2FollowBullet() {
        setImage("Horn2.png");
        this.getImage().scale(146, 100);
        speed = 5;
    }

    @Override
    public void act() {
        super.act();
        followPlayer();
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

}
