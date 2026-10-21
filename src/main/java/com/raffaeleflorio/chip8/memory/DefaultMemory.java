package com.raffaeleflorio.chip8.memory;

import com.raffaeleflorio.chip8.Memory;
import com.raffaeleflorio.chip8.UnsignedByte;
import com.raffaeleflorio.chip8.UnsignedShort;

import java.nio.file.Path;

public final class DefaultMemory implements Memory {

    private final Memory origin;

    public DefaultMemory() {
        this(
                new SynchronizedMemory(
                        new SimpleMemory()
                )
        );
    }

    DefaultMemory(final Memory origin) {
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
