package de.wgkassel.curstle.Worlds;

import de.wgkassel.curstle.MysteriousMan;
import de.wgkassel.curstle.Torch;
import de.wgkassel.curstle.Worlds.Level1.BaseWorld;
import de.wgkassel.curstle.enemy.Boss2.Boss2;
import de.wgkassel.curstle.enemy.Boss2.Boss2Bar;
import de.wgkassel.curstle.enemy.ShotgunMan;
import de.wgkassel.curstle.player.Hearts;
import de.wgkassel.curstle.player.Player;

public class TestRoom extends BaseWorld {
    public TestRoom(Player player) {
        super(player);
        prepare();
    }

    public void prepare() {

        addObject(player, getWidth() / 2, getHeight() / 10 * 9);


        Hearts hearts = new Hearts();
        addObject(hearts, 1300, 35);


        Torch torch = new Torch();
        addObject(torch, getWidth()/3, getHeight()/3);

        ShotgunMan shotgunMan = new ShotgunMan();
        addObject(shotgunMan, 500, 500);
    }
}
