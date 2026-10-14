package com.raffaeleflorio.chip8.instruction;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.Memory;
import com.raffaeleflorio.chip8.Peripherals;
import com.raffaeleflorio.chip8.Stack;
import com.raffaeleflorio.chip8.UnsignedNibble;
import com.raffaeleflorio.chip8.UnsignedShort;

/**
 * FX65 opcode: Fills from V0 to VX (including VX) with values from memory, starting at address I. I is left unmodified
 */
public final class RegLoad implements Instruction {

    private final static UnsignedShort ZERO = new UnsignedShort.From(0);
    private final static UnsignedShort ONE = new UnsignedShort.From(1);

    private final UnsignedNibble lastRegisterId; //X

    public RegLoad(final UnsignedNibble lastRegisterId) {
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
            register.value(
                    memory.value(registers.i().value().sum(offset))
            );
            offset = offset.sum(ONE);
        }
    }

    @Override
    public String toString() {
        return "F%X65".formatted(this.lastRegisterId.asInteger());
    }
}
