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
 * FX33 opcode: Stores the BCD representation of VX, with the hundreds digit in memory at location in I, the tens digit at location I+1, and the ones digit at location I+2
 */
public final class BCDVx implements Instruction {

    private final static UnsignedShort ONE = new UnsignedShort.From(1);
    private final static UnsignedShort TWO = new UnsignedShort.From(2);

    private final UnsignedNibble registerId; // X

    public BCDVx(final UnsignedNibble registerId) {
        this.registerId = registerId;
    }

    @Override
    public void execute(
            final Cpu.Registers registers,
            final Stack stack,
            final Memory memory,
            final Peripherals peripherals
    ) {
        var bcd = this.registerValue(registers).asBCD();
        memory.value(
                registers.i().value(),
                bcd.getFirst()
        );
        memory.value(
                registers.i().value().sum(ONE),
                bcd.get(1)
        );
        memory.value(
                registers.i().value().sum(TWO),
                bcd.get(2)
        );
    }

    private UnsignedByte registerValue(final Cpu.Registers registers) {
        return registers.vX(this.registerId).value();
    }

    @Override
    public String toString() {
        return "F%X33".formatted(this.registerId.asInteger());
    }
}