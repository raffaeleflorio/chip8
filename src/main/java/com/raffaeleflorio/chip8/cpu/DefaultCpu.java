package com.raffaeleflorio.chip8.cpu;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.Memory;
import com.raffaeleflorio.chip8.Peripherals;
import com.raffaeleflorio.chip8.Stack;
import com.raffaeleflorio.chip8.UnsignedShort;

import java.util.List;

public final class DefaultCpu implements Cpu {

    private final static UnsignedShort PC_ZERO = new UnsignedShort.From(0x200);
    private final static UnsignedShort TWO = new UnsignedShort.From(2);

    private final Stack stack;
    private final Memory memory;
    private final Peripherals peripherals;
    private final Registers registers;

    public DefaultCpu(final Stack stack, final Memory memory, final Peripherals peripherals) {
        this(
                stack,
                memory,
                peripherals,
                new DefaultRegisters()
        );
    }

    DefaultCpu(
            final Stack stack,
            final Memory memory,
            final Peripherals peripherals,
            final Registers registers
    ) {
        this.stack = stack;
        this.memory = memory;
        this.peripherals = peripherals;
        this.registers = registers;
    }

    @Override
    public synchronized void execute(final List<Instruction> instructions) {
        this.peripherals.display().on();
        this.registers.pc().value(PC_ZERO);
        while (this.pcValueAsIndex() < instructions.size()) {
            var currentPc = this.registers.pc().value();
            instructions.get(this.pcValueAsIndex()).execute(this.registers, this.stack, this.memory, this.peripherals);
            if (currentPc.equals(this.registers.pc().value())) {
                this.registers.pc().add(TWO);
            }
        }
        this.peripherals.display().off();
    }

    private Integer pcValueAsIndex() {
        return (this.registers.pc().value().asInteger() - PC_ZERO.asInteger()) / 2;
    }
}
