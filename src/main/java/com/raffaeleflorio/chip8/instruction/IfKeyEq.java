package com.raffaeleflorio.chip8.instruction;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.HexKeyboard;
import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.Memory;
import com.raffaeleflorio.chip8.Peripherals;
import com.raffaeleflorio.chip8.Stack;
import com.raffaeleflorio.chip8.UnsignedByte;
import com.raffaeleflorio.chip8.UnsignedNibble;
import com.raffaeleflorio.chip8.UnsignedShort;

import java.util.Optional;

/**
 * EX9E opcode: Skips the next instruction if the key stored in VX is pressed
 */
public final class IfKeyEq implements Instruction {

    private final static UnsignedShort TWO = new UnsignedShort.From(2);

    private final UnsignedNibble registerId; // X

    public IfKeyEq(final UnsignedNibble registerId) {
        this.registerId = registerId;
    }

    @Override
    public void execute(
            final Cpu.Registers registers,
            final Stack stack,
            final Memory memory,
            final Peripherals peripherals
    ) {
        if (this.keyPressed(registers, peripherals)) {
            registers.pc().add(TWO);
        }
    }

    private Boolean keyPressed(final Cpu.Registers registers, final Peripherals peripherals) {
        return this.keyNow(peripherals)
                .map(this.registerValue(registers)::equals)
                .orElse(false);
    }

    private Optional<UnsignedByte> keyNow(final Peripherals peripherals) {
        return peripherals.hexKeyboard().keyNow()
                .map(HexKeyboard.Key::asUnsignedNibble)
                .map(UnsignedNibble::asUnsignedByte);
    }

    private UnsignedByte registerValue(final Cpu.Registers registers) {
        return registers.vX(this.registerId).value();
    }

    @Override
    public void ifJumpOrElse(final Runnable whenJump, final Runnable whenNotJump) {
        whenNotJump.run();
    }

    @Override
    public String toString() {
        return "E%X9E".formatted(this.registerId.asInteger());
    }
}