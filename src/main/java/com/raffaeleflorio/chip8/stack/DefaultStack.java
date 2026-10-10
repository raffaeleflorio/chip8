package com.raffaeleflorio.chip8.stack;

import com.raffaeleflorio.chip8.Stack;
import com.raffaeleflorio.chip8.UnsignedShort;

import java.util.LinkedList;

public final class DefaultStack implements Stack {

    private final LinkedList<UnsignedShort> stack;

    public DefaultStack() {
        this(
                new LinkedList<>()
        );
    }

    DefaultStack(final LinkedList<UnsignedShort> stack) {
        this.stack = stack;
    }

    @Override
    public void push(final UnsignedShort address) {
        this.stack.push(address);
    }

    @Override
    public UnsignedShort pop() {
        return this.stack.pop();
    }
}
