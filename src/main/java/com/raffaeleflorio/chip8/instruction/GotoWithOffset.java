package com.raffaeleflorio.chip8.instruction;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.Instruction;
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
    public void execute(final Cpu cpu) {
        cpu.pc().value(
                this.value.sum(cpu.v0().value().asUnsignedShort())
        );
    }
}