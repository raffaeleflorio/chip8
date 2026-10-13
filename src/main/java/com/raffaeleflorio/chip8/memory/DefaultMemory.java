package com.raffaeleflorio.chip8.memory;

import com.raffaeleflorio.chip8.Memory;
import com.raffaeleflorio.chip8.UnsignedByte;
import com.raffaeleflorio.chip8.UnsignedShort;

public final class DefaultMemory implements Memory {

    private final static Integer SIZE = 4096;

    private final UnsignedByte[] memory;

    public DefaultMemory() {
        this(
                new UnsignedByte[SIZE]
        );
    }

    DefaultMemory(final UnsignedByte[] memory) {
        this.memory = memory;
    }

    @Override
    public void value(final UnsignedShort address, final UnsignedByte value) {
        if (address.asInteger() >= SIZE) {
            throw new IllegalArgumentException();
        }
        this.memory[address.asInteger()] = value;
    }

    @Override
    public UnsignedByte value(final UnsignedShort address) {
        if (address.asInteger() >= SIZE) {
            throw new IllegalArgumentException();
        }
        return this.memory[address.asInteger()];
    }
}
