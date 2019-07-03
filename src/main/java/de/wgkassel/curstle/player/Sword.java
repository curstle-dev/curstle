package de.wgkassel.curstle.player;

import de.wgkassel.curstle.Worlds.Level1.BaseWorld;
import de.wgkassel.curstle.enemy.*;
import de.wgkassel.curstle.enemy.Boss1.Boss;
import de.wgkassel.curstle.enemy.Boss1.BossImage;
import de.wgkassel.curstle.enemy.Endboss.Endboss;
import de.wgkassel.curstle.enemy.Endboss.EndbossImage;
import greenfoot.Actor;
import greenfoot.Greenfoot;

import java.util.List;

public class Sword extends Actor {

    static boolean allowHit = true;
    static boolean allowBossHit = true;
    static boolean allowEndbossHit = true;
    private int swordRange = 190;
    static boolean allowHitEnemy2 = true;

    public Sword() {
        setImage("sword.png");
        this.getImage().scale(150, 150);

    }

    @Override
    public void act() {
        super.act();
        checkEnemy();
        checkEnemy2();
        checkBoss();
        checkEndboss();
        followPlayer();
        resetAllowHits();

    }

    /**
     * resets allowHits
     */
    public void resetAllowHits() {
        if (!Greenfoot.isKeyDown("up") && !Greenfoot.isKeyDown("down") && !Greenfoot.isKeyDown("right") && !Greenfoot.isKeyDown("left")) {
            allowHit = true;
            allowBossHit = true;
            allowEndbossHit = true;
            allowHitEnemy2 = true;
        }
    }

    /**
     * checks if there is a Bug
     */
    public void checkEnemy() {
        List<MainEnemy> intersectingObjects = getObjectsInRange(swordRange, MainEnemy.class);
        if (allowHit) {
            if (intersectingObjects != null) {
                intersectingObjects.forEach(MainEnemy::lowerHealth);
                allowHit = false;
            }
        } else if (intersectingObjects == null) {
            allowHit = true;
        }
    }


    /**
     * checks if there is a BEEEEEEEE
     */
    public void checkEnemy2() {
        List<Enemy2> intersectingObjects = getObjectsInRange(swordRange, Enemy2.class);
        if (allowHitEnemy2) {
            if (intersectingObjects != null) {
                intersectingObjects.forEach(Enemy2::lowerHealth);
                allowHitEnemy2 = false;
            }
        } else if (intersectingObjects == null) {
            allowHitEnemy2 = true;
        }
    }

    /**
     * check the if there is a Boss
     */
    public void checkBoss() {
        if (!getObjectsInRange(swordRange, BossImage.class).isEmpty() && allowBossHit) {
            Boss.lives--;
            Greenfoot.playSound("hit.wav");
            allowBossHit = false;
        }
        }


    /**
     * check if theres an Endboss
     */
    public void checkEndboss() {
        if (!getObjectsInRange(swordRange, EndbossImage.class).isEmpty() && allowEndbossHit && !Endboss.invisible) {
            Endboss.live--;
            Greenfoot.playSound("hit.wav");
            allowEndbossHit = false;
        }
    }

    /**
     * Sword follows the player :o
     */
    public void followPlayer() {
        Player player = ((BaseWorld) getWorld()).getPlayer();
        int playerX = player.getX();
        int playerY = player.getY();

        switch (Knight.swordDirection) {
            case UP:
                setLocation(playerX, playerY - 100);
                this.setRotation(90);
                break;
            case DOWN:
                setLocation(playerX, playerY + 100);
                this.setRotation(175);
                break;
            case LEFT:
                setLocation(playerX - 100, playerY);
                this.setRotation(0);
                break;
            case RIGHT:
            default:
                setLocation(playerX + 100, playerY);
                this.setRotation(90);
                break;
        }


    }


}
