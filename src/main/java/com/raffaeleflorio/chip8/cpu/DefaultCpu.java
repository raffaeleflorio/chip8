package com.raffaeleflorio.chip8.cpu;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.Stack;
import com.raffaeleflorio.chip8.UnsignedShort;

import java.util.List;

public final class DefaultCpu implements Cpu {

    private final static UnsignedShort PC_ZERO = new UnsignedShort.From(0x200);
    private final static UnsignedShort TWO = new UnsignedShort.From(2);

    private final Stack stack;
    private final Registers registers;

    public DefaultCpu(final Stack stack) {
        this(
                stack,
                new DefaultRegisters()
        );
    }

    DefaultCpu(
            final Stack stack,
            final Registers registers
    ) {
        this.stack = stack;
        this.registers = registers;
    }

    @Override
    public synchronized void execute(final List<Instruction> instructions) {
        this.registers.pc().value(PC_ZERO);
        while (this.pcValueAsIndex() < instructions.size()) {
            var currentPc = this.registers.pc().value();
            instructions.get(this.pcValueAsIndex()).execute(this.registers, this.stack);
            if (currentPc.equals(this.registers.pc().value())) {
                this.registers.pc().add(TWO);
            }
        }
    }

    private Integer pcValueAsIndex() {
        return (this.registers.pc().value().asInteger() - PC_ZERO.asInteger()) / 2;
    }
}
