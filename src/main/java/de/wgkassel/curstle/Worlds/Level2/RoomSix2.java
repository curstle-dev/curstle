package de.wgkassel.curstle.Worlds.Level2;


import de.wgkassel.curstle.RoomContent.MiniMap;
import de.wgkassel.curstle.RoomContent.DecoKnightDown;
import de.wgkassel.curstle.Worlds.Level1.BaseWorld;
import de.wgkassel.curstle.gates.RoomBoss2Gate;
import de.wgkassel.curstle.gates.SixToFiveGate;
import de.wgkassel.curstle.player.Hearts;
import de.wgkassel.curstle.player.Player;

public class RoomSix2 extends BaseWorld {
    public RoomSix2(Player player) {
        super(player);
        prepare();
        generateBees();
        generateBugs();
        generatePotions();
    }

    public void prepare() {
        generateWoodWalls();

        addObject(player, 1520, 500);

        SixToFiveGate gate1 = new SixToFiveGate();
        gate1.setRotation(90);
        addObject(gate1, 1700, 500);

        RoomBoss2Gate gate2 = new RoomBoss2Gate();
        addObject(gate2, 850, 10);

        Hearts hearts = new Hearts();
        addObject(hearts, 1300, 35);

        MiniMap miniMap = new MiniMap(11);
        addObject(miniMap, 1500, 800);

        DecoKnightDown dknight = new DecoKnightDown();
        addObject(dknight, 700, 110);

        DecoKnightDown dknight1 = new DecoKnightDown();
        addObject(dknight1, 1000, 110);

    }
}
