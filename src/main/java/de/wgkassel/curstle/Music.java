package de.wgkassel.curstle;

import greenfoot.GreenfootSound;

public class Music {

    private static Music moi;
    private boolean playing;
    private GreenfootSound backgroundMusic = new GreenfootSound("backgroundmusic.wav");

    private Music() {

    }

    public static Music getMoi() {
        if (moi == null) {
            moi = new Music();
        }
        return moi;
    }

    public void started() {
        if (!playing) {
            backgroundMusic.playLoop();
            backgroundMusic.setVolume(100);
            playing = true;
        }
    }

    public void stopped() {
        if (playing) {
            backgroundMusic.stop();
            playing = false;
        }
    }

    public boolean isPlaying() {
        return this.playing;
    }
}
