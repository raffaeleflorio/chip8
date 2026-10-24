package com.raffaeleflorio.chip8.stack;

import com.raffaeleflorio.chip8.Stack;
import com.raffaeleflorio.chip8.UnsignedShort;

import java.util.NoSuchElementException;

final class SimpleStack implements Stack {

    private final UnsignedShort[] stack;
    private Integer sp;

    SimpleStack(final Integer maxDepth) {
        this(
                new UnsignedShort[maxDepth],
                0
        );
    }

    SimpleStack(final UnsignedShort[] stack, final Integer sp) {
        this.stack = stack;
        this.sp = sp;
    }

    @Override
    public void push(final UnsignedShort address) {
        if (this.sp.equals(this.stack.length)) {
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
