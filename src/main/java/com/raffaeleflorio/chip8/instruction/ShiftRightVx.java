package com.raffaeleflorio.chip8.instruction;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.Memory;
import com.raffaeleflorio.chip8.Peripherals;
import com.raffaeleflorio.chip8.Stack;
import com.raffaeleflorio.chip8.UnsignedByte;
import com.raffaeleflorio.chip8.UnsignedNibble;

/**
 * 8XY6 opcode: Sets VX to VY shifted to the right by 1. Sets VF to 1 if the least significant bit of VY is set, or to 0 if it is not
 */
public final class ShiftRightVx implements Instruction {

    private final UnsignedNibble firstRegisterId; // X
    private final UnsignedNibble secondRegisterId; // Y

    public ShiftRightVx(final UnsignedNibble firstRegisterId, final UnsignedNibble secondRegisterId) {
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
        var secondRegisterValue = this.secondRegisterValue(registers);
        this.firstRegister(registers).value(secondRegisterValue.shiftedRight());
        registers.vF().value(secondRegisterValue.leastSignificantBit().asUnsignedByte());
    }

    private UnsignedByte secondRegisterValue(final Cpu.Registers registers) {
        return registers.vX(this.secondRegisterId).value();
    }

    private Cpu.Register<UnsignedByte> firstRegister(final Cpu.Registers registers) {
        return registers.vX(this.firstRegisterId);
    }

    @Override
    public void ifJumpOrElse(final Runnable whenJump, final Runnable whenNotJump) {
        whenNotJump.run();
    }

    @Override
    public String toString() {
        return "8%X%X6".formatted(this.firstRegisterId.asInteger(), this.secondRegisterId.asInteger());
    }
}