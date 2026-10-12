package com.raffaeleflorio.chip8.instruction;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.Stack;
import com.raffaeleflorio.chip8.UnsignedByte;

/**
 * FX1E opcode: Adds VX to I
 */
public final class AddVxToI implements Instruction {

    private final RegisterId registerId; // X

    public AddVxToI(final RegisterId registerId) {
        this.registerId = registerId;
    }


    @Override
    public void execute(final Cpu.Registers registers, final Stack stack) {
        registers.i().add(this.registerValue(registers).asUnsignedShort());
    }

    private UnsignedByte registerValue(final Cpu.Registers registers) {
        return this.registerId.toRegister(registers).value();
    }

    @Override
    public String toString() {
        return "F%X1E".formatted(this.registerId.ordinal());
    }
}