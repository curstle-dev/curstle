package de.wgkassel.curstle.enemy;

import de.wgkassel.curstle.RoomContent.Shelf;
import de.wgkassel.curstle.RoomContent.Wall;
import de.wgkassel.curstle.player.MageShot;
import de.wgkassel.curstle.player.Player;
import greenfoot.Greenfoot;

import java.util.List;

public class Enemy2 extends BaseEnemy {

    private State state = State.WALK;
    boolean canIAttack = true;
    long waitForAttack = System.currentTimeMillis();
    int lives = 1;
    boolean hit;

    public Enemy2() {
        setImage("bee2.png");
        this.getImage().scale(87, 90);
    }

    private enum State {
        WALK, ATTACK;
    }

    @Override
    public void act() {
        super.act();

        handleStateUpdates();

        switch (state) {
            case WALK:
                walk();
                break;
            case ATTACK:
                attackThePlayer();
                break;
        }
        checkAttack();
        if (isTouching(MageShot.class)) {
            die();
        }

    }

    private void handleStateUpdates() {
        List<Player> players = getObjectsInRange(600, Player.class);
        int nPlayers = players.size();
        if (nPlayers >= 1) {
            state = State.ATTACK;
        } else {
            state = State.WALK;
        }
    }

    /**
     * shoots at the player if the player is in range
     */
    public void attackThePlayer() {
        if (canIAttack) {
            BeeBullet beeBullet = new BeeBullet();
            getWorld().addObject(beeBullet, this.getX(), this.getY());
            beeBullet.getRotation();
            waitForAttack = System.currentTimeMillis();
            beeBullet.whenAmIAway = System.currentTimeMillis();
            canIAttack = false;
        }
    }

    /**
     * walks randomly around
     */
    public void walk() {
        move(3);

        if (Greenfoot.getRandomNumber(100) > 96) {
            int random = Greenfoot.getRandomNumber(360);
            turn(random);
        }
        if (isTouching(Wall.class)) {
            turn(180);
            move(10);
        }
        if (isTouching(Shelf.class)) {
            turn(180);
            move(10);
        }
    }

    /**
     * check if the Bee can shoot
     */
    public void checkAttack() {
        if (System.currentTimeMillis() - waitForAttack > 2000) {
            canIAttack = true;
        }
    }

    /**
     * lowers Health
     */
    public void lowerHealth() {
        Greenfoot.playSound("hit.wav");
        lives--;
        move(-100);
        hit = true;
        if (lives <= 0) {
            die();
        }
    }

}
