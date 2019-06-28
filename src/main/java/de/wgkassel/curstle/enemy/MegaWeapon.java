package de.wgkassel.curstle.enemy;

import de.wgkassel.curstle.Worlds.Level1.BaseWorld;
import de.wgkassel.curstle.player.Player;
import greenfoot.Actor;

public class MegaWeapon extends Actor {

    boolean allowToShoot = true;

    public MegaWeapon() {
        setImage("weaponoftheboss.png");
        this.getImage().scale(115, 60);


    }

    @Override
    public void act() {
        super.act();
        move(6);
        Player player = ((BaseWorld) getWorld()).getPlayer();
        int playerX = player.getX();
        int playerY = player.getY();
        if (allowToShoot) {
            turnTowards(playerX, playerY);
            allowToShoot = false;
        }
        if (isAtEdge()) {
            getWorld().removeObject(this);
        }
    }
}
