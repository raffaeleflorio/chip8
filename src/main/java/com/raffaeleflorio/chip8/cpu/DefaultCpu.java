package com.raffaeleflorio.chip8.cpu;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.Memory;
import com.raffaeleflorio.chip8.Peripherals;
import com.raffaeleflorio.chip8.Stack;
import com.raffaeleflorio.chip8.UnsignedShort;

import java.util.List;

public final class DefaultCpu implements Cpu {

    private final Cpu origin;

    public DefaultCpu(
            final Stack stack,
            final Memory memory,
            final Peripherals peripherals
    ) {
        this(
                new SynchronizedCpu(
                        new InitializedMemory(
                                new InitializedPeripheralsCpu(
                                        new SimpleCpu(
                                                stack,
                                                memory,
                                                peripherals
                                        ),
                                        peripherals
                                ),
                                memory
                        )
                )
        );
    }

    DefaultCpu(final Cpu origin) {
        this.origin = origin;
    }

    @Override
    public void execute(final UnsignedShort initialPC, final List<Instruction> instructions) {
        this.origin.execute(initialPC, instructions);
    }
}
