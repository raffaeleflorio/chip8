package com.raffaeleflorio.chip8.cpu;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.Peripherals;
import com.raffaeleflorio.chip8.UnsignedShort;

import java.util.List;

final class InitializedPeripherals implements Cpu {

    private final Cpu origin;
    private final Peripherals peripherals;

    InitializedPeripherals(final Cpu origin, final Peripherals peripherals) {
        this.origin = origin;
        this.peripherals = peripherals;
    }

    @Override
    public void execute(final UnsignedShort initialPC, final List<Instruction> instructions) {
        this.initialized(
                () -> this.origin.execute(initialPC, instructions)
        );
    }

    private void initialized(final Runnable runnable) {
        this.peripherals.display().on();
        this.peripherals.hexKeyboard().on();
        runnable.run();
        this.peripherals.hexKeyboard().off();
        this.peripherals.display().off();
    }
}
