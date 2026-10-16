package com.raffaeleflorio.chip8.peripherals;

import com.raffaeleflorio.chip8.Display;
import com.raffaeleflorio.chip8.HexKeyboard;
import com.raffaeleflorio.chip8.Peripherals;
import com.raffaeleflorio.chip8.Timer;
import javafx.stage.Stage;

public final class DefaultPeripherals implements Peripherals {

    private final Display display;
    private final Timer delayTimer;
    private final HexKeyboard hexKeyboard;

    public DefaultPeripherals() {
        this(
                new Stage()
        );
    }

    DefaultPeripherals(final Stage stage) {
        this(
                new FxDisplay(stage),
                new DelayTimer(),
                new FxHexKeyboard(stage)
        );
    }

    DefaultPeripherals(final Display display, final DelayTimer delayTimer, final HexKeyboard hexKeyboard) {
        this.display = display;
        this.delayTimer = delayTimer;
        this.hexKeyboard = hexKeyboard;
    }

    @Override
    public Display display() {
        return this.display;
    }

    @Override
    public Timer delayTimer() {
        return this.delayTimer;
    }

    @Override
    public HexKeyboard hexKeyboard() {
        return this.hexKeyboard;
    }
}
