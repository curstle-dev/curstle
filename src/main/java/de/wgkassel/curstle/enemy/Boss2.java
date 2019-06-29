package de.wgkassel.curstle.enemy;

import greenfoot.Actor;

public class Boss2 extends Actor {
    State state = State.PAUSE;

    enum State {
        AIM, AROUND, SPAWN, PAUSE;
    }

    public Boss2() {
        setImage("placeholder_enemy.png");
    }

    @Override
    public void act() {
        super.act();
        attack();
    }

    public void attack() {
        switch (state) {

            case PAUSE:
                default:
                pause();
                break;
            case AIM:
                shootAtPlayer();
                break;
            case AROUND:
                shootAround();
                break;
            case SPAWN:
                spawnEnemy();
                break;

        }

    }

    public void shootAtPlayer() {

    }

    public void shootAround() {

    }

    public void spawnEnemy() {

    }

    public void pause() {

    }
}
