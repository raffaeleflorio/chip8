package com.raffaeleflorio.chip8.instruction;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.Memory;
import com.raffaeleflorio.chip8.Peripherals;
import com.raffaeleflorio.chip8.Stack;
import com.raffaeleflorio.chip8.UnsignedByte;
import com.raffaeleflorio.chip8.UnsignedNibble;

/**
 * 8XY3 opcode: Sets VX to VX xor VY
 */
public final class SetVxToVxXorVy implements Instruction {

    private final UnsignedNibble firstRegisterId; // X
    private final UnsignedNibble secondRegisterId; // Y

    public SetVxToVxXorVy(final UnsignedNibble firstRegisterId, final UnsignedNibble secondRegisterId) {
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
        this.firstRegister(registers).xor(this.secondRegisterValue(registers));
    }

    private Cpu.Register<UnsignedByte> firstRegister(final Cpu.Registers registers) {
        return registers.vX(this.firstRegisterId);
    }

    private UnsignedByte secondRegisterValue(final Cpu.Registers registers) {
        return registers.vX(this.secondRegisterId).value();
    }

    @Override
    public String toString() {
        return "8%X%X3".formatted(this.firstRegisterId.asInteger(), this.secondRegisterId.asInteger());
    }
}