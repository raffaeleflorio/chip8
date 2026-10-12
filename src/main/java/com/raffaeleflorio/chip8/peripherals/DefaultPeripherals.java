package com.raffaeleflorio.chip8.peripherals;

import com.raffaeleflorio.chip8.Display;
import com.raffaeleflorio.chip8.Peripherals;

public final class DefaultPeripherals implements Peripherals {

    private final Display display;

    public DefaultPeripherals() {
        this(new FxDisplay());
    }

    DefaultPeripherals(final Display display) {
        this.display = display;
    }

    @Override
    public Display display() {
        return this.display;
    }
}
