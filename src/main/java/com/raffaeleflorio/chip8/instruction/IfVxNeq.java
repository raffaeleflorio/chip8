package com.raffaeleflorio.chip8.instruction;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.UnsignedByte;
import com.raffaeleflorio.chip8.UnsignedShort;

/**
 * 4XNN opcode: Skips the next instruction if VX does not equal NN
 */
public final class IfVxNeq implements Instruction {

    private final static UnsignedShort FOUR = new UnsignedShort.From(4);

    private final RegisterId registerId; // X
    private final UnsignedByte value; // NN

    public IfVxNeq(final RegisterId registerId, final UnsignedByte value) {
        this.registerId = registerId;
        this.value = value;
    }

    @Override
    public void execute(final Cpu cpu) {
        if (!this.registerValue(cpu).equals(this.value)) {
            cpu.pc().add(FOUR);
        }
    }

    private UnsignedByte registerValue(final Cpu cpu) {
        return this.registerId.toRegister(cpu).value();
    }

    @Override
    public String toString() {
        return "4%X%02X".formatted(this.registerId.ordinal(), this.value.asInteger());
    }
}