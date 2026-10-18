package com.raffaeleflorio.chip8.instruction;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.Memory;
import com.raffaeleflorio.chip8.Peripherals;
import com.raffaeleflorio.chip8.Stack;
import com.raffaeleflorio.chip8.UnsignedByte;
import com.raffaeleflorio.chip8.UnsignedNibble;

/**
 * FX0A opcode: A key press is awaited, and then stored in VX. Blocking operation
 */
public final class GetKey implements Instruction {

    private final UnsignedNibble registerId; // X

    public GetKey(final UnsignedNibble registerId) {
        this.registerId = registerId;
    }

    @Override
    public void execute(
            final Cpu.Registers registers,
            final Stack stack,
            final Memory memory,
            final Peripherals peripherals
    ) {
        this.register(registers).add(this.key(peripherals));
    }

    private Cpu.Register<UnsignedByte> register(final Cpu.Registers registers) {
        return registers.vX(this.registerId);
    }

    private UnsignedByte key(final Peripherals peripherals) {
        return peripherals.hexKeyboard().key().asUnsignedNibble().asUnsignedByte();
    }

    @Override
    public void ifJumpOrElse(final Runnable whenJump, final Runnable whenNotJump) {
        whenNotJump.run();
    }

    @Override
    public String toString() {
        return "F%X0A".formatted(this.registerId.asInteger());
    }
}