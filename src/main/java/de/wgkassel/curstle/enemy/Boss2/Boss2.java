package de.wgkassel.curstle.enemy.Boss2;

import de.wgkassel.curstle.Worlds.Level1.BaseWorld;
import de.wgkassel.curstle.enemy.Endboss.EndbossWeapon;
import de.wgkassel.curstle.enemy.Enemy2;
import de.wgkassel.curstle.player.Player;
import greenfoot.Actor;
import greenfoot.Greenfoot;

public class Boss2 extends Actor {
    public static int lives = 40;

    private boolean allowAttack = true;
    private long shotPause = System.currentTimeMillis();
    private long wait = System.currentTimeMillis();
    private long pause = System.currentTimeMillis();
    private int countAim = 0;
    private int countAround = 0;
    State state = State.SPAWN;
    LastState lastState = LastState.SPAWN;

    enum State {AIM, AROUND, SPAWN, PAUSE, NOTHING;}

    enum LastState {AIM, AROUND, SPAWN, PAUSE;}


    public Boss2() {
        setImage("Boss2.png");
        this.getImage().scale(280, 232);

    }

    @Override
    public void act() {
        super.act();
        attack();
        die();
    }

    public void attack() {
        switch (state) {

            case PAUSE:
            default:
                longPause();
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
            case NOTHING:
                pause();

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

        } else if (countAim >= 6) {
            countAim = 0;
            lastState = LastState.AIM;
            pause = System.currentTimeMillis();
            pause();
        }
    }


    public void shootAround() {
        int x = 0;
        if (System.currentTimeMillis() - shotPause > 2000 && countAround < 6) {
            for (int i = 0; i <= 15; i++) {
                generateBullet(x);
                x = x + 24;
            }
            countAround++;
        } else if (countAround >= 6) {
            lastState = LastState.AROUND;
            pause = System.currentTimeMillis();
            pause();
        }
    }

    public void generateBullet(int rotationOffset) {
        Boss2Bullet boss2Bullet = new Boss2Bullet();
        this.getWorld().addObject(boss2Bullet, this.getX() + 90, this.getY() + 10);
        int rotation = boss2Bullet.getRotation();
        boss2Bullet.setRotation(rotation + rotationOffset);
        shotPause = System.currentTimeMillis();
    }

    public void spawnEnemy() {

        for (int i = 0; i < 3; i++) {
            Boss2Enemy boss2Enemy = new Boss2Enemy();
            getWorld().addObject(boss2Enemy, getRandomX(), getRandomY());
        }

        pause = System.currentTimeMillis();
        lastState = LastState.SPAWN;
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
        state = State.NOTHING;
        if (System.currentTimeMillis() - pause > 3000) {
            switch (lastState) {
                case SPAWN:
                default:
                    state = State.PAUSE;
                    break;
                case PAUSE:
                    state = State.AIM;
                    break;
                case AIM:
                    state = State.AROUND;
                    break;
                case AROUND:
                    state = State.SPAWN;
                    break;

            }

        }
    }

    public void longPause() {
        if (System.currentTimeMillis() - pause > 10000) {
            lastState = LastState.PAUSE;
            pause();
        }
    }
    public void die(){
        if (lives <= 0){
            getWorld().removeObject(this);
        }
    }
}
