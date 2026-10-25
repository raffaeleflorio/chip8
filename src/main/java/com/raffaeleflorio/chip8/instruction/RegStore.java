package com.raffaeleflorio.chip8.instruction;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.Memory;
import com.raffaeleflorio.chip8.Peripherals;
import com.raffaeleflorio.chip8.Stack;
import com.raffaeleflorio.chip8.UnsignedNibble;
import com.raffaeleflorio.chip8.UnsignedShort;

/**
 * FX55 opcode: Stores from V0 to VX (including VX) in memory, starting at address I. I is incremented by X + 1
 */
public final class RegStore implements Instruction {

    private final static UnsignedShort ONE = new UnsignedShort.From(1);

    private final UnsignedNibble lastRegisterId; //X

    public RegStore(final UnsignedNibble lastRegisterId) {
        this.lastRegisterId = lastRegisterId;
    }

    @Override
    public void execute(
            final Cpu.Registers registers,
            final Stack stack,
            final Memory memory,
            final Peripherals peripherals
    ) {
        for (var register : registers.vXUpTo(this.lastRegisterId)) {
            memory.value(registers.i().value(), register.value());
            registers.i().add(ONE);
        }
    }

    @Override
    public void ifJumpOrElse(final Runnable whenJump, final Runnable whenNotJump) {
        whenNotJump.run();
    }

    @Override
    public String toString() {
        return "F%X55".formatted(this.lastRegisterId.asInteger());
    }
}
