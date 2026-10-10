package com.raffaeleflorio.chip8;

public interface Stack {

    void push(UnsignedShort address);

    UnsignedShort pop();
}
