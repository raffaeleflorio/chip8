package com.raffaeleflorio.chip8;

public interface OpCodeTable {

    Instruction instruction(UnsignedShort opcode);
}
