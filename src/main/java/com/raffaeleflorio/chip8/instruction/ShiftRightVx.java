package com.raffaeleflorio.chip8.instruction;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.Stack;
import com.raffaeleflorio.chip8.UnsignedByte;

/**
 * 8XY6 opcode: Shifts VX to the right by 1, then stores the least significant bit of VX prior to the shift into VF
 */
public final class ShiftRightVx implements Instruction {

    private final RegisterId registerId; // X, Y

    public ShiftRightVx(final RegisterId registerId) {
        this.registerId = registerId;
    }

    @Override
    public void execute(final Cpu.Registers registers, final Stack stack) {
        this.register(registers).shiftRight(
                lsb -> registers.vF().value(lsb.asUnsignedByte())
        );
    }

    private Cpu.Register<UnsignedByte> register(final Cpu.Registers registers) {
        return this.registerId.toRegister(registers);
    }

    @Override
    public String toString() {
        return "8%X%X6".formatted(this.registerId.ordinal(), this.registerId.ordinal());
    }
}