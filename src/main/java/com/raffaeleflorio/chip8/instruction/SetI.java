package com.raffaeleflorio.chip8.instruction;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.Peripherals;
import com.raffaeleflorio.chip8.Stack;
import com.raffaeleflorio.chip8.UnsignedShort;

/**
 * ANNN opcode: Sets I to NN
 */
public final class SetI implements Instruction {

    private final UnsignedShort value; // NNN

    public SetI(final UnsignedShort value) {
        this.value = value;
    }

    @Override
    public void execute(final Cpu.Registers registers, final Stack stack, final Peripherals peripherals) {
        registers.i().value(this.value);
    }

    @Override
    public String toString() {
        return "A%03X".formatted(this.value.asInteger());
    }
}