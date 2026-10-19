package com.raffaeleflorio.chip8.cpu;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.Memory;
import com.raffaeleflorio.chip8.Peripherals;
import com.raffaeleflorio.chip8.Stack;
import com.raffaeleflorio.chip8.UnsignedShort;

import java.util.List;

final class SimpleCpu implements Cpu {

    private final static UnsignedShort ZERO = new UnsignedShort.From(0);
    private final static UnsignedShort TWO = new UnsignedShort.From(2);

    private final Stack stack;
    private final Memory memory;
    private final Peripherals peripherals;
    private final Registers registers;

    SimpleCpu(final Stack stack, final Memory memory, final Peripherals peripherals) {
        this(
                stack,
                memory,
                peripherals,
                new DefaultRegisters()
        );
    }

    SimpleCpu(
            final Stack stack,
            final Memory memory,
            final Peripherals peripherals,
            final Registers registers
    ) {
        this.stack = stack;
        this.memory = memory;
        this.peripherals = peripherals;
        this.registers = registers;

    @Override
    public void execute(final List<Instruction> instructions) {
        this.registers.pc().value(ZERO);
        for (var i = this.pcValueAsIndex(); i < instructions.size(); i = this.pcValueAsIndex()) {
            var instruction = instructions.get(i);
            instruction.execute(this.registers, this.stack, this.memory, this.peripherals);
            instruction.ifNotJump(
                    () -> this.registers.pc().add(TWO)
            );
        }
    }

    private Integer pcValueAsIndex() {
        return this.registers.pc().value().asInteger() / 2;
    }
}
