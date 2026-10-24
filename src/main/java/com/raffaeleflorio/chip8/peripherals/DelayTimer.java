package com.raffaeleflorio.chip8.peripherals;

import com.raffaeleflorio.chip8.Timer;
import com.raffaeleflorio.chip8.UnsignedByte;

final class DelayTimer implements Timer {

    private final static Integer FREQUENCY_HZ = 60;
    private final static UnsignedByte ZERO = new UnsignedByte.From(0);

    private UnsignedByte value;
    private Thread valueThread;

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
        if (previousValue.equals(ZERO) && !this.value.equals(ZERO)) {
            this.notify();
        }
    }

    @Override
    public synchronized void on() {
        if (this.valueThread == null) {
            this.valueThread = new ValueThread();
            this.valueThread.start();
        }
    }

    @Override
    public synchronized void off() {
        if (this.valueThread != null) {
            this.valueThread.interrupt();
            this.valueThread = null;
        }
    }

    final class ValueThread extends Thread {

        private final static UnsignedByte ZERO = new UnsignedByte.From(0);
        private final static UnsignedByte ONE = new UnsignedByte.From(1);

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    synchronized (DelayTimer.this) {
                        while (DelayTimer.this.value().equals(ZERO)) {
                            DelayTimer.this.wait();
                        }

                        DelayTimer.this.value = DelayTimer.this.value().difference(ONE);
                    }
                    Thread.sleep(1000 / FREQUENCY_HZ);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
