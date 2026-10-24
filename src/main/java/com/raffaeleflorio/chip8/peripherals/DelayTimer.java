package com.raffaeleflorio.chip8.peripherals;

import com.raffaeleflorio.chip8.Timer;
import com.raffaeleflorio.chip8.UnsignedByte;

final class DelayTimer implements Timer {

    private final static Integer FREQUENCY_HZ = 60;
    private final static UnsignedByte ZERO = new UnsignedByte.From(0);

    private UnsignedByte value;
    private Thread valueDecreaseThread;

    DelayTimer() {
        this(ZERO);
    }

    DelayTimer(final UnsignedByte value) {
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
        if (this.valueDecreaseThread == null) {
            this.valueDecreaseThread = new PeriodicValueDecreaseThread();
            this.valueDecreaseThread.start();
        } else if (previousValue.equals(ZERO) && !this.value.equals(ZERO)) {
            this.notify();
        }
    }

    final class PeriodicValueDecreaseThread extends Thread {

        private final static UnsignedByte ZERO = new UnsignedByte.From(0);
        private final static UnsignedByte ONE = new UnsignedByte.From(1);

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(1000 / FREQUENCY_HZ);
                    synchronized (DelayTimer.this) {
                        while (DelayTimer.this.value().equals(ZERO)) {
                            DelayTimer.this.wait();
                        }

                        DelayTimer.this.value = DelayTimer.this.value().difference(ONE);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
