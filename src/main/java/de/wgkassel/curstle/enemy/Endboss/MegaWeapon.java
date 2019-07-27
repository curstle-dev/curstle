package de.wgkassel.curstle.enemy.Endboss;

import de.wgkassel.curstle.Worlds.Level1.BaseWorld;
import de.wgkassel.curstle.enemy.BaseBullet;
import de.wgkassel.curstle.player.Player;
import greenfoot.Actor;

public class MegaWeapon extends BaseBullet {

    boolean allowToShoot = true;

    public MegaWeapon() {
        setImage("weaponoftheboss.png");
        this.getImage().scale(115, 60);
        speed = 6;


    }

    @Override
    public void act() {
        super.act();
        turnToword();
    }

    public void turnToword(){
        Player player = ((BaseWorld) getWorld()).getPlayer();
        int playerX = player.getX();
        int playerY = player.getY();
        if (allowToShoot) {
            turnTowards(playerX, playerY);
            allowToShoot = false;
        }
    }
}
