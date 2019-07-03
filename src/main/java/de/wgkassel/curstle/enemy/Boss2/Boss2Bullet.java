package de.wgkassel.curstle.enemy.Boss2;

import de.wgkassel.curstle.Worlds.Level1.BaseWorld;
import de.wgkassel.curstle.player.Player;
import greenfoot.Actor;

public class Boss2Bullet extends Actor {
    boolean followPlayer;

    public Boss2Bullet() {
    }

    @Override
    public void act() {
        super.act();
        aimAtPlayer();
        remove();
    }

    public void aimAtPlayer() {
        if (followPlayer) {
            Player player = ((BaseWorld) getWorld()).getPlayer();
            int playerX = player.getX();
            int playerY = player.getY();

            turnTowards(playerX, playerY);
        }
    }
    public void remove(){
        if (isAtEdge()){
            getWorld().removeObject(this);
        }
    }
}
