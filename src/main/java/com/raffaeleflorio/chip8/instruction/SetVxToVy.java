package com.raffaeleflorio.chip8.instruction;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.Stack;
import com.raffaeleflorio.chip8.UnsignedByte;

/**
 * 8XY0 opcode: Sets VX to the value of VY
 */
public final class SetVxToVy implements Instruction {

    private final RegisterId firstRegisterId; // X
    private final RegisterId secondRegisterId; // Y

    public SetVxToVy(final RegisterId firstRegisterId, final RegisterId secondRegisterId) {
        this.firstRegisterId = firstRegisterId;
        this.secondRegisterId = secondRegisterId;
    }

    @Override
    public void execute(final Cpu.Registers registers, final Stack stack) {
        this.firstRegister(registers).value(this.secondRegisterValue(registers));
    }

    private Cpu.Register<UnsignedByte> firstRegister(final Cpu.Registers registers) {
        return this.firstRegisterId.toRegister(registers);
    }

    private UnsignedByte secondRegisterValue(final Cpu.Registers registers) {
        return this.secondRegisterId.toRegister(registers).value();
    }

    @Override
    public String toString() {
        return "8%X%X0".formatted(this.firstRegisterId.ordinal(), this.secondRegisterId.ordinal());
    }
}