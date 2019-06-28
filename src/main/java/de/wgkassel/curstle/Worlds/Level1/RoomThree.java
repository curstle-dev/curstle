package de.wgkassel.curstle.Worlds.Level1;

import de.wgkassel.curstle.MiniMap;
import de.wgkassel.curstle.gates.BaseGate;
import de.wgkassel.curstle.gates.ThreeToOneGate;
import de.wgkassel.curstle.player.Hearts;
import de.wgkassel.curstle.player.Player;

public class RoomThree extends BaseWorld {

    public RoomThree(Player player) {

        super(player);

        BaseGate.roomThreeCleared = true;

        generateWalls();
        generateBugs();
        addObject(player, 200, 500);

        ThreeToOneGate doorC2 = new ThreeToOneGate();
        addObject(doorC2, 15, 500);
        doorC2.getImage().rotate(270);

        Hearts hearts = new Hearts();
        addObject(hearts, 1300, 35);

        MiniMap miniMap = new MiniMap(3);
        addObject(miniMap, 1600, 900);


        generatePotions();

    }

}
