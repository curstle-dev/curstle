package de.wgkassel.curstle;
import greenfoot.Actor;

public class Torch extends Actor {
    private long wait = System.currentTimeMillis();
    int counter = 1;

    @Override
    public void act() {
        super.act();
        counter();
        switchImage();
    }

    public void switchImage() {
        switch (counter) {
            case 1:
                setImage("torch1.gif");
                this.getImage().scale(100, 280);
                break;
            case 2:
                setImage("torch2.gif");
                this.getImage().scale(100, 280);
                break;
            case 3:
                setImage("torch3.gif");
                this.getImage().scale(100, 280);
                break;
            case 4:
                setImage("torch4.gif");
                this.getImage().scale(100, 280);
                break;
        }
    }

    public void counter() {
        if (System.currentTimeMillis() - wait > 200){
            counter++;
            wait = System.currentTimeMillis();
            if (counter == 5){
                counter = 1;
            }
        }
    }
}
