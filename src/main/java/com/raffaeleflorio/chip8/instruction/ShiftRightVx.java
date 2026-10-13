package com.raffaeleflorio.chip8.instruction;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.Memory;
import com.raffaeleflorio.chip8.Peripherals;
import com.raffaeleflorio.chip8.Stack;
import com.raffaeleflorio.chip8.UnsignedByte;
import com.raffaeleflorio.chip8.UnsignedNibble;

/**
 * 8XY6 opcode: Shifts VX to the right by 1, then stores the least significant bit of VX prior to the shift into VF
 */
public final class ShiftRightVx implements Instruction {

    private final UnsignedNibble registerId; // X, Y

    public ShiftRightVx(final UnsignedNibble registerId) {
        this.registerId = registerId;
    }

    @Override
    public void execute(
            final Cpu.Registers registers,
            final Stack stack,
            final Memory memory,
            final Peripherals peripherals
    ) {
        this.register(registers).shiftRight(
                lsb -> registers.vF().value(lsb.asUnsignedByte())
        );
    }

    private Cpu.Register<UnsignedByte> register(final Cpu.Registers registers) {
        return registers.vX(this.registerId);
    }

    @Override
    public String toString() {
        return "8%X%X6".formatted(this.registerId.asInteger(), this.registerId.asInteger());
    }
}