package com.raffaeleflorio.chip8.instruction;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.Memory;
import com.raffaeleflorio.chip8.Peripherals;
import com.raffaeleflorio.chip8.Stack;
import com.raffaeleflorio.chip8.UnsignedNibble;
import com.raffaeleflorio.chip8.UnsignedShort;

/**
 * FX55 opcode: Stores from V0 to VX (including VX) in memory, starting at address I. I is left unmodified
 */
public final class RegDump implements Instruction {

    private final static UnsignedShort ZERO = new UnsignedShort.From(0);
    private final static UnsignedShort ONE = new UnsignedShort.From(1);

    private final UnsignedNibble lastRegisterId; //X

    public RegDump(final UnsignedNibble lastRegisterId) {
        this.lastRegisterId = lastRegisterId;
    }

    @Override
    public void execute(
            final Cpu.Registers registers,
            final Stack stack,
            final Memory memory,
            final Peripherals peripherals
    ) {
        var offset = ZERO;
        for (var register : registers.vXUpTo(this.lastRegisterId)) {
            memory.value(
                    registers.i().value().sum(offset),
                    register.value()
            );
            offset = offset.sum(ONE);
        }
    }

    @Override
    public String toString() {
        return "F%X55".formatted(this.lastRegisterId.asInteger());
    }
}
