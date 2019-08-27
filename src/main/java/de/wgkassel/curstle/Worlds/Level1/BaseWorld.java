package de.wgkassel.curstle.Worlds.Level1;

import de.wgkassel.curstle.RoomContent.CornerWall;
import de.wgkassel.curstle.RoomContent.CornerWoodWall;
import de.wgkassel.curstle.RoomContent.Wall;
import de.wgkassel.curstle.RoomContent.WoodWall;
import de.wgkassel.curstle.enemy.BaseEnemy;
import de.wgkassel.curstle.enemy.Bee;
import de.wgkassel.curstle.enemy.Bug;
import de.wgkassel.curstle.items.*;
import de.wgkassel.curstle.player.Player;
import greenfoot.World;

import java.util.HashMap;
import java.util.Random;


public abstract class BaseWorld extends World {

    public final static int WIDTH = 1700;
    public final static int HEIGHT = 1000;

    public BaseWorld(Player player) {
        super(WIDTH, HEIGHT, 1);
        this.player = player;
    }

    public BaseWorld() {
        super(WIDTH, HEIGHT, 1);
        this.player = player;
    }

    protected Player player;


    public Player getPlayer() {
        return player;
    }


    public void generateWalls() {
        int k = 50;

        for (int i = 0; i < 50; i++) {         //Wand oben
            addObject(new Wall(), k, 10);
            k = k + 170;
        }

        int l = 20;

        for (int i = 0; i < 20; i++) {         //Wand links
            Wall wall = new Wall();
            addObject(wall, 10, l);
            wall.setRotation(270);
            l = l + 170;
        }

        int m = 50;

        for (int i = 0; i < 50; i++) {         //wand unten
            Wall wall = new Wall();
            addObject(wall, m, 1000);
            wall.setRotation(180);
            m = m + 170;
        }

        int n = 20;

        for (int i = 0; i < 20; i++) {         // wand rechts
            Wall wall = new Wall();
            addObject(wall, 1700, n);
            wall.setRotation(90);
            n = n + 170;
        }

        CornerWall cornerTopLeft = new CornerWall();                        //corner walls :)
        addObject(cornerTopLeft, 10, 10);
        cornerTopLeft.getImage().scale(170, 170);

        CornerWall cornerTopRight = new CornerWall();
        addObject(cornerTopRight, 1700, 10);
        cornerTopRight.getImage().scale(170, 170);
        cornerTopRight.setRotation(90);

        CornerWall cornerBottomRight = new CornerWall();
        addObject(cornerBottomRight, 1700, 1000);
        cornerBottomRight.getImage().scale(170, 170);
        cornerBottomRight.setRotation(180);

        CornerWall cornerBottomLeft = new CornerWall();
        addObject(cornerBottomLeft, 10, 1000);
        cornerBottomLeft.getImage().scale(170, 170);
        cornerBottomLeft.setRotation(270);
    }

    public void generateWoodWalls() {
        int k = 50;

        for (int i = 0; i < 50; i++) {         //Wand oben
            addObject(new WoodWall(), k, 10);
            k = k + 170;
        }

        int l = 20;

        for (int i = 0; i < 20; i++) {         //Wand links
            WoodWall woodWall = new WoodWall();
            addObject(woodWall, 10, l);
            woodWall.setRotation(270);
            l = l + 170;
        }

        int m = 50;

        for (int i = 0; i < 50; i++) {         //wand unten
            WoodWall woodWall = new WoodWall();
            addObject(woodWall, m, 1000);
            woodWall.setRotation(180);
            m = m + 170;
        }

        int n = 20;

        for (int i = 0; i < 20; i++) {         // wand rechts
            WoodWall woodWall = new WoodWall();
            addObject(woodWall, 1700, n);
            woodWall.setRotation(90);
            n = n + 170;
        }

        CornerWoodWall cornerWoodTopLeft = new CornerWoodWall();                        //corner walls :)
        addObject(cornerWoodTopLeft, 10, 10);
        cornerWoodTopLeft.getImage().scale(170, 170);

        CornerWoodWall cornerWoodTopRight = new CornerWoodWall();
        addObject(cornerWoodTopRight, 1700, 10);
        cornerWoodTopRight.getImage().scale(170, 170);
        cornerWoodTopRight.setRotation(90);

        CornerWoodWall cornerWoodBottomRight = new CornerWoodWall();
        addObject(cornerWoodBottomRight, 1700, 1000);
        cornerWoodBottomRight.getImage().scale(170, 170);
        cornerWoodBottomRight.setRotation(180);

        CornerWoodWall cornerWoodBottomLeft = new CornerWoodWall();
        addObject(cornerWoodBottomLeft, 10, 1000);
        cornerWoodBottomLeft.getImage().scale(170, 170);
        cornerWoodBottomLeft.setRotation(270);
    }

