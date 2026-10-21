package com.raffaeleflorio.chip8.memory;

import com.raffaeleflorio.chip8.Memory;
import com.raffaeleflorio.chip8.UnsignedByte;
import com.raffaeleflorio.chip8.UnsignedShort;

import java.nio.file.Path;

final class SynchronizedMemory implements Memory {

    private final Memory origin;

    SynchronizedMemory(final Memory origin) {
        this.origin = origin;
    }

    @Override
    public synchronized void value(final UnsignedShort address, final UnsignedByte value) {
        this.origin.value(address, value);
    }

    @Override
    public synchronized UnsignedByte value(final UnsignedShort address) {
        return this.origin.value(address);
    }

    @Override
    public synchronized void load(final UnsignedShort address, final Path dump) {
        this.origin.load(address, dump);
    }
}
