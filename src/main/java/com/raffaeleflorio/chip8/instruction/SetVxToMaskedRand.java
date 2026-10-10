package com.raffaeleflorio.chip8.instruction;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.UnsignedByte;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * CXNN opcode: Sets VX to the result of a bitwise and operation on a random number and NN
 */
public final class SetVxToMaskedRand implements Instruction {

    private final static Integer MAX_RAND_EXCLUSIVE = 256;

    private final RegisterId registerId; // X
    private final UnsignedByte value; // NN
    private final Random random;

    public SetVxToMaskedRand(final RegisterId registerId, final UnsignedByte value) {
        this(
                registerId,
                value,
                ThreadLocalRandom.current()
        );
    }

    SetVxToMaskedRand(final RegisterId registerId, final UnsignedByte value, final Random random) {
        this.registerId = registerId;
        this.value = value;
        this.random = random;
    }

    @Override
    public void execute(final Cpu cpu) {
        this.register(cpu).value(this.value.and(this.randomUnsignedByte()));
    }

    private Cpu.Register<UnsignedByte> register(final Cpu cpu) {
        return this.registerId.toRegister(cpu);
    }

    private UnsignedByte randomUnsignedByte() {
        return new UnsignedByte.From(this.random.nextInt(MAX_RAND_EXCLUSIVE));
    }
}