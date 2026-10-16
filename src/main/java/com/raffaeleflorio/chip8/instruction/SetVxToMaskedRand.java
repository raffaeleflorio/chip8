package com.raffaeleflorio.chip8.instruction;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.Memory;
import com.raffaeleflorio.chip8.Peripherals;
import com.raffaeleflorio.chip8.Stack;
import com.raffaeleflorio.chip8.UnsignedByte;
import com.raffaeleflorio.chip8.UnsignedNibble;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * CXNN opcode: Sets VX to the result of a bitwise and operation on a random number and NN
 */
public final class SetVxToMaskedRand implements Instruction {

    private final static Integer MAX_RAND_EXCLUSIVE = 256;

    private final UnsignedNibble registerId; // X
    private final UnsignedByte mask; // NN
    private final Random random;

    public SetVxToMaskedRand(final UnsignedNibble registerId, final UnsignedByte mask) {
        this(
                registerId,
                mask,
                ThreadLocalRandom.current()
        );
    }

    SetVxToMaskedRand(final UnsignedNibble registerId, final UnsignedByte mask, final Random random) {
        this.registerId = registerId;
        this.mask = mask;
        this.random = random;
    }

    @Override
    public void execute(
            final Cpu.Registers registers,
            final Stack stack,
            final Memory memory,
            final Peripherals peripherals
    ) {
        this.register(registers).value(this.mask.and(this.randomUnsignedByte()));
    }

    private Cpu.Register<UnsignedByte> register(final Cpu.Registers registers) {
        return registers.vX(this.registerId);
    }

    private UnsignedByte randomUnsignedByte() {
        return new UnsignedByte.From(this.random.nextInt(MAX_RAND_EXCLUSIVE));
    }

    @Override
    public String toString() {
        return "C%X%02X".formatted(this.registerId.asInteger(), this.mask.asInteger());
    }
}