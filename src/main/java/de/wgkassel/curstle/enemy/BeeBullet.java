package de.wgkassel.curstle.enemy;

public class BeeBullet extends BaseBullet {


    public BeeBullet() {
        setImage("beeBullet2.png");
        this.getImage().scale(24, 3);
        speed = 5;
        pointAtPlayer = true;
        removeAfterTime = true;
        whenAmIAway = 1000;
    }
}
