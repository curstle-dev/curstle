
package de.wgkassel.curstle.Worlds.Level1;

import de.wgkassel.curstle.RoomContent.MiniMap;
import de.wgkassel.curstle.gates.BaseGate;
import de.wgkassel.curstle.gates.TwoToOneGate;
import de.wgkassel.curstle.player.Hearts;
import de.wgkassel.curstle.player.Player;

public class RoomTwo extends BaseWorld {

    public RoomTwo(Player player) {

        super(player);
        addObject(player, 1500, 500);
        BaseGate.roomTwoCleared = true;

        generateWalls();
        generateBugs();

        TwoToOneGate doorB2 = new TwoToOneGate();
        addObject(doorB2, 1688, 500);
        doorB2.getImage().rotate(90);

        Hearts hearts = new Hearts();
        addObject(hearts, 1300, 35);

        MiniMap miniMap = new MiniMap(2);
        addObject(miniMap, 1600, 900);


        generatePotions();

    }

}
