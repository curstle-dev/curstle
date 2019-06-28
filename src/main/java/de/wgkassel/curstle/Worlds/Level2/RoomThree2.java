package de.wgkassel.curstle.Worlds.Level2;

import de.wgkassel.curstle.MiniMap;
import de.wgkassel.curstle.Worlds.Level1.BaseWorld;
import de.wgkassel.curstle.gates.BaseGate;
import de.wgkassel.curstle.gates.RoomFour2Gate;
import de.wgkassel.curstle.gates.ThreeToTwo2Gate;
import de.wgkassel.curstle.player.Hearts;
import de.wgkassel.curstle.player.Player;

public class RoomThree2 extends BaseWorld {
    public RoomThree2(Player player) {
        super(player);
        prepare();
        generateBees();
        generateBugs();
        generatePotions();
    }

    public void prepare() {
        generateWoodWalls();

        if (BaseGate.RoomFour2ToThree) {
            addObject(player, 1400, 500);
            BaseGate.RoomFour2ToThree = false;
        } else {
            addObject(player, 850, 800);
        }

        ThreeToTwo2Gate gate1 = new ThreeToTwo2Gate();
        gate1.setRotation(180);
        addObject(gate1, 850, 1000);

        RoomFour2Gate gate2 = new RoomFour2Gate();
        gate2.setRotation(90);
        addObject(gate2, 1700, 500);

        Hearts hearts = new Hearts();
        addObject(hearts, 1300, 35);
        MiniMap miniMap = new MiniMap(8);

        addObject(miniMap, 1500, 800);
    }
}
