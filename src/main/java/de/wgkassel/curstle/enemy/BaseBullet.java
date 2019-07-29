package de.wgkassel.curstle.enemy;

import de.wgkassel.curstle.Worlds.Level1.BaseWorld;
import de.wgkassel.curstle.player.Player;
import greenfoot.Actor;

public class BaseBullet extends Actor {

    public int speed;
    private boolean hitPlayer = false;
    private long wait = System.currentTimeMillis();
    public boolean removeMe = false;
    public boolean pointAtPlayer = false;
    public boolean followPlayer = false;
    long time = System.currentTimeMillis();
    public int whenAmIAway = 1000;
    public boolean removeAfterTime = false;

    @Override
    public void act() {
        super.act();
        move(speed);
        checkRemove();
        checkPlayer();
        checkRemove();
        removeMe();

        if (followPlayer) {
            followPlayer();
        } else if (pointAtPlayer) {
            followPlayer();
            pointAtPlayer = false;
        }

        if (removeAfterTime) {
            removeAfterTime();
        }
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
            removeMe = true;
        }

    }

    public void removeMe() {
        if (removeMe) {
            getWorld().removeObject(this);
        }
    }

    public void followPlayer() {
        Player player = ((BaseWorld) getWorld()).getPlayer();
        int playerX = player.getX();
        int playerY = player.getY();
        turnTowards(playerX, playerY);
    }

    public void removeAfterTime() {
        if (System.currentTimeMillis() - time > whenAmIAway) {
            removeMe = true;
        }
    }
}