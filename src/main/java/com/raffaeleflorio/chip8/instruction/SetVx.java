package com.raffaeleflorio.chip8.instruction;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.UnsignedByte;

/**
 * 6XNN opcode: Sets VX to NN
 */
public final class SetVx implements Instruction {

    private final RegisterId registerId; // X
    private final UnsignedByte value; // NN

    public SetVx(final RegisterId registerId, final UnsignedByte value) {
        this.registerId = registerId;
        this.value = value;
    }

    @Override
    public void execute(final Cpu cpu) {
        this.register(cpu).value(this.value);
    }

    private Cpu.Register<UnsignedByte> register(final Cpu cpu) {
        return this.registerId.toRegister(cpu);
    }
}