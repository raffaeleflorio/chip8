package com.raffaeleflorio.chip8.instruction;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.Peripherals;
import com.raffaeleflorio.chip8.Stack;
import com.raffaeleflorio.chip8.UnsignedByte;
import com.raffaeleflorio.chip8.UnsignedNibble;
import com.raffaeleflorio.chip8.UnsignedShort;

/**
 * 4XNN opcode: Skips the next instruction if VX does not equal NN
 */
public final class IfVxNeq implements Instruction {

    private final static UnsignedShort FOUR = new UnsignedShort.From(4);

    private final UnsignedNibble registerId; // X
    private final UnsignedByte value; // NN

    public IfVxNeq(final UnsignedNibble registerId, final UnsignedByte value) {
        this.registerId = registerId;
        this.value = value;
    }

    @Override
    public void execute(final Cpu.Registers registers, final Stack stack, final Peripherals peripherals) {
        if (!this.registerValue(registers).equals(this.value)) {
            registers.pc().add(FOUR);
        }
    }

    private UnsignedByte registerValue(final Cpu.Registers registers) {
        return registers.vX(this.registerId).value();
    }

    @Override
    public String toString() {
        return "4%X%02X".formatted(this.registerId.asInteger(), this.value.asInteger());
    }
}