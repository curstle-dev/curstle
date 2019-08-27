package de.wgkassel.curstle.enemy;

import de.wgkassel.curstle.RoomContent.Shelf;
import de.wgkassel.curstle.RoomContent.Wall;
import de.wgkassel.curstle.Worlds.Level1.BaseWorld;
import de.wgkassel.curstle.player.MageShot;
import de.wgkassel.curstle.player.Player;

import java.util.List;

public class Bug extends BaseEnemy {

    private boolean moveVertical = false;
    private boolean moveHorizontal = false;
    private boolean deBounce = false;
    private int lastX;
    private int lastY;
    private int freeX;
    private int freeY;
    int BugX;
    int BugY;

    public Bug() {
        setImage("enemy.png");
        lives = 4;
    }


    /**
     * act method for MainEnemy
     */
    @Override
    public void act() {

        super.act();
        checkDirection();
        getLastCords();
        walk();
        getLastCords();


        if (deBounce) {
            deBounce = false;
            followThePlayer();
        }
        deBounce = true;
        if (isTouching(MageShot.class)) {
            lives--;
            lives--;
            removeTouching(MageShot.class);
            if (lives == 0) {
                die();
            }
            move(-50);
        }
    }

    public void getLastCords() {

        if (getIntersectingObjects(Bug.class).isEmpty()) {
            lastX = this.getX();
            lastY = this.getY();
        } else if (!getIntersectingObjects(Bug.class).isEmpty()) {
            if (moveHorizontal) {
                setLocation(BugX, lastY);
                lastX = this.getX();
                return;
            } else if (moveVertical) {
                setLocation(lastX, BugY);
                lastY = this.getY();
                return;
            }
            else {
                return;
            }
        }
    }

    /**
     * enemy moves forwards
     */
    public void walk() {
        if (getIntersectingObjects(Bug.class).isEmpty()) {
            List<Player> players = getObjectsInRange(600, Player.class);
            int nPlayers = players.size();
            if (getObjectsInRange(100, Player.class).isEmpty()) {


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
            BugX = this.getX();
            BugY = this.getY();
        }
        else{setLocation(lastX, lastY);}

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
        int deltaX = Math.abs(playerX - enemyX);
        int deltaY = Math.abs(playerY - enemyY);

        if (deltaX > deltaY) {
            moveHorizontal = true;
            moveVertical = false;
        } else if (deltaY > deltaX) {
            moveVertical = true;
            moveHorizontal = false;
        } else {
            moveHorizontal = false;
            moveVertical = false;
        }
    }
}
