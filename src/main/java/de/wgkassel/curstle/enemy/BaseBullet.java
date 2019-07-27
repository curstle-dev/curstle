package de.wgkassel.curstle.enemy;

import de.wgkassel.curstle.player.Player;
import greenfoot.Actor;

public class BaseBullet extends Actor {

    public int speed;
    private boolean hitPlayer = false;
    long wait = System.currentTimeMillis();
    public boolean removeMe = false;

    @Override
    public void act() {
        super.act();
        move(speed);
        checkRemove();
        checkPlayer();
        checkRemove();
        removeMe();
    }

    public void checkPlayer() {
        if (!hitPlayer && !getIntersectingObjects(Player.class).isEmpty()) {
            hitPlayer = true;
            wait = System.currentTimeMillis();
        }
    }

    public void checkRemove() {
        if (hitPlayer) {
            if (System.currentTimeMillis() - wait > 10) {
                removeMe = true;
            }
        } else if (isAtEdge()) {
            getWorld().removeObject(this);
            removeMe = true;
        }

    }

    public void removeMe(){
        if (removeMe){
            getWorld().removeObject(this);
        }
    }
}
