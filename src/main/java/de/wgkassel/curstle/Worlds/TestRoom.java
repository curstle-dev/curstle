package de.wgkassel.curstle.Worlds;

import de.wgkassel.curstle.Worlds.Level1.BaseWorld;
import de.wgkassel.curstle.enemy.Boss2;

public class TestRoom extends BaseWorld {
    public TestRoom() {
        prepare();
    }

    public void prepare() {
        Boss2 boss2 = new Boss2();
        addObject(boss2, this.getWidth()/2, this.getHeight()/2);
    }
}