    //public void generateBrickWalls() {}

    public void generateBugs() {

        HashMap<Class<? extends BaseWorld>, HashMap<Class<? extends BaseEnemy>, Integer>> levelmap = player.getLevelmap();
        HashMap<Class<? extends BaseEnemy>, Integer> enemyMap = levelmap.get(this.getClass());
        if (enemyMap != null) {
            Integer bugAmount = enemyMap.get(Bug.class);

            if (bugAmount == null) {
                bugAmount = 0;
            }

            if (bugAmount >= 1) {
                addObject(new Bug(), getWidth() / 8 * 5, getHeight() / 4 * 3);
            }

            if (bugAmount >= 2) {
                addObject(new Bug(), getWidth() / 5 * 2, getHeight() / 12 * 5);
            }

            if (bugAmount >= 3) {
                addObject(new Bug(), getWidth() / 4 * 3, getHeight() / 4);
            }

            if (bugAmount >= 4) {
                addObject(new Bug(), getWidth() / 7 * 5, getHeight() / 2);
            }
        }
    }

    public void generatePotions() {
        try {
            generateItems(PotionRed.class);
            generateItems(PotionBlue.class);
            generateItems(PotionPurple.class);
            generateItems(PotionGreen.class);
        } catch (IllegalAccessException | InstantiationException e) {
            System.err.println("Error generating items!");
        }
    }

    private int getRandomPosition() {
        int random = 0;
        while (random < 100) {
            random = new Random().nextInt(900);
        }
        return random;
    }


    private void generateItems(Class<? extends BaseItem> itemClass) throws IllegalAccessException, InstantiationException {
        HashMap<Class<? extends BaseWorld>, HashMap<Class<? extends BaseItem>, Integer>> levelmap = player.getItemMap();
        HashMap<Class<? extends BaseItem>, Integer> itemMap = levelmap.get(this.getClass());
        if (itemMap != null) {
            Integer itemAmount = itemMap.get(itemClass);
            if (itemAmount == null) {
                itemAmount = 0;
            }

            if (itemAmount >= 1) {
                addObject(itemClass.newInstance(), getRandomPosition(), getRandomPosition());
            }

            if (itemAmount >= 2) {
                addObject(itemClass.newInstance(), getRandomPosition(), getRandomPosition());
            }

            if (itemAmount >= 3) {
                addObject(itemClass.newInstance(), getRandomPosition(), getRandomPosition());
            }

            if (itemAmount >= 4) {
                addObject(itemClass.newInstance(), getRandomPosition(), getRandomPosition());
            }
        }
    }


    public void generateBees() {

        HashMap<Class<? extends BaseWorld>, HashMap<Class<? extends BaseEnemy>, Integer>> levelmap = player.getLevelmap();
        HashMap<Class<? extends BaseEnemy>, Integer> enemyMap = levelmap.get(this.getClass());
        if (enemyMap != null) {
            Integer beeAmount = enemyMap.get(Bee.class);

            if (beeAmount == null) {
                beeAmount = 0;
            }

            if (beeAmount >= 1) {
                addObject(new Bee(), getWidth() / 8 * 5, getHeight() / 4 * 3);
            }

            if (beeAmount >= 2) {
                addObject(new Bee(), getWidth() / 5 * 2, getHeight() / 12 * 5);
            }

            if (beeAmount >= 3) {
                addObject(new Bee(), getWidth() / 4 * 3, getHeight() / 4);
            }

            if (beeAmount >= 4) {
                addObject(new Bee(), getWidth() / 7 * 5, getHeight() / 2);
            }
        }
    }

}
