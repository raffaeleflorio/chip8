package com.raffaeleflorio.chip8.stack;

import com.raffaeleflorio.chip8.Stack;
import com.raffaeleflorio.chip8.UnsignedShort;

import java.util.NoSuchElementException;

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
        if (this.sp.equals(MAX_DEPTH)) {
            throw new IllegalStateException();
        }
        this.stack[sp++] = address;
    }

    @Override
    public UnsignedShort pop() {
        if (this.sp.equals(0)) {
            throw new NoSuchElementException();
        }
        return this.stack[--sp];
    }
}
