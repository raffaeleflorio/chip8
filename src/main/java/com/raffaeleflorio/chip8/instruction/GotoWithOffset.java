package com.raffaeleflorio.chip8.instruction;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.Memory;
import com.raffaeleflorio.chip8.Peripherals;
import com.raffaeleflorio.chip8.Stack;
import com.raffaeleflorio.chip8.UnsignedShort;

/**
 * BNNN opcode: Jumps to the address NNN plus V0
 */
public final class GotoWithOffset implements Instruction {

    private final UnsignedShort value; // NNN

    public GotoWithOffset(final UnsignedShort value) {
        this.value = value;
    }

    @Override
    public void execute(
            final Cpu.Registers registers,
            final Stack stack,
            final Memory memory,
            final Peripherals peripherals
    ) {
        registers.pc().value(
                this.value.sum(registers.v0().value().asUnsignedShort())
        );
    }

    @Override
    public String toString() {
        return "B%03X".formatted(this.value.asInteger());
    }
}