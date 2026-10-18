package com.raffaeleflorio.chip8.cpu;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.Peripherals;
import com.raffaeleflorio.chip8.UnsignedShort;

import java.util.List;

final class InitializedPeripheralsCpu implements Cpu {

    private final Cpu origin;
    private final Peripherals peripherals;

    InitializedPeripheralsCpu(final Cpu origin, final Peripherals peripherals) {
        this.origin = origin;
        this.peripherals = peripherals;
    }

    @Override
    public void execute(final UnsignedShort initialPC, final List<Instruction> instructions) {
        this.peripherals.display().on();
        this.peripherals.hexKeyboard().on();
        this.origin.execute(initialPC, instructions);
        this.peripherals.hexKeyboard().off();
        this.peripherals.display().off();
    }
}
