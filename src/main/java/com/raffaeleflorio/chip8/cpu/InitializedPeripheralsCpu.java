package com.raffaeleflorio.chip8.cpu;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.Peripherals;

import java.util.List;

final class InitializedPeripheralsCpu implements Cpu {

    private final Cpu origin;
    private final Peripherals peripherals;

    InitializedPeripheralsCpu(final Cpu origin, final Peripherals peripherals) {
        this.origin = origin;
        this.peripherals = peripherals;
    }

    @Override
    public void execute(final List<Instruction> instructions) {
        this.peripherals.display().on();
        this.origin.execute(instructions);
        this.peripherals.display().off();
    }
}
