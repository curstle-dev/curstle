package de.wgkassel.curstle.gates;

import de.wgkassel.curstle.RoomContent.SilentObjects;
import de.wgkassel.curstle.StairsRoom;
import de.wgkassel.curstle.Worlds.Level1.*;
import de.wgkassel.curstle.Worlds.Level2.*;
import de.wgkassel.curstle.enemy.Boss;
import de.wgkassel.curstle.enemy.Endboss;
import de.wgkassel.curstle.enemy.Enemy2;
import de.wgkassel.curstle.enemy.MainEnemy;
import de.wgkassel.curstle.player.Player;
import greenfoot.Greenfoot;

public abstract class BaseGate extends SilentObjects {

    public BaseGate() {
        setImage("door.png");
    }

    public abstract void handleGateLogic();

    public static boolean roomTwoCleared = false;
    public static boolean roomThreeCleared = false;

    public static boolean roomFour2Cleared = false;

    public static boolean twoToOne = false;
    public static boolean threeToOne = false;
    public static boolean bossToOne;
    public static boolean stair1ToBoss;
    public static boolean RoomOne2ToStairs2_1;
    public static boolean RoomThree2ToTwo2;
    public static boolean RoomTwo2ToOne2;
    public static boolean RoomFour2ToThree;
    public static boolean RoomFive2ToTwo2;
    public static boolean RoomSix2ToFive2;

    @Override
    public final void act() {
        super.act();
        handleGateLogic();
    }

    public void generateRoomOne() {
        if (getWorld().getObjects(MainEnemy.class).isEmpty()) {
            if (isTouching(Player.class)) {
                System.out.println("Generating Room One");
                RoomOne r1 = new RoomOne(((BaseWorld) getWorld()).getPlayer());
                Greenfoot.setWorld(r1);
            }
        }
    }

    public void generateTwoToOneGate() {
        if (getWorld().getObjects(MainEnemy.class).isEmpty()) {
            twoToOne = true;
            setImage("door_open.png");
            this.getImage().rotate(90);
            if (isTouching(Player.class)) {
                RoomOne r1 = new RoomOne(((BaseWorld) getWorld()).getPlayer());
                Greenfoot.setWorld(r1);
            }
        }
    }

    public void generateThreeToOneGate() {
        if (getWorld().getObjects(MainEnemy.class).isEmpty()) {
            threeToOne = true;
            setImage("door_open.png");
            this.getImage().rotate(-90);
            if (isTouching(Player.class)) {
                RoomOne r1 = new RoomOne(((BaseWorld) getWorld()).getPlayer());
                Greenfoot.setWorld(r1);
            }
        }
    }

    public void generateBossToOneGate() {
        if (getWorld().getObjects(Boss.class).isEmpty()) {
            bossToOne = true;
            setImage("door_open.png");
            this.getImage().rotate(180);
            if (isTouching(Player.class)) {
                RoomOne r1 = new RoomOne(((BaseWorld) getWorld()).getPlayer());
                Greenfoot.setWorld(r1);
            }
        }
    }

    public void generateRoomTwo() {
        if (getWorld().getObjects(MainEnemy.class).isEmpty()) {
            setImage("door_open.png");
            this.getImage().rotate(-90);
            if (isTouching(Player.class)) {
                System.out.println("Generating Room Two");
                RoomTwo r2 = new RoomTwo(((BaseWorld) getWorld()).getPlayer());
                Greenfoot.setWorld(r2);
            }
        }
    }

    public void generateRoomThree() {
        if (getWorld().getObjects(MainEnemy.class).isEmpty()) {
            setImage("door_open.png");
            this.getImage().rotate(90);
            if (isTouching(Player.class)) {
                System.out.println("Generating Room Three");
                RoomThree r3 = new RoomThree(((BaseWorld) getWorld()).getPlayer());
                Greenfoot.setWorld(r3);
            }
        }
    }

    public void generateBossRoom() {
        if (getWorld().getObjects(MainEnemy.class).isEmpty() && roomTwoCleared && roomThreeCleared) {
            setImage("door_open.png");
            if (isTouching(Player.class)) {
                System.out.println("Generating Room Boss");
                RoomBoss rb = new RoomBoss(((BaseWorld) getWorld()).getPlayer());
                Greenfoot.setWorld(rb);
            }
        }
    }


