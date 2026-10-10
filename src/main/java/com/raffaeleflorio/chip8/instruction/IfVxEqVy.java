package com.raffaeleflorio.chip8.instruction;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.UnsignedByte;
import com.raffaeleflorio.chip8.UnsignedShort;

/**
 * 5XY0 opcode: Skips the next instruction if VX equals VY
 */
public final class IfVxEqVy implements Instruction {

    private final static UnsignedShort FOUR = new UnsignedShort.From(4);

    private final RegisterId firstRegisterId; // X
    private final RegisterId secondRegisterId; // Y

    public IfVxEqVy(
            final RegisterId firstRegisterId,
            final RegisterId secondRegisterId
    ) {
        this.firstRegisterId = firstRegisterId;
        this.secondRegisterId = secondRegisterId;
    }


    @Override
    public void execute(final Cpu cpu) {
        if (this.firstRegisterValue(cpu).equals(this.secondRegisterValue(cpu))) {
            cpu.pc().add(FOUR);
        }
    }

    private UnsignedByte firstRegisterValue(final Cpu cpu) {
        return this.firstRegisterId.toRegister(cpu).value();
    }

    private UnsignedByte secondRegisterValue(final Cpu cpu) {
        return this.secondRegisterId.toRegister(cpu).value();
    }
}