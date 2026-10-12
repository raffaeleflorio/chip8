package com.raffaeleflorio.chip8.instruction;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.Peripherals;
import com.raffaeleflorio.chip8.Stack;
import com.raffaeleflorio.chip8.UnsignedByte;

/**
 * 8XY5 opcode: VY is subtracted from VX. VF is set to 0 when there's an underflow, and 1 when there is not
 */
public final class SubVyFromVx implements Instruction {

    private final static UnsignedByte ZERO = new UnsignedByte.From(0);
    private final static UnsignedByte ONE = new UnsignedByte.From(1);

    private final RegisterId firstRegisterId; // X
    private final RegisterId secondRegisterId; // Y

    public SubVyFromVx(final RegisterId firstRegisterId, final RegisterId secondRegisterId) {
        this.firstRegisterId = firstRegisterId;
        this.secondRegisterId = secondRegisterId;
    }

    @Override
    public void execute(final Cpu.Registers registers, final Stack stack, final Peripherals peripherals) {
        var firstRegister = this.firstRegister(registers);
        firstRegister(registers).sub(this.secondRegisterValue(registers));
        firstRegister.value().ifUnderflowedOrElse(
                () -> registers.vF().value(ZERO),
                () -> registers.vF().value(ONE)
        );
    }

    private Cpu.Register<UnsignedByte> firstRegister(final Cpu.Registers registers) {
        return this.firstRegisterId.toRegister(registers);
    }

    private UnsignedByte secondRegisterValue(final Cpu.Registers registers) {
        return this.secondRegisterId.toRegister(registers).value();
    }

    @Override
    public String toString() {
        return "8%X%X5".formatted(this.firstRegisterId.ordinal(), this.secondRegisterId.ordinal());
    }
}