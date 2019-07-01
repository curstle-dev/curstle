package de.wgkassel.curstle.enemy;

import greenfoot.Actor;

public class Boss2 extends Actor {
    private long shotPause = System.currentTimeMillis();
    State state = State.AROUND;

    enum State {AIM, AROUND, SPAWN, PAUSE;}


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
        Boss2FollowBullet boss2FollowBullet = new Boss2FollowBullet();
        getWorld().addObject(boss2FollowBullet, this.getX(), this.getY());
    }

    public void shootAround() {
        int x = 0;
        if (System.currentTimeMillis() - shotPause > 500) {
            for (int i = 0; i <= 15; i++) {
                generateBullet(x);
                x = x + 24;
            }
        }
    }

    public void generateBullet(int rotationOffset) {
        EndbossWeapon endbossWeapon = new EndbossWeapon();
        this.getWorld().addObject(endbossWeapon, this.getX() + 90, this.getY() + 10);
        int rotation = endbossWeapon.getRotation();
        endbossWeapon.setRotation(rotation + rotationOffset);
        shotPause = System.currentTimeMillis();
    }

    public void spawnEnemy() {

    }

    public void pause() {

    }
}
