package de.wgkassel.curstle.player;

import de.wgkassel.curstle.Worlds.Level1.BaseWorld;
import de.wgkassel.curstle.enemy.*;
import de.wgkassel.curstle.enemy.Boss1.Boss;
import de.wgkassel.curstle.enemy.Boss1.BossImage;
import de.wgkassel.curstle.enemy.Boss2.Boss2;
import de.wgkassel.curstle.enemy.Boss2.Boss2Enemy;
import de.wgkassel.curstle.enemy.Endboss.Endboss;
import de.wgkassel.curstle.enemy.Endboss.EndbossImage;
import greenfoot.Actor;
import greenfoot.Greenfoot;

import java.util.List;

import static de.wgkassel.curstle.player.Direction.LEFT;
import static de.wgkassel.curstle.player.Direction.UP;

public class Sword extends Actor {




    int startRotation;
    int x;
    static boolean amIThere = true;
    public static int swordX;
    public static int swordY;

    public Sword() {
        setImage("Sword.png");
        this.getImage().scale(190, 190);
    }

    @Override
    public void act() {
        super.act();
        followPlayer();
        animation();

    }

    public void animation() {
        setRotation(startRotation + x);
        if (Knight.swordDirection == UP || Knight.swordDirection == LEFT) {
            x = x - 9;
            if (x < -90) {
                x = 0;
                amIThere = false;
                getWorld().removeObject(this);
            }
        } else {
            x = x + 9;
            if (getRotation() > startRotation + 90) {
                x = 0;
                amIThere = false;
                getImage().setTransparency(255);
                getWorld().removeObject(this);
            }
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
                swordX = this.getX();
                swordY = this.getY();
                this.setRotation(90);
                startRotation = 90;
                getImage().setTransparency(0);
                break;
            case DOWN:
                getImage().setTransparency(255);
                setLocation(playerX + 30, playerY + 40);
                swordX = this.getX();
                swordY = this.getY();
                this.setRotation(90);
                startRotation = 90;
                break;
            case LEFT:
                getImage().setTransparency(255);
                setLocation(playerX - 37, playerY + 28);
                swordX = this.getX();
                swordY = this.getY();
                this.setRotation(270);
                startRotation = 270;
                break;
            case RIGHT:
            default:
                getImage().setTransparency(255);
                setLocation(playerX + 37, playerY  + 28);
                swordX = this.getX();
                swordY = this.getY();
                this.setRotation(0);
                startRotation =  0;
                break;
        }


    }


}
