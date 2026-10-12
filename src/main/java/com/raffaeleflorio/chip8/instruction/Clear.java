package com.raffaeleflorio.chip8.instruction;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.Peripherals;
import com.raffaeleflorio.chip8.Stack;

/**
 * 00E0 opcode: Clears the screen
 */
public final class Clear implements Instruction {

    @Override
    public void execute(final Cpu.Registers registers, final Stack stack, final Peripherals peripherals) {
        peripherals.display().clear();
    }

    @Override
    public String toString() {
        return "00E0";
    }
}
