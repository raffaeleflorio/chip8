package com.raffaeleflorio.chip8.instruction;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.Instruction;
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
    public void execute(final Cpu cpu) {
        cpu.i().add(this.registerValue(cpu).asUnsignedShort());
    }

    private UnsignedByte registerValue(final Cpu cpu) {
        return this.registerId.toRegister(cpu).value();
    }
}