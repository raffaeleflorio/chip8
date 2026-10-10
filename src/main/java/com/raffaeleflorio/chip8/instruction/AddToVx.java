package com.raffaeleflorio.chip8.instruction;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.UnsignedByte;

/**
 * 7XNN opcode: Adds NN to VX
 */
public final class AddToVx implements Instruction {

    private final RegisterId registerId; // X
    private final UnsignedByte value; // NN

    public AddToVx(final RegisterId registerId, final UnsignedByte value) {
        this.registerId = registerId;
        this.value = value;
    }

    @Override
    public void execute(final Cpu cpu) {
        this.register(cpu).add(this.value);
    }

    private Cpu.Register<UnsignedByte> register(final Cpu cpu) {
        return this.registerId.toRegister(cpu);
    }
}