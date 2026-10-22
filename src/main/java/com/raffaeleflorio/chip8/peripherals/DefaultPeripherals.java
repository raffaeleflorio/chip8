package com.raffaeleflorio.chip8.peripherals;

import com.raffaeleflorio.chip8.Display;
import com.raffaeleflorio.chip8.HexKeyboard;
import com.raffaeleflorio.chip8.Peripherals;
import com.raffaeleflorio.chip8.Timer;
import javafx.stage.Stage;

public final class DefaultPeripherals implements Peripherals {

    private final Display display;
    private final Timer delayTimer;
    private final Timer soundTimer;
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
                new FxSoundTimer(),
                new FxHexKeyboard(stage)
        );
    }

    DefaultPeripherals(
            final Display display,
            final DelayTimer delayTimer,
            final Timer soundTimer,
            final HexKeyboard hexKeyboard
    ) {
        this.display = display;
        this.delayTimer = delayTimer;
        this.soundTimer = soundTimer;
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
    public Timer soundTimer() {
        return this.soundTimer;
    }

    @Override
    public HexKeyboard hexKeyboard() {
        return this.hexKeyboard;
    }
}
