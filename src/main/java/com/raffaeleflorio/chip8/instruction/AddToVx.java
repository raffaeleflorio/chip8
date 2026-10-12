package com.raffaeleflorio.chip8.instruction;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.Stack;
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
    public void execute(final Cpu.Registers registers, final Stack stack) {
        this.register(registers).add(this.value);
    }

    private Cpu.Register<UnsignedByte> register(final Cpu.Registers registers) {
        return this.registerId.toRegister(registers);
    }

    @Override
    public String toString() {
        return "7%X%02X".formatted(this.registerId.ordinal(), this.value.asInteger());
    }
}