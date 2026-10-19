package com.raffaeleflorio.chip8.cpu;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.UnsignedShort;

import java.util.List;

final class SynchronizedCpu implements Cpu {

    private final Cpu origin;

    SynchronizedCpu(final Cpu origin) {
        this.origin = origin;
    }

    @Override
    public synchronized void execute(final List<Instruction> instructions) {
        this.origin.execute(instructions);
    }
}
