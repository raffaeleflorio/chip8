package com.raffaeleflorio.chip8.instruction;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.Memory;
import com.raffaeleflorio.chip8.Peripherals;
import com.raffaeleflorio.chip8.Stack;
import com.raffaeleflorio.chip8.UnsignedByte;
import com.raffaeleflorio.chip8.UnsignedNibble;

/**
 * 8XY7 opcode: Sets VX to VY minus VX. VF is set to 0 when there's an underflow, and 1 when there is not
 */
public final class SetVxToVyMinusVx implements Instruction {

    private final static UnsignedByte ZERO = new UnsignedByte.From(0);
    private final static UnsignedByte ONE = new UnsignedByte.From(1);

    private final UnsignedNibble firstRegisterId; // X
    private final UnsignedNibble secondRegisterId; // Y

    public SetVxToVyMinusVx(final UnsignedNibble firstRegisterId, final UnsignedNibble secondRegisterId) {
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
        var firstRegister = this.firstRegister(registers);
        firstRegister.value(
                this.secondRegisterValue(registers).difference(firstRegister.value())
        );
        firstRegister.value().ifUnderflowedOrElse(
                () -> registers.vF().value(ZERO),
                () -> registers.vF().value(ONE)
        );
    }

    private Cpu.Register<UnsignedByte> firstRegister(final Cpu.Registers registers) {
        return registers.vX(this.firstRegisterId);
    }

    private UnsignedByte secondRegisterValue(final Cpu.Registers registers) {
        return registers.vX(this.secondRegisterId).value();
    }

    @Override
    public void ifJumpOrElse(final Runnable whenJump, final Runnable whenNotJump) {
        whenNotJump.run();
    }

    @Override
    public String toString() {
        return "8%X%X7".formatted(this.firstRegisterId.asInteger(), this.secondRegisterId.asInteger());
    }
}