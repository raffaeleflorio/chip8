package com.raffaeleflorio.chip8.peripherals;

import com.raffaeleflorio.chip8.Timer;
import com.raffaeleflorio.chip8.UnsignedByte;

final class DelayTimer implements Timer {

    private final static Integer FREQUENCY_HZ = 60;
    private final static UnsignedByte ZERO = new UnsignedByte.From(0);

    private UnsignedByte value;
    private Thread decreasingValueThread;

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
        if (this.decreasingValueThread == null) {
            this.decreasingValueThread = new Thread(new PeriodicTimerValueDecrease(this, FREQUENCY_HZ));
            this.decreasingValueThread.start();
        } else if (previousValue.equals(ZERO) && !this.value.equals(ZERO)) {
            this.notify();
        }
    }
}
