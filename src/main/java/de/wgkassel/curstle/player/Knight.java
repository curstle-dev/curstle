package de.wgkassel.curstle.player;

import greenfoot.Greenfoot;

public class Knight extends Player {

    private Sword sword = new Sword();
    private SwordHit swordHit = new SwordHit();
    static Direction swordDirection = null;
    private int delay = 5;
    private boolean allowHit = true;
    private int walkAnimation = 0;
    private int dodgeCounter = 0;

    public Knight() {
        this.getImage().scale(91, 131);
    }

    @Override
    public void doAct() {
        if (delay >= 5) {
            attack();
        }
        cancelSword();
        looking();
        delay++;
    }


    /**
     * creates a sword in the direction as it been pushed
     */
    public void attack() {
        if (allowHit) {
            if (Greenfoot.isKeyDown("up")) {
                this.getWorld().addObject(sword, this.getX(), this.getY() - 100);
                swordDirection = Direction.UP;
                setImage("Knight_back.png");
                this.getImage().scale(91, 131);
                Sword.amIThere = true;
                this.getWorld().addObject(swordHit, this.getX(), this.getY() - 150);
                delay = 0;
            } else if (Greenfoot.isKeyDown("right")) {
                this.getWorld().addObject(sword, this.getX() + 100, this.getY());
                swordDirection = Direction.RIGHT;
                setImage("Knight_right.png");
                this.getImage().scale(91, 131);
                Sword.amIThere = true;
                this.getWorld().addObject(swordHit, this.getX() + 150, this.getY());
                delay = 0;
            } else if (Greenfoot.isKeyDown("down")) {
                this.getWorld().addObject(sword, this.getX(), this.getY() + 100);
                swordDirection = Direction.DOWN;
                setImage("Knight.png");
                this.getImage().scale(91, 131);
                Sword.amIThere = true;
                this.getWorld().addObject(swordHit, this.getX(), this.getY() + 150);
                delay = 0;
            } else if (Greenfoot.isKeyDown("left")) {
                this.getWorld().addObject(sword, this.getX() - 100, this.getY());
                swordDirection = Direction.LEFT;
                setImage("Knight_left.png");
                this.getImage().scale(91, 131);
                Sword.amIThere = true;
                this.getWorld().addObject(swordHit, this.getX() - 150, this.getY());
                delay = 0;
            }
        }
        allowHit = false;
    }

    /**
     * removes the sword and resets allowHits
     */
    private void cancelSword() {
        if (!Greenfoot.isKeyDown("up") && !Greenfoot.isKeyDown("right") && (!Greenfoot.isKeyDown("down")) && (!Greenfoot.isKeyDown("left"))) {
            allowHit = true;
            SwordHit.allowHit = true;
            SwordHit.allowBossHit = true;
            SwordHit.allowEndbossHit = true;
            SwordHit.allowHitEnemy2 = true;
            SwordHit.allowHitBoss2Enemy = true;
            SwordHit.allowHitBoss2 = true;
        }
    }

    /**
     * lets the player look in the direction hes walking
     */
    private void looking() {

        if (Greenfoot.isKeyDown("w")) {
            if (walkAnimation <= 5) {
                setImage("KnightWalkBack1.png");
                walkAnimation = walkAnimation + 1;
            } else if (walkAnimation <= 10) {
                setImage("KnightWalkBack2.png");
                walkAnimation = walkAnimation + 1;
            } else {
                walkAnimation = 0;
                dodgeCounter = 0;
            }
            this.getImage().scale(91, 131);
        } else if (Greenfoot.isKeyDown("d")) {
            if (walkAnimation <= 5) {
                setImage("KnightWalkRight1.png");
                walkAnimation = walkAnimation + 1;
            } else if (walkAnimation <= 10) {
                setImage("KnightWalkRight2.png");
                walkAnimation = walkAnimation + 1;
            } else {
                walkAnimation = 0;
                dodgeCounter = 0;
            }
            this.getImage().scale(91, 131);
        } else if (Greenfoot.isKeyDown("a")) {
            if (walkAnimation <= 5) {
                setImage("KnightWalkLeft1.png");
                walkAnimation = walkAnimation + 1;
            } else if (walkAnimation <= 10) {
                setImage("KnightWalkLeft2.png");
                walkAnimation = walkAnimation + 1;
            } else {
                walkAnimation = 0;
                dodgeCounter = 0;
            }
            this.getImage().scale(91, 131);
        } else if (Greenfoot.isKeyDown("s")) {
            if (walkAnimation <= 5) {
                setImage("KnightFrontWalk1.png");
                walkAnimation = walkAnimation + 1;
            } else if (walkAnimation <= 10) {
                setImage("KnightFrontWalk2.png");
                walkAnimation = walkAnimation + 1;
            } else {
                walkAnimation = 0;
                dodgeCounter = 0;
            }
            this.getImage().scale(91, 131);
        } else if (!upPressed && !downPressed && !leftPressed && !rightPressed && !Greenfoot.isKeyDown("right") && !Greenfoot.isKeyDown("left") && !Greenfoot.isKeyDown("up") && !Greenfoot.isKeyDown("down")) {
            dodgeCounter++;
                if(dodgeCounter <= 30) {
                    setImage("Knight.png");
                    this.getImage().scale(91, 131);
            }
            else{
                if (dodgeCounter > 50)
                {
                    dodgeCounter = 0;
                }
                setImage("Knight_dodge.png");
                this.getImage().scale(91, 131);
            }
        }

    }
}