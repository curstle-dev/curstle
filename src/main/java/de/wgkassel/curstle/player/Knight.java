package de.wgkassel.curstle.player;

import greenfoot.Greenfoot;


public class Knight extends Player {


    private Sword sword = new Sword();
    static Direction swordDirection = null;
    private Direction direction = null;
    int delay = 5;

    public Knight() {
        setImage("Knight.png");
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
        if (Greenfoot.isKeyDown("up")) {
            this.getWorld().addObject(sword, this.getX(), this.getY());
            swordDirection = Direction.UP;
            setImage("Knight_back.png");
            this.getImage().scale(91, 131);
            delay = 0;
        } else if (Greenfoot.isKeyDown("right")) {
            this.getWorld().addObject(sword, this.getX(), this.getY());
            swordDirection = Direction.RIGHT;
            setImage("Knight_right.png");
            this.getImage().scale(91, 131);
            delay = 0;
        } else if (Greenfoot.isKeyDown("down")) {
            this.getWorld().addObject(sword, this.getX(), this.getY());
            swordDirection = Direction.DOWN;
            setImage("Knight.png");
            this.getImage().scale(91, 131);
            delay = 0;
        } else if (Greenfoot.isKeyDown("left")) {
            this.getWorld().addObject(sword, this.getX(), this.getY());
            swordDirection = Direction.LEFT;
            setImage("Knight_left.png");
            this.getImage().scale(91, 131);
            delay = 0;
        }
    }

    /**
     * removes the sword and resets allowHits
     */
    public void cancelSword() {
        if (!Greenfoot.isKeyDown("up") && !Greenfoot.isKeyDown("right") && (!Greenfoot.isKeyDown("down")) && (!Greenfoot.isKeyDown("left"))) {
            getWorld().removeObject(sword);
            swordDirection = null;
            Sword.allowHit = true;
            Sword.allowBossHit = true;
            Sword.allowEndbossHit = true;
            Sword.allowHitEnemy2 = true;
            Sword.allowHitBoss2Enemy = true;


        }
    }

    /**
     * lets the player look in the direction hes walking
     */
    public void looking() {

        if (Greenfoot.isKeyDown("w")) {
            setImage("Knight_back.png");
            this.getImage().scale(91, 131);
        } else if (Greenfoot.isKeyDown("d")) {
            setImage("Knight_right.png");
            this.getImage().scale(91, 131);
        } else if (Greenfoot.isKeyDown("a")) {
            setImage("Knight_left.png");
            this.getImage().scale(91, 131);
        } else if (Greenfoot.isKeyDown("s")) {
            setImage("Knight.png");
            this.getImage().scale(91, 131);
        }
    }


}











