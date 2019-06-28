package de.wgkassel.curstle.Worlds.Level1;

import de.wgkassel.curstle.MiniMap;
import de.wgkassel.curstle.RoomContent.Door;
import de.wgkassel.curstle.RoomContent.Shelf;
import de.wgkassel.curstle.gates.BaseGate;
import de.wgkassel.curstle.gates.RoomBossGate;
import de.wgkassel.curstle.gates.RoomThreeGate;
import de.wgkassel.curstle.gates.RoomTwoGate;
import de.wgkassel.curstle.player.Hearts;
import de.wgkassel.curstle.player.Player;

public class RoomOne extends BaseWorld {

    public RoomOne(Player player) {
        super(player);
        prepare();
    }


    public void prepare() {

        generateWalls();


        Door doorA = new Door();
        addObject(doorA, 850, 988);
        doorA.getImage().rotate(180);

        RoomTwoGate doorB = new RoomTwoGate();
        addObject(doorB, 15, 500);
        doorB.getImage().rotate(270);

        RoomThreeGate doorC = new RoomThreeGate();
        addObject(doorC, 1688, 500);
        doorC.getImage().rotate(90);

        RoomBossGate doorD = new RoomBossGate();
        addObject(doorD, 850, 12);
        doorD.getImage().scale(170, 170);

        Shelf bookshelf = new Shelf();
        addObject(bookshelf, getWidth() / 8, getHeight() / 8);
        bookshelf.getImage().scale(150, 150);

        generateBugs();

        generatePotions();

        if (BaseGate.twoToOne) {
            addObject(player, 200, 500);
            BaseGate.twoToOne = false;
        } else if (BaseGate.threeToOne) {
            addObject(player, 1340, 500);
        } else if (BaseGate.bossToOne) {
            addObject(player, 850, 100);
        } else {
            addObject(player, 850, 900);
        }

        Hearts hearts = new Hearts();
        addObject(hearts, 1300, 35);

        MiniMap miniMap = new MiniMap(1);
        addObject(miniMap, 1600, 900);


    }


}

