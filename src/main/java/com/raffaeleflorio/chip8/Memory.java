package com.raffaeleflorio.chip8;

public interface Memory {

    void value(UnsignedShort address, UnsignedByte value);

    UnsignedByte value(UnsignedShort address);
}
