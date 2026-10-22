package com.raffaeleflorio.chip8.peripherals;

import com.raffaeleflorio.chip8.Timer;
import com.raffaeleflorio.chip8.UnsignedByte;
import javafx.scene.media.AudioClip;

public final class FxSoundTimer implements Timer {

    private final static Integer FREQUENCY_HZ = 60;
    private final static UnsignedByte ZERO = new UnsignedByte.From(0);
    private final static UnsignedByte ONE = new UnsignedByte.From(1);

    private final AudioClip beep;

    private UnsignedByte value;
    private Thread beepThread;

    public FxSoundTimer() {
        this(new AudioClip("file:src/main/resources/beep.wav"));
    }

    FxSoundTimer(final AudioClip beep) {
        this(beep, ZERO);
    }

    FxSoundTimer(final AudioClip beep, final UnsignedByte value) {
        this.beep = beep;
        this.value = value;
    }

    @Override
    public synchronized UnsignedByte value() {
        return this.value;
    }

    @Override
    public synchronized void value(final UnsignedByte value) {
        var previousValue = this.value;
        this.value = value;
        if (this.beepThread == null) {
            this.beepThread = new BeepThread();
            this.beepThread.start();
        } else if (previousValue.equals(ZERO) && !this.value.equals(ZERO)) {
            this.notify();
        }
    }

    final class BeepThread extends Thread {

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    synchronized (FxSoundTimer.this) {
                        if (!FxSoundTimer.this.value.equals(ZERO)) {
                            FxSoundTimer.this.value = FxSoundTimer.this.value.difference(ONE);
                            if (!FxSoundTimer.this.beep.isPlaying()) {
                                FxSoundTimer.this.beep.play();
                            }
                        }
                        while (FxSoundTimer.this.value.equals(ZERO)) {
                            if (FxSoundTimer.this.beep.isPlaying()) {
                                FxSoundTimer.this.beep.stop();
                            }
                            FxSoundTimer.this.wait();
                        }
                    }
                    Thread.sleep(1000 / FREQUENCY_HZ);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
