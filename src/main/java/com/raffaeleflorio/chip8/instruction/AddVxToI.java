package com.raffaeleflorio.chip8.instruction;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.Memory;
import com.raffaeleflorio.chip8.Peripherals;
import com.raffaeleflorio.chip8.Stack;
import com.raffaeleflorio.chip8.UnsignedByte;
import com.raffaeleflorio.chip8.UnsignedNibble;

/**
 * FX1E opcode: Adds VX to I
 */
public final class AddVxToI implements Instruction {

    private final UnsignedNibble registerId; // X

    public AddVxToI(final UnsignedNibble registerId) {
        this.registerId = registerId;
    }

    @Override
    public void execute(
            final Cpu.Registers registers,
            final Stack stack,
            final Memory memory,
            final Peripherals peripherals
    ) {
        registers.i().add(registers.vX(this.registerId).value().asUnsignedShort());
    }

    private UnsignedByte registerValue(final Cpu.Registers registers) {
        return registers.vX(this.registerId).value();
    }

    @Override
    public String toString() {
        return "F%X1E".formatted(this.registerId.asInteger());
    }
}