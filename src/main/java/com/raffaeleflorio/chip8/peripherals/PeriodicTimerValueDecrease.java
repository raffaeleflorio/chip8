package com.raffaeleflorio.chip8.peripherals;

import com.raffaeleflorio.chip8.Timer;
import com.raffaeleflorio.chip8.UnsignedByte;

final class PeriodicTimerValueDecrease implements Runnable {

    private final static UnsignedByte ZERO = new UnsignedByte.From(0);
    private final static UnsignedByte ONE = new UnsignedByte.From(1);

    private final Timer timer;
    private final Integer frequencyHz;

    PeriodicTimerValueDecrease(final Timer timer, final Integer frequencyHz) {
        this.timer = timer;
        this.frequencyHz = frequencyHz;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(1000 / this.frequencyHz);
                synchronized (this.timer) {
                    if (!this.timer.value().equals(ZERO)) {
                        this.timer.value(this.timer.value().difference(ONE));
                    }
                    while (this.timer.value().equals(ZERO)) {
                        this.timer.wait();
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}