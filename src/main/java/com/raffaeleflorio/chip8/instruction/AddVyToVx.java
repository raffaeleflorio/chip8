package com.raffaeleflorio.chip8.instruction;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.UnsignedByte;

/**
 * 8XY4 opcode: Adds VY to VX. VF is set to 1 when there's an overflow, and to 0 when there is not
 */
public final class AddVyToVx implements Instruction {

    private final static UnsignedByte ZERO = new UnsignedByte.From(0);
    private final static UnsignedByte ONE = new UnsignedByte.From(1);

    private final RegisterId firstRegisterId; // X
    private final RegisterId secondRegisterId; // Y

    public AddVyToVx(final RegisterId firstRegisterId, final RegisterId secondRegisterId) {
        this.firstRegisterId = firstRegisterId;
        this.secondRegisterId = secondRegisterId;
    }


    @Override
    public void execute(final Cpu cpu) {
        var firstRegister = this.firstRegister(cpu);
        firstRegister.add(this.secondRegisterValue(cpu));
        firstRegister.value().ifOverflowedOrElse(
                () -> cpu.vF().value(ONE),
                () -> cpu.vF().value(ZERO)
        );
    }

    private Cpu.Register<UnsignedByte> firstRegister(final Cpu cpu) {
        return this.firstRegisterId.toRegister(cpu);
    }

    private UnsignedByte secondRegisterValue(final Cpu cpu) {
        return this.secondRegisterId.toRegister(cpu).value();
    }

    @Override
    public String toString() {
        return "8%X%X4".formatted(this.firstRegisterId.ordinal(), this.secondRegisterId.ordinal());
    }
}