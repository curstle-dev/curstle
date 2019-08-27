package de.wgkassel.curstle.enemy.Endboss;

import de.wgkassel.curstle.enemy.BaseBullet;

public class MegaWeapon extends BaseBullet {

    public MegaWeapon() {
        setImage("weaponoftheboss.png");
        this.getImage().scale(115, 60);
        speed = 6;
        pointAtPlayer = true;
    }
}
