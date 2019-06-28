package de.wgkassel.curstle.Worlds.Level2;

import de.wgkassel.curstle.MiniMap;
import de.wgkassel.curstle.RoomContent.Shelf;
import de.wgkassel.curstle.Worlds.Level1.BaseWorld;
import de.wgkassel.curstle.gates.BaseGate;
import de.wgkassel.curstle.gates.One2ToStairs2Gate;
import de.wgkassel.curstle.gates.RoomTwo2Gate;
import de.wgkassel.curstle.player.Hearts;
import de.wgkassel.curstle.player.Player;

public class RoomOne2 extends BaseWorld {

    public RoomOne2(Player player) {
        super(player);
        prepare();
        generateBees();
        generateBugs();
        generatePotions();
    }

    public void prepare() {
        generateWoodWalls();

        if (BaseGate.RoomTwo2ToOne2) {
            addObject(player, 850, 200);
        } else {
            addObject(player, 850, 800);
        }
        One2ToStairs2Gate sRGate2_1 = new One2ToStairs2Gate();
        sRGate2_1.getImage().rotate(180);
        addObject(sRGate2_1, 850, 1000);

        addObject(new RoomTwo2Gate(), 850, 10);

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


        MiniMap miniMap = new MiniMap(6);
        addObject(miniMap, 1500, 800);

    }
}
