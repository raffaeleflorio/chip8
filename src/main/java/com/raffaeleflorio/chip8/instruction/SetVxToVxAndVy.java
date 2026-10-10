package com.raffaeleflorio.chip8.instruction;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.UnsignedByte;

/**
 * 8XY2 opcode: Sets VX to VX and VY
 */
public final class SetVxToVxAndVy implements Instruction {

    private final RegisterId firstRegisterId; // X
    private final RegisterId secondRegisterId; // Y

    public SetVxToVxAndVy(final RegisterId firstRegisterId, final RegisterId secondRegisterId) {
        this.firstRegisterId = firstRegisterId;
        this.secondRegisterId = secondRegisterId;
    }

    @Override
    public void execute(final Cpu cpu) {
        this.firstRegister(cpu).and(this.secondRegisterValue(cpu));
    }

    private Cpu.Register<UnsignedByte> firstRegister(final Cpu cpu) {
        return this.firstRegisterId.toRegister(cpu);
    }

    private UnsignedByte secondRegisterValue(final Cpu cpu) {
        return this.secondRegisterId.toRegister(cpu).value();
    }
}