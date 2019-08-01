package de.wgkassel.curstle.enemy;

import de.wgkassel.curstle.player.MageShot;
import de.wgkassel.curstle.player.Player;
import greenfoot.Greenfoot;

import java.util.List;

public class Enemy2 extends BaseEnemy {

    private State state = State.WALK;
    private boolean canIAttack = true;
    private long waitForAttack = System.currentTimeMillis();
    private int lives = 1;

    public Enemy2() {
        setImage("bee2.png");
        this.getImage().scale(87, 90);
    }

    private enum State {WALK, ATTACK}

    @Override
    public void act() {
        super.act();

        handleStateUpdates();

        switch (state) {
            case WALK:
                walkRandomly();
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
    private void attackThePlayer() {
        if (canIAttack) {
            BeeBullet beeBullet = new BeeBullet();
            getWorld().addObject(beeBullet, this.getX(), this.getY());
            beeBullet.getRotation();
            waitForAttack = System.currentTimeMillis();
            beeBullet.time = System.currentTimeMillis();
            canIAttack = false;
        }
    }


    /**
     * check if the Bee can shoot
     */
    private void checkAttack() {
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
        if (lives <= 0) {
            die();
        }
    }

}
