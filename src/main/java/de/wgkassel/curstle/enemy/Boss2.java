package de.wgkassel.curstle.enemy;

import de.wgkassel.curstle.Worlds.Level1.BaseWorld;
import de.wgkassel.curstle.player.Player;
import greenfoot.Actor;
import greenfoot.Greenfoot;

public class Boss2 extends Actor {
    private boolean allowAttack = true;
    private long shotPause = System.currentTimeMillis();
    private long wait = System.currentTimeMillis();
    private long pause = System.currentTimeMillis();
    private int countAim = 0;
    private int countAround = 0;
    State state = State.SPAWN;

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
                delayAttack();
                break;
            case AROUND:
                shootAround();
                break;
            case SPAWN:
                spawnEnemy();
                break;

        }

    }

    public void delayAttack() {
        if (System.currentTimeMillis() - wait > 2000) {
            allowAttack = true;
        }
    }


    public void shootAtPlayer() {
        if (allowAttack && countAim < 6) {
            Boss2FollowBullet boss2FollowBullet = new Boss2FollowBullet();
            getWorld().addObject(boss2FollowBullet, this.getX(), this.getY());
            wait = System.currentTimeMillis();
            allowAttack = false;
            countAim++;

        }
        else if (countAim >= 6){
            countAim = 0;
            state = State.AROUND;
        }
    }


    public void shootAround() {
        int x = 0;
        if (System.currentTimeMillis() - shotPause > 2000) {
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
        for (int i = 0; i < 3; i++) {
            Bug bug = new Bug();
            getWorld().addObject(bug, getRandomX(), getRandomY());
        }
        state = State.PAUSE;
    }

    /**
     * creates a random X coordinate thats not in the player or near him
     *
     * @return
     */
    private int getRandomX() {
        Player player = ((BaseWorld) getWorld()).getPlayer();
        int playerX = player.getX();
        int x;
        do {
            x = Greenfoot.getRandomNumber(getWorld().getWidth() - (170 * 2)) + 170;
        } while (x > playerX - 100 && x < playerX + 100);
        return x;
    }

    /**
     * creates a random Y coordinate thats not on the player or near him
     *
     * @return
     */
    private int getRandomY() {
        Player player = ((BaseWorld) getWorld()).getPlayer();
        int playerY = player.getY();
        int y;
        do {
            y = Greenfoot.getRandomNumber(getWorld().getHeight() - (170 * 2)) + 170;
        } while (y > playerY - 100 && y < playerY + 100);
        return y;
    }

    public void pause() {
        if(System.currentTimeMillis() - pause > 5000){
            state = State.AIM;
        }
    }
}
