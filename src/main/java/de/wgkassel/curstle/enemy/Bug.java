package de.wgkassel.curstle.enemy;

import de.wgkassel.curstle.RoomContent.Shelf;
import de.wgkassel.curstle.RoomContent.Wall;
import de.wgkassel.curstle.Worlds.Level1.BaseWorld;
import de.wgkassel.curstle.player.MageShot;
import de.wgkassel.curstle.player.Player;
import greenfoot.Greenfoot;

import java.util.List;

public class Bug extends BaseEnemy {

    private boolean moveVertical = false;
    private boolean moveHorizontal = false;
    private boolean deBounce = false;
    private int lives = 4;
    boolean hit;
    private int lastX;
    private int lastY;

    public Bug() {
        setImage("enemy.png");
    }





    /**
     * act method for MainEnemy
     */
    @Override
    public void act() {

        super.act();
        getLastCords();
        checkDirection();

        if (deBounce) {

            deBounce = false;
            followThePlayer();
        }
        deBounce = true;
        if (isTouching(MageShot.class)) {
            lives--;
            lives--;
            removeTouching(MageShot.class);
            hit = true;
            if (lives == 0) {
                die();
            }
            move(-50);
        }
        walk();
    }

    public void getLastCords(){
        if(getObjectsInRange(100, Bug.class).isEmpty()) {
            lastX = this.getX();
            lastY = this.getY();
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
            die();
        }
    }

    /**
     * enemy moves forwards
     */
    public void walk() {
        List<Player> players = getObjectsInRange(600, Player.class);
        int nPlayers = players.size();
        if (getObjectsInRange(100, Player.class).isEmpty()) {
            if (!getObjectsInRange(200, Bug.class).isEmpty()) {
                if (moveHorizontal){
                    setLocation(getX(), lastY);
                }
                else if (moveVertical){
                    setLocation(lastX, getY());
                }
            }

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

    public void checkDirection() {
        Player player = ((BaseWorld) getWorld()).getPlayer();
        int playerX = player.getX();
        int playerY = player.getY();
        int enemyX = this.getX();
        int enemyY = this.getY();
        int deltaX = playerX - enemyX;
        int deltaY = playerY - enemyY;

        if (deltaX > deltaY) {
            moveHorizontal = true;
        } else if(deltaY > deltaX){
            moveVertical = true;
        }
        else {
            moveHorizontal = false;
            moveVertical = false;
        }
    }
}
