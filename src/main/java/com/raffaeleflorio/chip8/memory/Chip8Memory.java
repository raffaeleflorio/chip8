package com.raffaeleflorio.chip8.memory;

import com.raffaeleflorio.chip8.Memory;
import com.raffaeleflorio.chip8.UnsignedByte;
import com.raffaeleflorio.chip8.UnsignedShort;

import java.nio.file.Path;

public final class Chip8Memory implements Memory {

    private final Memory origin;

    public Chip8Memory() {
        this(
                new SynchronizedMemory(
                        new SimpleMemory(4096)
                )
        );
    }

    Chip8Memory(final Memory origin) {
        this.origin = origin;
    }

    @Override
    public void value(final UnsignedShort address, final UnsignedByte value) {
        this.origin.value(address, value);
    }

    @Override
    public UnsignedByte value(final UnsignedShort address) {
        return this.origin.value(address);
    }

    @Override
    public void load(final UnsignedShort address, final Path dump) {
        this.origin.load(address, dump);
    }
}
