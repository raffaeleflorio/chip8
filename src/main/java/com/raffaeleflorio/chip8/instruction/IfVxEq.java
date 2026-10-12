package com.raffaeleflorio.chip8.instruction;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.Stack;
import com.raffaeleflorio.chip8.UnsignedByte;
import com.raffaeleflorio.chip8.UnsignedShort;

/**
 * 3XNN opcode: Skips the next instruction if VX equals NN
 */
public final class IfVxEq implements Instruction {

    private final static UnsignedShort FOUR = new UnsignedShort.From(4);

    private final RegisterId registerId; // X
    private final UnsignedByte value; // NN

    public IfVxEq(final RegisterId registerId, final UnsignedByte value) {
        this.registerId = registerId;
        this.value = value;
    }

    @Override
    public void execute(final Cpu.Registers registers, final Stack stack) {
        if (this.registerValue(registers).equals(this.value)) {
            registers.pc().add(FOUR);
        }
    }

    private UnsignedByte registerValue(final Cpu.Registers registers) {
        return this.registerId.toRegister(registers).value();
    }

    @Override
    public String toString() {
        return "3%X%02X".formatted(this.registerId.ordinal(), this.value.asInteger());
    }
}