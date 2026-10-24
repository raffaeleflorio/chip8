package com.raffaeleflorio.chip8;

public interface Timer extends Peripheral {

    UnsignedByte value();

    void value(UnsignedByte value);
}
