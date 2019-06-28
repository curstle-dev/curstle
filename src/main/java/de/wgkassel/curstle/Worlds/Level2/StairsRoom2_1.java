package de.wgkassel.curstle.Worlds.Level2;

import de.wgkassel.curstle.RoomContent.MiniMap;
import de.wgkassel.curstle.RoomContent.Shelf;
import de.wgkassel.curstle.Worlds.Level1.BaseWorld;
import de.wgkassel.curstle.gates.BaseGate;
import de.wgkassel.curstle.gates.RoomOne2Gate;
import de.wgkassel.curstle.gates.StairsRoomGate;
import de.wgkassel.curstle.player.Player;

public class StairsRoom2_1 extends BaseWorld {

    public StairsRoom2_1(Player player) {
        super(player);
        if (BaseGate.RoomOne2ToStairs2_1) {
            addObject(player, 850, 200);
        } else {
            addObject(player, 1520, 800);
        }

        generateWalls();

        StairsRoomGate stairsGate = new StairsRoomGate();
        stairsGate.setRotation(180);
        addObject(stairsGate, 1520, 1000);

        RoomOne2Gate r1_2Gate = new RoomOne2Gate();
        addObject(r1_2Gate, 850, 10);
        r1_2Gate.setImage("door_open.png");


        Shelf bookshelf = new Shelf();
        addObject(bookshelf, 300, 800);
        bookshelf.getImage().scale(300, 150);

        Shelf shelf = new Shelf();
        addObject(shelf, 300, 600);
        shelf.getImage().scale(300, 150);

        Shelf shelf2 = new Shelf();
        addObject(shelf2, 300, 400);
        shelf2.getImage().scale(300, 150);

        Shelf shelf3 = new Shelf();
        addObject(shelf3, 300, 200);
        shelf3.getImage().scale(300, 150);

        MiniMap miniMap = new MiniMap(12);
        addObject(miniMap, 1500, 800);


    }

}
