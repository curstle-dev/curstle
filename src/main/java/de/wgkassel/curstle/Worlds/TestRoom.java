package de.wgkassel.curstle.Worlds;

import de.wgkassel.curstle.Worlds.Level1.BaseWorld;
import de.wgkassel.curstle.enemy.Boss2;
import de.wgkassel.curstle.player.Player;

public class TestRoom extends BaseWorld {
    public TestRoom(Player player) {
        super(player);
        prepare();
    }

    public void prepare() {
        Boss2 boss2 = new Boss2();
        addObject(boss2, this.getWidth()/2, this.getHeight()/2);
        addObject(player, getWidth() / 2, getHeight() / 10 * 9);
    }
}