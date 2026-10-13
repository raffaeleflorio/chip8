package com.raffaeleflorio.chip8.instruction;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.Peripherals;
import com.raffaeleflorio.chip8.Stack;
import com.raffaeleflorio.chip8.UnsignedByte;
import com.raffaeleflorio.chip8.UnsignedNibble;
import com.raffaeleflorio.chip8.UnsignedShort;

/**
 * 9XY0 opcode: Skips the next instruction if VX does not equal VY
 */
public final class IfVxNeqVy implements Instruction {

    private final static UnsignedShort FOUR = new UnsignedShort.From(4);

    private final UnsignedNibble firstRegisterId; // X
    private final UnsignedNibble secondRegisterId; // Y

    public IfVxNeqVy(
            final UnsignedNibble firstRegisterId,
            final UnsignedNibble secondRegisterId
    ) {
        this.firstRegisterId = firstRegisterId;
        this.secondRegisterId = secondRegisterId;
    }

    @Override
    public void execute(final Cpu.Registers registers, final Stack stack, final Peripherals peripherals) {
        if (!this.firstRegisterValue(registers).equals(this.secondRegisterValue(registers))) {
            registers.pc().add(FOUR);
        }
    }

    private UnsignedByte firstRegisterValue(final Cpu.Registers registers) {
        return registers.vX(this.firstRegisterId).value();
    }

    private UnsignedByte secondRegisterValue(final Cpu.Registers registers) {
        return registers.vX(this.secondRegisterId).value();
    }

    @Override
    public String toString() {
        return "9%X%X0".formatted(this.firstRegisterId.asInteger(), this.secondRegisterId.asInteger());
    }
}