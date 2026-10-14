package com.raffaeleflorio.chip8.peripherals;

import com.raffaeleflorio.chip8.Display;
import com.raffaeleflorio.chip8.Peripherals;
import com.raffaeleflorio.chip8.Timer;

public final class DefaultPeripherals implements Peripherals {

    private final Display display;
    private final Timer delayTimer;

    public DefaultPeripherals() {
        this(
                new FxDisplay(),
                new DelayTimer()
        );
    }

    DefaultPeripherals(final Display display, final DelayTimer delayTimer) {
        this.display = display;
        this.delayTimer = delayTimer;
    }

    @Override
    public Display display() {
        return this.display;
    }

    @Override
    public Timer delayTimer() {
        return this.delayTimer;
    }
}
