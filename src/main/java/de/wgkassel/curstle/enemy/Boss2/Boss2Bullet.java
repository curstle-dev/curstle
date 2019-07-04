package de.wgkassel.curstle.enemy.Boss2;

import greenfoot.Actor;

public class Boss2Bullet extends Actor {

    public Boss2Bullet() {
        setImage("Horn2.png");
        this.getImage().scale(146, 100);
    }

    @Override
    public void act() {
        super.act();
        move(5);
        remove();
    }

    public void remove(){
        if (isAtEdge()){
            getWorld().removeObject(this);
        }
    }
}
