package com.raffaeleflorio.chip8.instruction;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.UnsignedByte;

/**
 * 8XYE opcode: Shifts VX to the left by 1, then sets VF to 1 if the most significant bit of VX prior to that shift was set, or to 0 if it was unset
 */
public final class ShiftLeftVx implements Instruction {

    private final RegisterId registerId; // X

    public ShiftLeftVx(final RegisterId registerId) {
        this.registerId = registerId;
    }

    @Override
    public void execute(final Cpu cpu) {
        this.register(cpu).shiftLeft(
                msb -> cpu.vF().value(msb.asUnsignedByte())
        );
    }

    private Cpu.Register<UnsignedByte> register(final Cpu cpu) {
        return this.registerId.toRegister(cpu);
    }
}