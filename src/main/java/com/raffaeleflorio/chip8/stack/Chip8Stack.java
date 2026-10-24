package com.raffaeleflorio.chip8.stack;

import com.raffaeleflorio.chip8.Stack;
import com.raffaeleflorio.chip8.UnsignedShort;

public final class Chip8Stack implements Stack {

    private final Stack origin;

    public Chip8Stack() {
        this(
                new SynchronizedStack(
                        new SimpleStack(12)
                )
        );
    }

    Chip8Stack(final Stack origin) {
        this.origin = origin;
    }

    @Override
    public void push(final UnsignedShort address) {
        this.origin.push(address);
    }

    @Override
    public UnsignedShort pop() {
        return this.origin.pop();
    }
}
