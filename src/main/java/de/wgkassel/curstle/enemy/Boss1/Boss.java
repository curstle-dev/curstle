package de.wgkassel.curstle.enemy.Boss1;

import de.wgkassel.curstle.Worlds.Level1.BaseWorld;
import greenfoot.Actor;


public class Boss extends Actor {


    private static final int COOLDOWN = -2000;

    private long lastTurn = System.currentTimeMillis(); //time of the last turn of the Boss
    private long shotPause = System.currentTimeMillis();
    private boolean shouldBeZigZagging = false;
    private boolean firstZigZag = false;
    private boolean firstWalk = false;
    private int turnCount = 0;
    private boolean firstZigZagDone = false;
    private BossImage image = new BossImage();
    private boolean whenCanIAttackThePlayer = false;
    private int attackCount = 0;
    private boolean attackDone = true;


    public static int lives = 30;
    private int walk = 1;
    private int ZigZag = 1;

    public Boss() {
        this.getImage().setTransparency(0);

    }


    @Override
    public void act() {
        super.act();

        if (attack() || !attackDone) {
            handleCooldown();
            return;
        }
        if (shouldBeZigZagging) {
            zigZag();
        } else {
            walk();
        }

        image.setLocation(this.getX(), this.getY());
        die();
    }

    public BossImage getImageActor() {
        return image;
    }

    private void handleCooldown() {
        if (lastTurn == 0L) {
            lastTurn = System.currentTimeMillis();
        } else if (lastTurn - System.currentTimeMillis() <= COOLDOWN) {
            attackDone = true;
            lastTurn = System.currentTimeMillis();
        }
    }

    /**
     * method looks if the Boss can attack the Player
     *
     *
     */
    private boolean attack() {
        if (whenCanIAttackThePlayer) {
            if (attackCount < 4) {
                attackThePlayer();
                attackDone = false;
            } else {
                whenCanIAttackThePlayer = false;
                attackCount = 0;
                this.setRotation(0);
                walk = 1;
            }
            return true;

        }
        return false;
    }

    /**
     * method looks if the Boss is in the startarea (quarter obenlinks)
     *
     *
     */
    private boolean isInStartArea() {
        return (this.getX() <= 250 && this.getX() >= 0)
                && (this.getY() <= 250 && this.getY() >= 0);
    }

    /**
     * Boss walks in a quarter
     */
    private void walk() {

        move(8);
        if (this.getY() < 500 && this.getX() > BaseWorld.WIDTH - 200 && walk == 1) {
            turn(90);
            walk++;

        } else if (this.getY() > BaseWorld.HEIGHT - 200 && this.getX() > BaseWorld.WIDTH - 200 && walk == 2) {
            turn(90);
            walk++;

        } else if (this.getY() > BaseWorld.HEIGHT - 200 && this.getX() < 200 && walk == 3) {
            turn(90);
            walk++;

        } else if (this.getY() < 200 && this.getX() < 200 && walk == 4) {
            turn(90);
            shouldBeZigZagging = true;
            firstZigZag = true;
            ZigZag = 1;

        }
    }

    /**
     * Boss walks in a zigzag pattern
     */
    private void zigZag() {

        if (isInStartArea() && firstZigZagDone) {
            shouldBeZigZagging = false;
            setRotation(25);
            lastTurn = System.currentTimeMillis();
            whenCanIAttackThePlayer = true;
            return;
        }
        if (firstZigZag && ZigZag == 1) {
            setRotation(70);
            firstZigZag = false;
            ZigZag++;
            return;
        }
        if (this.getY() > 790 && ZigZag == 2) {
            turn(-150);
            ZigZag++;
            return;

        } else if (this.getY() < 210 && ZigZag == 3) {
            turn(150);
            ZigZag++;
            return;
        }

        if (this.getY() > 790 && ZigZag == 4) {
            turn(-150);
            ZigZag++;
            return;

        } else if (this.getY() < 210 && ZigZag == 5) {
            turn(150);
            ZigZag++;
            return;
        }
        if (this.getY() > 790 && ZigZag == 6) {
            turn(-150);
            ZigZag++;
            return;

        } else if (this.getY() < 210 && ZigZag == 7) {
            turn(150);
            ZigZag++;
            return;
        }

        if (this.getY() > 790 && ZigZag == 8) {
            turn(-150);
            ZigZag++;
            return;

        } else if (this.getY() < 210 && ZigZag == 9) {
            turn(150);
            ZigZag++;
            return;
        }
        if (this.getX() > 1490 && ZigZag == 10) {
            setRotation(-157);
            ZigZag++;
            return;
        }
        if (this.getY() < 250 && ZigZag == 11) {
            setRotation(180);
            move(7);
            ZigZag = 1;
        }
        firstWalk = true;
        move(15);
        if (!isInStartArea()) {
            firstZigZagDone = true;
        }

    }


    /**
     * Boss generates a Weapon which makes damage to the Player
     */
    private void attackThePlayer() {
        if (System.currentTimeMillis() - shotPause > 2000) {
            doShoot(0);
            doShoot(18);
            doShoot(36);
            doShoot(54);
            doShoot(72);
            doShoot(90);
            attackCount++;
        }
    }

    private void doShoot(int rotationOffset) {
        WeaponOfTheBoss weapon = new WeaponOfTheBoss();
        this.getWorld().addObject(weapon, this.getX(), this.getY());
        weapon.setRotation(rotationOffset);
        shotPause = System.currentTimeMillis();
    }

    private boolean shouldTurn(int dMin, int dMax) {
        return this.shouldTurn(dMin, dMax, 2500);
    }

    private boolean shouldTurn(int dMin, int dMax, long sec) {
        return this.getRotation() >= dMin && getRotation() < dMax && System.currentTimeMillis() - lastTurn > sec;
    }


    /**
     * Boss dies
     */

    private void die() {
        if (lives <= 0) {
            getWorld().removeObject(image);
            getWorld().removeObject(this);
        }
    }


}
