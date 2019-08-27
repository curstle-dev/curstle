package de.wgkassel.curstle.player;

import de.wgkassel.curstle.enemy.BaseEnemy;
import de.wgkassel.curstle.enemy.Boss1.Boss;
import de.wgkassel.curstle.enemy.Boss1.BossImage;
import de.wgkassel.curstle.enemy.Boss2.Boss2;
import de.wgkassel.curstle.enemy.Boss2.Boss2Enemy;
import de.wgkassel.curstle.enemy.Endboss.Endboss;
import de.wgkassel.curstle.enemy.Endboss.EndbossImage;
import greenfoot.Actor;
import greenfoot.Greenfoot;

import java.util.List;

public class SwordHit extends Actor {
    private int offset = 100;
    static boolean allowHit = true;
    static boolean allowHitBoss2Enemy = true;
    static boolean allowBossHit = true;
    static boolean allowHitBoss2 = true;
    static boolean allowEndbossHit = true;
    static boolean allowHitEnemy2 = true;
    private int swordRange = 100;

    public SwordHit() {
        setImage("swordHit.png");
        getImage().scale(100, 100);
    }

    @Override
    public void act() {
        super.act();
        checkEnemy();
        checkBoss2Enemy();
        checkBoss();
        checkBoss2();
        checkEndboss();
        removeMe();
        followPlayer();
    }

    public void removeMe() {
        if (!Sword.amIThere)
            getWorld().removeObject(this);
    }

    public void followPlayer() {
        switch (Knight.swordDirection) {
            case UP:
                setLocation(Sword.swordX, Sword.swordY - offset + 60);
                this.setRotation(270);
                break;
            case DOWN:
                setLocation(Sword.swordX, Sword.swordY + offset);
                this.setRotation(90);
                break;
            case LEFT:
                setLocation(Sword.swordX - offset, Sword.swordY);
                this.setRotation(180);
                break;
            case RIGHT:
            default:
                setLocation(Sword.swordX + offset, Sword.swordY);
                this.setRotation(0);
                break;
        }
    }

    /**
     * checks if there is a Bug
     */
    public void checkEnemy() {
        List<BaseEnemy> intersectingObjects = getObjectsInRange(swordRange, BaseEnemy.class);
        if (allowHit) {
            if (intersectingObjects != null) {
                intersectingObjects.forEach(BaseEnemy::lowerHealth);
                allowHit = false;
            }
        } else if (intersectingObjects == null) {
            allowHit = true;
        }
    }

    /**
     * checks if there is a Boss2Enemy
     */
    public void checkBoss2Enemy() {
        List<Boss2Enemy> intersectingObjects = getObjectsInRange(swordRange, Boss2Enemy.class);
        if (allowHitBoss2Enemy) {
            if (intersectingObjects != null) {
                intersectingObjects.forEach(Boss2Enemy::lowerHealth);
                allowHitBoss2Enemy = false;
            }
        } else if (intersectingObjects == null) {
            allowHitBoss2Enemy = true;
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
     * check the if there is a Boss2
     */
    public void checkBoss2() {
        if (!getObjectsInRange(swordRange, Boss2.class).isEmpty() && allowHitBoss2) {
            Boss2.lives--;
            Greenfoot.playSound("hit.wav");
            allowHitBoss2 = false;
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
}
