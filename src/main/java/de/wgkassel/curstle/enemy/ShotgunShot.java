package de.wgkassel.curstle.enemy;

public class ShotgunShot extends BaseBullet {

    public ShotgunShot(int x) {
        speed = x;
        removeAfterTime = true;
        whenAmIAway = 700;
    }
}
