package de.wgkassel.curstle.Worlds;

import de.wgkassel.curstle.RoomContent.MiniMap;
import de.wgkassel.curstle.RoomContent.Shelf;
import de.wgkassel.curstle.Worlds.Level1.BaseWorld;
import de.wgkassel.curstle.gates.StairsRoom2_1Gate;
import de.wgkassel.curstle.gates.StairsToBossGate;
import de.wgkassel.curstle.player.Player;

public class StairsRoom extends BaseWorld {

    public StairsRoom(Player player) {
        super(player);
        addObject(player, 200, 800);
        generateWalls();

        StairsToBossGate stairsGate = new StairsToBossGate();
        addObject(stairsGate, 200, 1000);
        stairsGate.getImage().rotate(180);
        addObject(new StairsRoom2_1Gate(), 1520, 10);

        MiniMap miniMap = new MiniMap(5);
        addObject(miniMap, 1600, 900);

        Shelf bookshelf = new Shelf();
        addObject(bookshelf, 300, getHeight() / 8);
        bookshelf.getImage().scale(300, 150);

        Shelf shelf = new Shelf();
        addObject(shelf, 800, getHeight() / 8 - 50);

    }

}
