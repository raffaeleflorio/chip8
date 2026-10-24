package com.raffaeleflorio.chip8.stack;

import com.raffaeleflorio.chip8.Stack;
import com.raffaeleflorio.chip8.UnsignedShort;

final class SynchronizedStack implements Stack {

    private final Stack origin;

    SynchronizedStack(final Stack origin) {
        this.origin = origin;
    }

    @Override
    public synchronized void push(final UnsignedShort address) {
        this.origin.push(address);
    }

    @Override
    public synchronized UnsignedShort pop() {
        return this.origin.pop();
    }
}
