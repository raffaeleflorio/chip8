package com.raffaeleflorio.chip8.instruction;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.Stack;
import com.raffaeleflorio.chip8.UnsignedShort;

/**
 * 00EE opcode: Returns from a subroutine
 */
public final class Return implements Instruction {

    private final static UnsignedShort TWO = new UnsignedShort.From(2);

    @Override
    public void execute(final Cpu.Registers registers, final Stack stack) {
        registers.pc().value(stack.pop().sum(TWO));
    }

    @Override
    public String toString() {
        return "00EE";
    }
}