    public void generateStairsToBossGate() {
        if (getWorld().getObjects(MainEnemy.class).isEmpty() && roomTwoCleared && roomThreeCleared) {
            stair1ToBoss = true;
            setImage("door_open.png");
            this.getImage().rotate(180);
            if (isTouching(Player.class)) {
                RoomBoss rb = new RoomBoss(((BaseWorld) getWorld()).getPlayer());
                Greenfoot.setWorld(rb);
            }
        }
    }

    public void generateStairsRoom() {
        if (getWorld().getObjects(Boss.class).isEmpty()) {
            setImage("door_open.png");
            if (isTouching(Player.class)) {
                StairsRoom sR = new StairsRoom(((BaseWorld) getWorld()).getPlayer());
                Greenfoot.setWorld(sR);
            }
        }
    }


    public void generateStairsRoom2_1() {
        setImage("door_open.png");
        if (isTouching(Player.class)) {
            System.out.println("Generating Stairs Room 2.1");
            StairsRoom2_1 sR2_1 = new StairsRoom2_1(((BaseWorld) getWorld()).getPlayer());
            Greenfoot.setWorld(sR2_1);
        }
    }

    public void generateOne2ToStairs2Gate() {
        if (getWorld().getObjects(MainEnemy.class).isEmpty() && getWorld().getObjects(Enemy2.class).isEmpty()) {
            RoomOne2ToStairs2_1 = true;
            setImage("door_open.png");
            this.getImage().rotate(180);
            if (isTouching(Player.class)) {
                System.out.println("Generating Stairs Room 2.1");
                StairsRoom2_1 sR2_1 = new StairsRoom2_1(((BaseWorld) getWorld()).getPlayer());
                Greenfoot.setWorld(sR2_1);
            }
        }
    }

    public void generateTwo2ToOne2Gate() {
        if (getWorld().getObjects(MainEnemy.class).isEmpty() && getWorld().getObjects(Enemy2.class).isEmpty()) {
            RoomTwo2ToOne2 = true;
            setImage("door_open.png");
            this.getImage().rotate(180);
            this.getImage().rotate(180);
            if (isTouching(Player.class)) {
                System.out.println("Generating Room One 2");
                RoomOne2 rOne2 = new RoomOne2(((BaseWorld) getWorld()).getPlayer());
                Greenfoot.setWorld(rOne2);
            }
        }
    }

    public void generateRoomOne2() {
        setImage("door_open.png");
        this.getImage().rotate(180);
        if (isTouching(Player.class)) {
            System.out.println("Generating Room One 2");
            RoomOne2 rOne2 = new RoomOne2(((BaseWorld) getWorld()).getPlayer());
            Greenfoot.setWorld(rOne2);
        }
    }

    public void generateRoomTwo2() {
        if (getWorld().getObjects(MainEnemy.class).isEmpty() && getWorld().getObjects(Enemy2.class).isEmpty()) {
            setImage("door_open.png");
            if (isTouching(Player.class)) {
                System.out.println("Generating Room Two 2");
                RoomTwo2 rTwo2 = new RoomTwo2(((BaseWorld) getWorld()).getPlayer());
                Greenfoot.setWorld(rTwo2);

            }
        }
    }

    public void generateFiveToTwo2Gate() {
        if (getWorld().getObjects(MainEnemy.class).isEmpty() && getWorld().getObjects(Enemy2.class).isEmpty()) {
            RoomFive2ToTwo2 = true;
            setImage("door_open.png");
            if (isTouching(Player.class)) {
                System.out.println("Generating Room Two 2");
                RoomTwo2 rTwo2 = new RoomTwo2(((BaseWorld) getWorld()).getPlayer());
                Greenfoot.setWorld(rTwo2);

            }
        }
    }

    public void generateThreeToTwo2Gate() {
        if (getWorld().getObjects(MainEnemy.class).isEmpty() && getWorld().getObjects(Enemy2.class).isEmpty()) {
            RoomThree2ToTwo2 = true;
            setImage("door_open.png");
            if (isTouching(Player.class)) {
                System.out.println("Generating Room Three 2");
                RoomTwo2 rTwo2 = new RoomTwo2(((BaseWorld) getWorld()).getPlayer());
                Greenfoot.setWorld(rTwo2);

            }
        }
    }

