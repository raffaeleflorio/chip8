package com.raffaeleflorio.chip8.peripherals;

import com.raffaeleflorio.chip8.Display;
import com.raffaeleflorio.chip8.Peripherals;
import javafx.stage.Stage;

public final class DefaultPeripherals implements Peripherals {

    private final Display display;

    public DefaultPeripherals(final Stage stage) {
        this(new FxDisplay(stage));
    }

    DefaultPeripherals(final Display display) {
        this.display = display;
    }

    @Override
    public Display display() {
        return this.display;
    }
}
