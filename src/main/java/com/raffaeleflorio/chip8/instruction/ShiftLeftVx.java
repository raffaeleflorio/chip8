package com.raffaeleflorio.chip8.instruction;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.Memory;
import com.raffaeleflorio.chip8.Peripherals;
import com.raffaeleflorio.chip8.Stack;
import com.raffaeleflorio.chip8.UnsignedByte;
import com.raffaeleflorio.chip8.UnsignedNibble;

/**
 * 8XYE opcode: Sets VY to VX shifted to the left by 1. Sets VF to 1 if the most significant bit of VX is set, or to 0 if it is not
 */
public final class ShiftLeftVx implements Instruction {

    private final UnsignedNibble firstRegisterId; // X
    private final UnsignedNibble secondRegisterId; // Y

    public ShiftLeftVx(final UnsignedNibble firstRegisterId, final UnsignedNibble secondRegisterId) {
        this.firstRegisterId = firstRegisterId;
        this.secondRegisterId = secondRegisterId;
    }

    @Override
    public void execute(
            final Cpu.Registers registers,
            final Stack stack,
            final Memory memory,
            final Peripherals peripherals
    ) {
        var firstRegisterValue = this.firstRegisterValue(registers);
        this.secondRegister(registers).value(firstRegisterValue.shiftedLeft());
        registers.vF().value(firstRegisterValue.mostSignificantBit().asUnsignedByte());
    }

    private UnsignedByte firstRegisterValue(final Cpu.Registers registers) {
        return registers.vX(this.secondRegisterId).value();
    }

    private Cpu.Register<UnsignedByte> secondRegister(final Cpu.Registers registers) {
        return registers.vX(this.firstRegisterId);
    }

    @Override
    public String toString() {
        return "8%X%XE".formatted(this.firstRegisterId.asInteger(), this.secondRegisterId.asInteger());
    }
}