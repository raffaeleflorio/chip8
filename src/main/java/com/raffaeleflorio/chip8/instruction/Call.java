package com.raffaeleflorio.chip8.instruction;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.Peripherals;
import com.raffaeleflorio.chip8.Stack;
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
    public void execute(final Cpu.Registers registers, final Stack stack, final Peripherals peripherals) {
        stack.push(registers.pc().value());
        registers.pc().value(this.subroutine);
    }

    @Override
    public String toString() {
        return "2%03X".formatted(this.subroutine.asInteger());
    }
}
