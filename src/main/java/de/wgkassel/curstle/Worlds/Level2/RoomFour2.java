package de.wgkassel.curstle.Worlds.Level2;


import de.wgkassel.curstle.RoomContent.MiniMap;
import de.wgkassel.curstle.RoomContent.DecoKnightLeft;
import de.wgkassel.curstle.RoomContent.Shelf;
import de.wgkassel.curstle.Worlds.Level1.BaseWorld;
import de.wgkassel.curstle.gates.BaseGate;
import de.wgkassel.curstle.gates.FourToThreeGate;
import de.wgkassel.curstle.player.Hearts;
import de.wgkassel.curstle.player.Player;

public class RoomFour2 extends BaseWorld {
    public RoomFour2(Player player) {
        super(player);
        prepare();
        generateBees();
        generateBugs();
        generatePotions();
    }

    public void prepare() {

        BaseGate.roomFour2Cleared = true;

        generateWoodWalls();

        addObject(player, 200, 500);

        FourToThreeGate gate = new FourToThreeGate();
        gate.setRotation(270);
        addObject(gate, 10, 500);

        Shelf bookshelf = new Shelf();
        addObject(bookshelf, getWidth() / 8, getHeight() / 8);
        bookshelf.getImage().scale(150, 150);

        Shelf shelf = new Shelf();
        addObject(shelf, getWidth() / 8 + 200, getHeight() / 8);
        shelf.getImage().scale(150, 150);

        Shelf shelf2 = new Shelf();
        addObject(shelf2, getWidth() / 8 + 1100, getHeight() / 8);
        shelf2.getImage().scale(150, 150);

        Shelf shelf3 = new Shelf();
        addObject(shelf3, getWidth() / 8 + 1300, getHeight() / 8);
        shelf3.getImage().scale(150, 150);

        Hearts hearts = new Hearts();
        addObject(hearts, 1300, 35);

        DecoKnightLeft lknight = new DecoKnightLeft();
        addObject(lknight, 1480, 300);

        MiniMap miniMap = new MiniMap(9);
        addObject(miniMap, 1500, 800);


    }
}
