package com.raffaeleflorio.chip8.instruction;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.Memory;
import com.raffaeleflorio.chip8.Peripherals;
import com.raffaeleflorio.chip8.Stack;
import com.raffaeleflorio.chip8.UnsignedByte;
import com.raffaeleflorio.chip8.UnsignedNibble;
import com.raffaeleflorio.chip8.UnsignedShort;

/**
 * FX29 opcode: Sets I to the location of the 4x5 sprite for the character in VX.
 */
public final class SpriteAddr implements Instruction {

    private final static Integer SPRITE_HEIGHT = 5;

    private final UnsignedNibble registerId; // X

    public SpriteAddr(final UnsignedNibble registerId) {
        this.registerId = registerId;
    }

    @Override
    public void execute(
            final Cpu.Registers registers,
            final Stack stack,
            final Memory memory,
            final Peripherals peripherals
    ) {
        registers.i().value(this.spriteAddress(registers));
    }

    private UnsignedShort spriteAddress(final Cpu.Registers registers) {
        return new UnsignedShort.From(SPRITE_HEIGHT * this.registerValue(registers).asInteger());
    }

    private UnsignedByte registerValue(final Cpu.Registers registers) {
        return registers.vX(this.registerId).value();
    }

    @Override
    public String toString() {
        return "F%X29".formatted(this.registerId.asInteger());
    }
}