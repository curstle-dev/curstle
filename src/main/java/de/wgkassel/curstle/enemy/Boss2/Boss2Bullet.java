package de.wgkassel.curstle.enemy.Boss2;

import de.wgkassel.curstle.enemy.BaseBullet;

public class Boss2Bullet extends BaseBullet {

    public Boss2Bullet() {
        setImage("Horn2.png");
        this.getImage().scale(146, 100);
        speed = 5;
    }
}
