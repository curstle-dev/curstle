package de.wgkassel.curstle.enemy;

import de.wgkassel.curstle.player.Player;
import greenfoot.Actor;

public class BaseBullet extends Actor {

    private int speed;
    private boolean hitPlayer;
    long wait = System.currentTimeMillis();

    @Override
    public void act() {
        super.act();
        move(speed);
        checkPlayer();
        checkRemove();
    }

    public void checkPlayer(){
       if(!getIntersectingObjects(Player.class).isEmpty()){
           hitPlayer = true;
       }
    }

    public void checkRemove(){
        if (isAtEdge() ) {
            getWorld().removeObject(this);
        }
        if (hitPlayer){
            wait = System.currentTimeMillis();
            if (System.currentTimeMillis() - wait > 500) {
                getWorld().removeObject(this);
            }
        }
    }
}
