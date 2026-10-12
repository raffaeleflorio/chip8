package com.raffaeleflorio.chip8.instruction;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.Stack;
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
    public void execute(final Cpu.Registers registers, final Stack stack) {
        this.register(registers).value(this.value);
    }

    private Cpu.Register<UnsignedByte> register(final Cpu.Registers registers) {
        return this.registerId.toRegister(registers);
    }

    @Override
    public String toString() {
        return "6%X%02X".formatted(this.registerId.ordinal(), this.value.asInteger());
    }
}