package com.raffaeleflorio.chip8.instruction;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.UnsignedShort;

/**
 * 1NNN opcode: Jumps to address NNN
 */
public final class Goto implements Instruction {

    private final UnsignedShort address; // NNN

    public Goto(final UnsignedShort address) {
        this.address = address;
    }

    @Override
    public void execute(final Cpu cpu) {
        cpu.pc().value(this.address);
    }

    @Override
    public String toString() {
        return "1%03X".formatted(this.address.asInteger());
    }
}
