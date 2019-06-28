package de.wgkassel.curstle.Worlds.Level2;

import de.wgkassel.curstle.MiniMap;
import de.wgkassel.curstle.RoomContent.Shelf;
import de.wgkassel.curstle.Worlds.Level1.BaseWorld;
import de.wgkassel.curstle.gates.BaseGate;
import de.wgkassel.curstle.gates.RoomFive2Gate;
import de.wgkassel.curstle.gates.RoomThree2Gate;
import de.wgkassel.curstle.gates.Two2ToOne2Gate;
import de.wgkassel.curstle.player.Hearts;
import de.wgkassel.curstle.player.Player;

public class RoomTwo2 extends BaseWorld {
    public RoomTwo2(Player player) {
        super(player);
        prepare();
        generateBees();
        generateBugs();
        generatePotions();
    }

    public void prepare() {
        generateWoodWalls();

        if (BaseGate.RoomThree2ToTwo2) {
            addObject(player, 850, 200);
            BaseGate.RoomThree2ToTwo2 = false;
        } else if (BaseGate.RoomFive2ToTwo2) {
            addObject(player, 200, 500);
            BaseGate.RoomFive2ToTwo2 = false;
        } else {
            addObject(player, 850, 800);
        }

        Two2ToOne2Gate gate = new Two2ToOne2Gate();
        gate.setRotation(180);
        addObject(gate, 850, 1000);

        RoomThree2Gate gate2 = new RoomThree2Gate();
        addObject(gate2, 850, 10);

        RoomFive2Gate gate3 = new RoomFive2Gate();
        gate3.setRotation(270);
        addObject(gate3, 10, 500);

        Shelf bookshelf = new Shelf();
        addObject(bookshelf, getWidth() / 8, getHeight() / 8);
        bookshelf.getImage().scale(150, 150);

        Shelf shelf2 = new Shelf();
        addObject(shelf2, getWidth() / 8 + 1300, getHeight() / 8);
        shelf2.getImage().scale(150, 150);


        Shelf shelf3 = new Shelf();
        addObject(shelf3, getWidth() / 8 + 1300, getHeight() / 8 + 700);
        shelf3.getImage().scale(150, 150);

        Shelf shelf4 = new Shelf();
        addObject(shelf4, getWidth() / 8, getHeight() / 8 + 700);
        shelf4.getImage().scale(150, 150);

        Hearts hearts = new Hearts();
        addObject(hearts, 1300, 35);

        MiniMap miniMap = new MiniMap(7);
        addObject(miniMap, 1500, 800);

    }
}
