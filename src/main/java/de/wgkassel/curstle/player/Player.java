package de.wgkassel.curstle.player;

import de.wgkassel.curstle.Worlds.EndScreen.Endscreen;
import de.wgkassel.curstle.RoomContent.DecoKnight;
import de.wgkassel.curstle.RoomContent.Shelf;
import de.wgkassel.curstle.RoomContent.Wall;
import de.wgkassel.curstle.Worlds.VictoryScreen.VictoryScreen;
import de.wgkassel.curstle.Worlds.Level1.*;
import de.wgkassel.curstle.Worlds.Level2.*;
import de.wgkassel.curstle.enemy.*;
import de.wgkassel.curstle.enemy.Boss1.Boss;
import de.wgkassel.curstle.enemy.Boss2.Boss2;
import de.wgkassel.curstle.enemy.Boss2.Boss2Enemy;
import de.wgkassel.curstle.items.*;
import greenfoot.Actor;
import greenfoot.Greenfoot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Player extends Actor {

    //variables for checkHit
    private long time = System.currentTimeMillis();
    private Class checkHit;

    //lives of the Player
    public static int lives = 10;

    //variables for the walking delay
    public static final double DEFAULT_MULTIPLIER = 0.001;
    private double multiplier = DEFAULT_MULTIPLIER;
    private int mac /*multiplier acceleration count*/ = 0;
    private int mbc /*multiplier braking count*/ = 0;
    private int d = 7;//diagonal walking speed
    private int s = 7;//straight walking speed
    private Direction direction = null;
    private boolean moving;

    //general movement variables and collision
    public boolean upPressed;
    public boolean leftPressed;
    public boolean downPressed;
    public boolean rightPressed;
    private int wallSize = 145;
    private int lastX;
    private int lastY;
    private Class touchingClass;

    //generates hash map
    private HashMap<Class<? extends BaseWorld>, HashMap<Class<? extends BaseEnemy>, Integer>> levelmap = new HashMap<>();
    private HashMap<Class<? extends BaseWorld>, HashMap<Class<? extends BaseItem>, Integer>> itemsMap = new HashMap<>();


    /**
     * Constructor
     */
    public Player() {
        initEnemyMap();
    }


    /**
     * Act
     */
    @Override
    public final void act() {
        super.act();
        checkWASD();
        checkHits();
        checkMove();
        doAct();
        cheat();
    }


    /**
     * checks if anything can harm the player
     */
    public void checkHits() {
        for (int i = 0; i < 6; i++) {
            switch (i) {
                case 1:
                    checkHit = BaseEnemy.class;
                    break;
                case 2:
                    checkHit = Boss.class;
                    break;
                case 4:
                    checkHit = Boss2Enemy.class;
                    break;
                case 5:
                    checkHit = BaseBullet.class;
                    break;
            }
            if (!getIntersectingObjects(checkHit).isEmpty()) {
                if (System.currentTimeMillis() - time > 1000) {
                    decreaseHealth();
                    time = System.currentTimeMillis();
                }
            }
        }
    }


    public abstract void doAct();

    /**
     * decreases lives
     */
    public void decreaseHealth() {
        lives = lives - 1;
        if (lives <= 0) {
            die();
        }
    }

    public void cheat() {
        if (Greenfoot.isKeyDown("q")) {
            lives = 10;
        }
    }


    /**
     * Player dies
     */
    public void die() {
        Endscreen endscreen = new Endscreen(((BaseWorld) getWorld()).getPlayer());
        Greenfoot.setWorld(endscreen);
    }


    /**
     * Everything about moving
     */
    public void checkWASD() {
        upPressed = Greenfoot.isKeyDown("w");
        leftPressed = Greenfoot.isKeyDown("a");
        downPressed = Greenfoot.isKeyDown("s");
        rightPressed = Greenfoot.isKeyDown("d");
    }

    public void checkMove() {
        if (canMove()) {
            if (!isTouching(Shelf.class) && !isTouching(DecoKnight.class) && !isTouching(Boss2.class)) {
                lastX = getX();
                lastY = getY();
            }
            touchObjects();
            accelMove();
            brakeMove();
            walk();
        }
    }

    public int getWalkingSpeed(int actual) {
        //Berechnet die Geschwindigkeit aus der jeweiligen Maximalgeschwindigkeit. Dies sorgt für eine gewisse Trägheit beim Bewegen des Charakters.
        return (int) (actual * multiplier);
    }

    private void accelMove() {
        mac++;
        if (mac >= 7 && multiplier <= 1 && (upPressed || downPressed || leftPressed || rightPressed)) {
            multiplier = multiplier + 0.33;
            mac = 0;
        }
        multiplier = 1;
    }

    private void walk() {
        int x = getX();
        int y = getY();

        if (upPressed && !downPressed) {
            if (leftPressed) {
                setLocation(x - getWalkingSpeed(d), y - getWalkingSpeed(d)); //Moving left and up
                moving = true;
                direction = Direction.getCurrent();
                return;
            } else if (rightPressed) {
                setLocation(x + getWalkingSpeed(d), y - getWalkingSpeed(d)); //Moving right and up
                moving = true;
                direction = Direction.getCurrent();
                return;
            } else {
                setLocation(x, y - getWalkingSpeed(s)); //Moving up
                moving = true;
                direction = Direction.getCurrent();
                return;
            }
        } else if (downPressed && !upPressed) {
            if (leftPressed) {
                setLocation(x - getWalkingSpeed(d), y + getWalkingSpeed(d)); //Moving left and down
                moving = true;
                direction = Direction.getCurrent();
                return;
            } else if (rightPressed) {
                setLocation(x + getWalkingSpeed(d), y + getWalkingSpeed(d)); //Moving right and down
                moving = true;
                direction = Direction.getCurrent();
                return;
            } else {
                setLocation(x, y + getWalkingSpeed(s)); //Moving down
                moving = true;
                direction = Direction.getCurrent();
                return;
            }
        }


        if (rightPressed && !leftPressed) {
            setLocation(x + getWalkingSpeed(s), y); //Moving right
            moving = true;
            direction = Direction.getCurrent();
        } else if (leftPressed && !rightPressed) {
            setLocation(x - getWalkingSpeed(s), y); //Moving left
            moving = true;
            direction = Direction.getCurrent();
        }

    }

    public void brakeMove() {
        if (!(upPressed || downPressed || leftPressed || rightPressed)) {

            if (moving) {

                if (mbc >= 5) {
                    moving = false;
                    mbc = 0;
                }
                mbc++;
                if (multiplier < DEFAULT_MULTIPLIER) {
                    multiplier = DEFAULT_MULTIPLIER;
                }
                multiplier = multiplier - DEFAULT_MULTIPLIER;
                if (direction == Direction.UP && getY() > wallSize) {
                    setLocation(getX(), getY() - getWalkingSpeed(s));
                    return;
                }
                if (direction == Direction.DOWN && getY() < BaseWorld.HEIGHT - wallSize) {
                    setLocation(getX(), getY() + getWalkingSpeed(s));
                    return;
                }
                if (direction == Direction.LEFT && getX() > wallSize) {
                    setLocation(getX() - getWalkingSpeed(s), getY());
                    return;
                }
                if (direction == Direction.RIGHT && getX() < BaseWorld.WIDTH - wallSize) {
                    setLocation(getX() + getWalkingSpeed(s), getY());
                    return;
                }

                if (direction == Direction.LEFT_UP && getY() > wallSize && getX() > wallSize) {
                    setLocation(getX() - getWalkingSpeed(d), getY() - getWalkingSpeed(d));
                    return;

                }

                if (direction == Direction.LEFT_DOWN && getY() < wallSize && getX() > wallSize) {
                    setLocation(getX() - getWalkingSpeed(d), getY() + getWalkingSpeed(d));
                    return;

                }

                if (direction == Direction.RIGHT_UP && getY() > wallSize && getX() < wallSize) {
                    setLocation(getX() + getWalkingSpeed(d), getY() - getWalkingSpeed(d));
                    return;

                }

                if (direction == Direction.RIGHT_DOWN && getY() < wallSize && getX() < wallSize) {
                    setLocation(getX() + getWalkingSpeed(d), getY() + getWalkingSpeed(d));
                }
            }

        }
    }

    public boolean canMove() {
        if (getWorld() instanceof VictoryScreen || getWorld() instanceof Endscreen) {
            return false;
        }
        int walkingSpeed = getWalkingSpeed(4);
        int radius = (int) Math.ceil(wallSize + walkingSpeed);
        List<Wall> walls = getObjectsInRange(radius, Wall.class);
        for (Wall wall : walls) {
            int px = getX();
            int py = getY();
            int wx = wall.getX();
            int wy = wall.getY();

            double dx = px - wx;
            double dy = py - wy;

            // System.out.println("px = " + px + ", py = " + py);

            if ((py - walkingSpeed < wallSize && upPressed) && !(Math.abs(dx) > wallSize)) {
                // wall top
                // System.out.println("HOCH");
                return false;
            } else if ((py + walkingSpeed > BaseWorld.HEIGHT - (wallSize) && downPressed) && !(Math.abs(dx) > wallSize)) {
                // wall bottom
                // System.out.println("RUNTER");
                return false;
            } else if ((px - walkingSpeed < wallSize && leftPressed) && !(Math.abs(dy) > wallSize)) {
                // wall left
                // System.out.println("LINKS");
                return false;
            } else if ((px + walkingSpeed > BaseWorld.WIDTH - wallSize && rightPressed) && !(Math.abs(dy) > wallSize)) {
                // wall right
                // System.out.println("RECHTS");
                return false;
            }

        }
        return true;
    }

    public void touchObjects() {
        for (int i = 0; i < 4; i++) {
            switch (i) {
                case 1:
                    touchingClass = Shelf.class;
                    break;
                case 2:
                    touchingClass = DecoKnight.class;
                    break;
                case 3:
                    touchingClass = Boss2.class;
                    break;
            }
            resetPosition();
        }
    }

    public void resetPosition() {
        if (isTouching(touchingClass)) {
            this.setLocation(lastX, lastY);
            return;
        }
    }


    /**
     * Everything about the hash map
     * <p>
     * halp
     */
    public void initEnemyMap() {
        initEnemyMap(RoomOne.class, Bug.class, 4);
        initEnemyMap(RoomOne.class, Bee.class, 4);


        initEnemyMap(RoomTwo.class, Bug.class, 4);
        initEnemyMap(RoomTwo.class, Bee.class, 4);

        initEnemyMap(RoomThree.class, Bug.class, 4);
        initEnemyMap(RoomThree.class, Bee.class, 4);

        initItemMap(RoomOne.class, PotionRed.class, 1);
        initEnemyMap(RoomOne2.class, Bug.class, 2);
        initEnemyMap(RoomOne2.class, Bee.class, 2);

        initEnemyMap(RoomTwo2.class, Bug.class, 1);
        initEnemyMap(RoomTwo2.class, Bee.class, 3);

        initEnemyMap(RoomThree2.class, Bug.class, 0);
        initEnemyMap(RoomThree2.class, Bee.class, 4);

        initEnemyMap(RoomFour2.class, Bug.class, 2);
        initEnemyMap(RoomFour2.class, Bee.class, 2);

        initEnemyMap(RoomFive2.class, Bug.class, 3);
        initEnemyMap(RoomFive2.class, Bee.class, 1);

        initEnemyMap(RoomSix2.class, Bug.class, 3);
        initEnemyMap(RoomSix2.class, Bee.class, 3);

        initItemMap(RoomOne.class, PotionRed.class, 1);
        initItemMap(RoomBoss.class, PotionGreen.class, 1);
        initItemMap(RoomBoss.class, PotionBlue.class, 1);
        initItemMap(RoomThree.class, PotionPurple.class, 1);

        initItemMap(RoomOne2.class, PotionRed.class, 1);
        initItemMap(RoomTwo2.class, PotionBlue.class, 1);
        initItemMap(RoomTwo2.class, PotionGreen.class, 1);
        initItemMap(RoomThree2.class, PotionBlue.class, 1);
        initItemMap(RoomFour2.class, PotionPurple.class, 1);
        initItemMap(RoomFive2.class, PotionBlue.class, 1);
        initItemMap(RoomFive2.class, PotionRed.class, 1);
        initItemMap(RoomSix2.class, PotionGreen.class, 1);
        initItemMap(RoomBoss2.class, PotionPurple.class, 1);
        initItemMap(RoomBoss2.class, PotionBlue.class, 1);
        initItemMap(RoomBoss2.class, PotionGreen.class, 1);
    }

    private void initItemMap(Class<? extends BaseWorld> worldClass, Class<? extends BaseItem> itemClass, int i) {

        HashMap<Class<? extends BaseItem>, Integer> roomMap = itemsMap.get(worldClass);
        if (roomMap == null) {
            roomMap = new HashMap<>();
        }
        roomMap.put(itemClass, i);
        itemsMap.put(worldClass, roomMap);
    }

    void initEnemyMap(Class<? extends BaseWorld> worldClass, Class<? extends BaseEnemy> enemyClass, Integer mainEnemyAmount) {
        HashMap<Class<? extends BaseEnemy>, Integer> roomMap = levelmap.get(worldClass);
        if (roomMap == null) {
            roomMap = new HashMap<>();
        }
        roomMap.put(enemyClass, mainEnemyAmount);
        levelmap.put(worldClass, roomMap);
    }

    public HashMap<Class<? extends BaseWorld>, HashMap<Class<? extends BaseEnemy>, Integer>> getLevelmap() {
        return levelmap;
    }

    private ArrayList<BaseItem> itemsCollection = new ArrayList<>();

    public ArrayList<BaseItem> getItemsCollection() {
        return itemsCollection;
    }

    public HashMap<Class<? extends BaseWorld>, HashMap<Class<? extends BaseItem>, Integer>> getItemMap() {
        return itemsMap;
    }
}




