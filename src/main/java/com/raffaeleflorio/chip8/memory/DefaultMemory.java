package com.raffaeleflorio.chip8.memory;

import com.raffaeleflorio.chip8.Memory;
import com.raffaeleflorio.chip8.UnsignedByte;
import com.raffaeleflorio.chip8.UnsignedShort;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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

    @Override
    public void load(final UnsignedShort address, final Path dump) {
        try {
            var content = Files.readAllBytes(dump);
            for (var i = 0; i < content.length; i++) {
                var offset = new UnsignedShort.From(i);
                var ubyte = new UnsignedByte.From(content[i]);
                this.value(address.sum(offset), ubyte);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
