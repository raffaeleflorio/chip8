package com.raffaeleflorio.chip8.instruction;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.Memory;
import com.raffaeleflorio.chip8.Peripherals;
import com.raffaeleflorio.chip8.Stack;
import com.raffaeleflorio.chip8.UnsignedByte;
import com.raffaeleflorio.chip8.UnsignedNibble;

/**
 * 8XYE opcode: Shifts VX to the left by 1, then sets VF to 1 if the most significant bit of VX prior to that shift was set, or to 0 if it was unset
 */
public final class ShiftLeftVx implements Instruction {

    private final UnsignedNibble registerId; // X, Y

    public ShiftLeftVx(final UnsignedNibble registerId) {
        this.registerId = registerId;
    }

    @Override
    public void execute(
            final Cpu.Registers registers,
            final Stack stack,
            final Memory memory,
            final Peripherals peripherals
    ) {
        this.register(registers).shiftLeft(
                msb -> registers.vF().value(msb.asUnsignedByte())
        );
    }

    private Cpu.Register<UnsignedByte> register(final Cpu.Registers registers) {
        return registers.vX(this.registerId);
    }

    @Override
    public String toString() {
        return "8%X%XE".formatted(this.registerId.asInteger(), this.registerId.asInteger());
    }
}