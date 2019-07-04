package de.wgkassel.curstle.enemy.Boss2;

import de.wgkassel.curstle.RoomContent.Shelf;
import de.wgkassel.curstle.RoomContent.Wall;
import de.wgkassel.curstle.Worlds.Level1.BaseWorld;
import de.wgkassel.curstle.player.MageShot;
import de.wgkassel.curstle.player.Player;
import greenfoot.Actor;
import greenfoot.Greenfoot;

import java.util.List;

public class Boss2Enemy extends Actor {

    boolean deBounce = false;
    int lives = 4;
    boolean hit;
    public Boss2Enemy(){
        setImage("Guard.png");
    }

    public Boss2Enemy() {
        setImage("Boss2Enemy.png");
        this.getImage().scale(140, 116);
    }

    /**
     * act method for MainEnemy
     */
    @Override
    public void act() {

        super.act();


        if (deBounce) {

            deBounce = false;
            followThePlayer();
        }
        deBounce = true;
        walk();
        if (isTouching(MageShot.class)) {
            lives--;
            lives--;
            removeTouching(MageShot.class);
            hit = true;
            if (lives == 0) {
                getWorld().removeObject(this);
            }
            move(-50);
        }
    }

    /**
     * Health for all Enemies
     */
    public void lowerHealth() {
        Greenfoot.playSound("hit.wav");
        lives--;
        move(-100);
        hit = true;
        if (lives == 0) {
            getWorld().removeObject(this);
        }
    }

    /**
     * enemy moves forwards
     */
    public void walk() {
        List<Player> players = getObjectsInRange(600, Player.class);
        int nPlayers = players.size();
        if (nPlayers >= 1) {
            move(3);
        }
        if (isTouching(Shelf.class)) {
            turn(180);
            move(3);
        }
        if (isTouching(Wall.class)) {
            turn(180);
            move(3);
        }

    }


    /**
     * Enemy follows the Player
     */
    public void followThePlayer() {
        List<Player> players = getObjectsInRange(900, Player.class);
        int nPlayers = players.size();
        if (nPlayers >= 1) {
            Player player = ((BaseWorld) getWorld()).getPlayer();
            int playerX = player.getX();
            int playerY = player.getY();
            turnTowards(playerX, playerY);
        }
    }



}

