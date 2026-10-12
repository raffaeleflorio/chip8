package com.raffaeleflorio.chip8.instruction;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.Peripherals;
import com.raffaeleflorio.chip8.Stack;
import com.raffaeleflorio.chip8.UnsignedByte;

/**
 * 8XY1 opcode: Sets VX to VX or VY
 */
public final class SetVxToVxOrVy implements Instruction {

    private final RegisterId firstRegisterId; // X
    private final RegisterId secondRegisterId; // Y

    public SetVxToVxOrVy(final RegisterId firstRegisterId, final RegisterId secondRegisterId) {
        this.firstRegisterId = firstRegisterId;
        this.secondRegisterId = secondRegisterId;
    }

    @Override
    public void execute(final Cpu.Registers registers, final Stack stack, final Peripherals peripherals) {
        this.firstRegister(registers).or(this.secondRegisterValue(registers));
    }

    private Cpu.Register<UnsignedByte> firstRegister(final Cpu.Registers registers) {
        return this.firstRegisterId.toRegister(registers);
    }

    private UnsignedByte secondRegisterValue(final Cpu.Registers registers) {
        return this.secondRegisterId.toRegister(registers).value();
    }

    @Override
    public String toString() {
        return "8%X%X1".formatted(this.firstRegisterId.ordinal(), this.secondRegisterId.ordinal());
    }
}