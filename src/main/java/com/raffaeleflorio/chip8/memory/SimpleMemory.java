package com.raffaeleflorio.chip8.memory;

import com.raffaeleflorio.chip8.Memory;
import com.raffaeleflorio.chip8.UnsignedByte;
import com.raffaeleflorio.chip8.UnsignedShort;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

final class SimpleMemory implements Memory {

    private final static UnsignedByte ZERO = new UnsignedByte.From(0);

    private final UnsignedByte[] memory;

    public SimpleMemory(final Integer size) {
        this(
                new UnsignedByte[size]
        );
    }

    SimpleMemory(final UnsignedByte[] memory) {
        this.memory = memory;
    }

    @Override
    public void value(final UnsignedShort address, final UnsignedByte value) {
        if (address.asInteger() >= this.memory.length) {
            throw new IllegalArgumentException();
        }
        this.memory[address.asInteger()] = value;
    }

    @Override
    public UnsignedByte value(final UnsignedShort address) {
        if (address.asInteger() >= this.memory.length) {
            throw new IllegalArgumentException();
        }
        var result = this.memory[address.asInteger()];
        return result != null ? result : ZERO;
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
