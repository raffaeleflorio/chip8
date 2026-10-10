package com.raffaeleflorio.chip8.instruction;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.UnsignedShort;

/**
 * 2NNN opcode: Calls subroutine at NNN
 */
public final class Call implements Instruction {

    private final UnsignedShort subroutine; // NNN

    public Call(final UnsignedShort subroutine) {
        this.subroutine = subroutine;
    }

    @Override
    public void execute(final Cpu cpu) {
        cpu.stack().push(cpu.pc().value());
        cpu.pc().value(this.subroutine);
    }
}
