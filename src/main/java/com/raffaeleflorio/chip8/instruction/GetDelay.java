package com.raffaeleflorio.chip8.instruction;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.Memory;
import com.raffaeleflorio.chip8.Peripherals;
import com.raffaeleflorio.chip8.Stack;
import com.raffaeleflorio.chip8.UnsignedNibble;

/**
 * FX07 opcode: Sets VX to the value of the delay timer
 */
public final class GetDelay implements Instruction {

    private final UnsignedNibble registerId; // X

    public GetDelay(final UnsignedNibble registerId) {
        this.registerId = registerId;
    }

    @Override
    public void execute(
            final Cpu.Registers registers,
            final Stack stack,
            final Memory memory,
            final Peripherals peripherals
    ) {
        registers.vX(this.registerId).value(peripherals.delayTimer().value());
    }

    @Override
    public String toString() {
        return "F%X07".formatted(this.registerId.asInteger());
    }
}
