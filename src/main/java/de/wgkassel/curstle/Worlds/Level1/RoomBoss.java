package de.wgkassel.curstle.Worlds.Level1;

import de.wgkassel.curstle.RoomContent.MiniMap;
import de.wgkassel.curstle.enemy.Boss;
import de.wgkassel.curstle.enemy.BossBar;
import de.wgkassel.curstle.gates.BaseGate;
import de.wgkassel.curstle.gates.BossToOneGate;
import de.wgkassel.curstle.gates.StairsRoomGate;
import de.wgkassel.curstle.player.Hearts;
import de.wgkassel.curstle.player.Player;

public class RoomBoss extends BaseWorld {

    public RoomBoss(Player player) {
        super(player);
        generateWalls();

        if (BaseGate.stair1ToBoss) {
            addObject(player, 200, 200);
        } else {
            addObject(player, 850, 900);
        }

        Boss boss = new Boss();
        addObject(boss, 200, 200);

        addObject(boss.getImageActor(), 200, 200);

        BossToOneGate doorD2 = new BossToOneGate();
        addObject(doorD2, 850, 1000);
        doorD2.getImage().rotate(180);

        addObject(new StairsRoomGate(), 200, 10);

        Hearts hearts = new Hearts();
        addObject(hearts, 1300, 35);

        if (Boss.lives > 0) {
            BossBar bossBar = new BossBar();
            addObject(bossBar, 850, 150);
        }
        MiniMap miniMap = new MiniMap(4);
        addObject(miniMap, 1600, 900);

        generatePotions();

    }

}
