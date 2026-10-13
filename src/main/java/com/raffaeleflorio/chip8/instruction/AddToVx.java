package com.raffaeleflorio.chip8.instruction;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.Peripherals;
import com.raffaeleflorio.chip8.Stack;
import com.raffaeleflorio.chip8.UnsignedByte;
import com.raffaeleflorio.chip8.UnsignedNibble;

/**
 * 7XNN opcode: Adds NN to VX
 */
public final class AddToVx implements Instruction {

    private final UnsignedNibble registerId; // X
    private final UnsignedByte value; // NN

    public AddToVx(final UnsignedNibble registerId, final UnsignedByte value) {
        this.registerId = registerId;
        this.value = value;
    }

    @Override
    public void execute(final Cpu.Registers registers, final Stack stack, final Peripherals peripherals) {
        this.register(registers).add(this.value);
    }

    private Cpu.Register<UnsignedByte> register(final Cpu.Registers registers) {
        return registers.vX(this.registerId);
    }

    @Override
    public String toString() {
        return "7%X%02X".formatted(this.registerId.asInteger(), this.value.asInteger());
    }
}