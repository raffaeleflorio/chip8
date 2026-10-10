package com.raffaeleflorio.chip8.stack;

import com.raffaeleflorio.chip8.Stack;
import com.raffaeleflorio.chip8.UnsignedShort;

public final class DefaultStack implements Stack {

    private final static Integer MAX_DEPTH = 12;

    private final UnsignedShort[] stack;
    private Integer sp;

    public DefaultStack() {
        this(
                new UnsignedShort[MAX_DEPTH],
                0
        );
    }

    DefaultStack(final UnsignedShort[] stack, final Integer sp) {
        this.stack = stack;
        this.sp = sp;
    }

    @Override
    public void push(final UnsignedShort address) {
        this.stack[sp++] = address;
    }

    @Override
    public UnsignedShort pop() {
        return this.stack[--sp];
    }
}
