package com.raffaeleflorio.chip8.instruction;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.UnsignedByte;

/**
 * 8XY3 opcode: Sets VX to VX xor VY
 */
public final class SetVxToVxXorVy implements Instruction {

    private final RegisterId firstRegisterId; // X
    private final RegisterId secondRegisterId; // Y

    public SetVxToVxXorVy(final RegisterId firstRegisterId, final RegisterId secondRegisterId) {
        this.firstRegisterId = firstRegisterId;
        this.secondRegisterId = secondRegisterId;
    }

    @Override
    public void execute(final Cpu cpu) {
        this.firstRegister(cpu).xor(this.secondRegisterValue(cpu));
    }

    private Cpu.Register<UnsignedByte> firstRegister(final Cpu cpu) {
        return this.firstRegisterId.toRegister(cpu);
    }

    private UnsignedByte secondRegisterValue(final Cpu cpu) {
        return this.secondRegisterId.toRegister(cpu).value();
    }
}