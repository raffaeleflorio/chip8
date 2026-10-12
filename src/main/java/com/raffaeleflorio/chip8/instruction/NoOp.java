package com.raffaeleflorio.chip8.instruction;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.Stack;

/**
 * 0NNN opcode: Calls machine code routine (RCA 1802 for COSMAC VIP) at address NNN
 */
public final class NoOp implements Instruction {

    @Override
    public void execute(final Cpu.Registers registers, final Stack stack) {

    }

    @Override
    public String toString() {
        return "0NNN";
    }
}
