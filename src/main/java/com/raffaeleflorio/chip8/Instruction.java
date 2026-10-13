package com.raffaeleflorio.chip8;

public interface Instruction {

    void execute(Cpu.Registers registers, Stack stack, Memory memory, Peripherals peripherals);
}
