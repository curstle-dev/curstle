package de.wgkassel.curstle.Worlds.Level2;


import de.wgkassel.curstle.RoomContent.MiniMap;
import de.wgkassel.curstle.RoomContent.Shelf;
import de.wgkassel.curstle.Worlds.Level1.BaseWorld;
import de.wgkassel.curstle.gates.BaseGate;
import de.wgkassel.curstle.gates.FiveToTwo2Gate;
import de.wgkassel.curstle.gates.RoomSix2Gate;
import de.wgkassel.curstle.player.Hearts;
import de.wgkassel.curstle.player.Player;

public class RoomFive2 extends BaseWorld {
    public RoomFive2(Player player) {
        super(player);
        prepare();
        generateBees();
        generateBugs();
        generatePotions();
    }

    public void prepare() {
        generateWoodWalls();

        if (BaseGate.RoomSix2ToFive2) {
            addObject(player, 200, 500);
            BaseGate.RoomSix2ToFive2 = false;
        } else {
            addObject(player, 1520, 500);
        }

        FiveToTwo2Gate gate1 = new FiveToTwo2Gate();
        gate1.setRotation(90);
        addObject(gate1, 1700, 500);

        RoomSix2Gate gate2 = new RoomSix2Gate();
        gate2.setRotation(270);
        addObject(gate2, 10, 500);

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

        MiniMap miniMap = new MiniMap(10);
        addObject(miniMap, 1500, 800);
    }
}