    public void generateRoomThree2() {
        if (getWorld().getObjects(MainEnemy.class).isEmpty() && getWorld().getObjects(Enemy2.class).isEmpty()) {
            setImage("door_open.png");
            if (isTouching(Player.class)) {
                System.out.println("Generating Room Three 2");
                RoomThree2 r3_2 = new RoomThree2(((BaseWorld) getWorld()).getPlayer());
                Greenfoot.setWorld(r3_2);
            }
        }
    }

    public void generateFourToThreeGate() {
        RoomFour2ToThree = true;
        if (getWorld().getObjects(MainEnemy.class).isEmpty() && getWorld().getObjects(Enemy2.class).isEmpty()) {
            setImage("door_open.png");
            if (isTouching(Player.class)) {
                System.out.println("Generating Room Three 2");
                RoomThree2 r3_2 = new RoomThree2(((BaseWorld) getWorld()).getPlayer());
                Greenfoot.setWorld(r3_2);
            }
        }
    }

    public void generateRoomFour2() {
        if (getWorld().getObjects(MainEnemy.class).isEmpty() && getWorld().getObjects(Enemy2.class).isEmpty()) {
            setImage("door_open.png");
            if (isTouching(Player.class)) {
                System.out.println("Generating Room Four 2");
                RoomFour2 r4_2 = new RoomFour2(((BaseWorld) getWorld()).getPlayer());
                Greenfoot.setWorld(r4_2);
            }
        }
    }

    public void generateRoomFive2() {
        if (getWorld().getObjects(MainEnemy.class).isEmpty() && getWorld().getObjects(Enemy2.class).isEmpty()) {
            setImage("door_open.png");
            if (isTouching(Player.class)) {
                System.out.println("Generating Room Five 2");
                RoomFive2 r5_2 = new RoomFive2(((BaseWorld) getWorld()).getPlayer());
                Greenfoot.setWorld(r5_2);
            }
        }
    }

    public void generateSixToFiveGate() {
        if (getWorld().getObjects(MainEnemy.class).isEmpty() && getWorld().getObjects(Enemy2.class).isEmpty()) {
            RoomSix2ToFive2 = true;
            setImage("door_open.png");
            if (isTouching(Player.class)) {
                System.out.println("Generating Room Five 2");
                RoomFive2 r5_2 = new RoomFive2(((BaseWorld) getWorld()).getPlayer());
                Greenfoot.setWorld(r5_2);
            }
        }
    }

    public void generateBoss2ToSixGate() {
        if (getWorld().getObjects(Endboss.class).isEmpty() && getWorld().getObjects(Enemy2.class).isEmpty()) {
            setImage("door_open.png");
            this.getImage().rotate(180);
            if (isTouching(Player.class)) {
                System.out.println("Generating Room Six 2");
                RoomSix2 r6_2 = new RoomSix2(((BaseWorld) getWorld()).getPlayer());
                Greenfoot.setWorld(r6_2);
            }
        }
    }

    public void generateRoomSix2() {
        if (getWorld().getObjects(MainEnemy.class).isEmpty() && getWorld().getObjects(Enemy2.class).isEmpty()) {
            setImage("door_open.png");
            if (isTouching(Player.class)) {
                System.out.println("Generating Room Six 2");
                RoomSix2 r6_2 = new RoomSix2(((BaseWorld) getWorld()).getPlayer());
                Greenfoot.setWorld(r6_2);
            }
        }
    }

    public void generateRoomBoss2() {
        if (getWorld().getObjects(MainEnemy.class).isEmpty() && getWorld().getObjects(Enemy2.class).isEmpty() && roomFour2Cleared) {
            setImage("door_open.png");
            if (isTouching(Player.class)) {
                System.out.println("Generating Room Boss 2");
                RoomBoss2 rB_2 = new RoomBoss2(((BaseWorld) getWorld()).getPlayer());
                Greenfoot.setWorld(rB_2);
            }
        }
    }

}


