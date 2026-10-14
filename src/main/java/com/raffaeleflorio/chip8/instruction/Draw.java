package com.raffaeleflorio.chip8.instruction;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.Display;
import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.Memory;
import com.raffaeleflorio.chip8.Peripherals;
import com.raffaeleflorio.chip8.Stack;
import com.raffaeleflorio.chip8.UnsignedByte;
import com.raffaeleflorio.chip8.UnsignedNibble;
import com.raffaeleflorio.chip8.UnsignedShort;

import java.util.HashSet;
import java.util.Set;

/**
 * DXYN opcode: Draws a sprite at coordinate (VX, VY) that has a width of 8 pixels and a height of N pixels. Each row of 8 pixels is read as bit-coded starting from memory location I. VF is set to 1 if any screen pixels are flipped from set to unset when the sprite is drawn, and to 0 if that does not happen.
 */
public final class Draw implements Instruction {

    private final static UnsignedByte ZERO = new UnsignedByte.From(0);
    private final static UnsignedByte ONE = new UnsignedByte.From(1);
    private final static Integer SPRITE_WIDTH = 8;

    private final UnsignedNibble firstRegisterId; // X
    private final UnsignedNibble secondRegisterId; // Y
    private final UnsignedNibble spriteHeight; // N

    public Draw(
            final UnsignedNibble firstRegisterId,
            final UnsignedNibble secondRegisterId,
            final UnsignedNibble spriteHeight
    ) {
        this.firstRegisterId = firstRegisterId;
        this.secondRegisterId = secondRegisterId;
        this.spriteHeight = spriteHeight;
    }

    @Override
    public void execute(
            final Cpu.Registers registers,
            final Stack stack,
            final Memory memory,
            final Peripherals peripherals
    ) {
        peripherals.display().xor(
                this.coordinates(registers, memory),
                () -> registers.vF().value(ONE),
                () -> registers.vF().value(ZERO)
        );
    }

    private Set<Display.Coordinate> coordinates(final Cpu.Registers registers, final Memory memory) {
        var result = new HashSet<Display.Coordinate>();
        for (var y = 0; y < this.spriteHeight.asInteger(); y++) {
            var row = memory.value(registers.i().value().sum(new UnsignedShort.From(y)));
            for (var x = 0; x < SPRITE_WIDTH; x++) {
                var coordinate = this.coordinate(registers, x, y);
                row.mostSignificantBit().ifOneOrElse(
                        () -> result.add(coordinate),
                        () -> {
                        }
                );
                row = row.shiftedLeft();
            }
        }
        return result;
    }

    private Display.Coordinate coordinate(final Cpu.Registers registers, final Integer x, final Integer y) {
        return new Display.Coordinate(
                this.firstRegisterValue(registers).sum(new UnsignedByte.From(x)).asInteger(),
                this.secondRegisterValue(registers).sum(new UnsignedByte.From(y)).asInteger()
        );
    }

    private UnsignedByte firstRegisterValue(final Cpu.Registers registers) {
        return registers.vX(this.firstRegisterId).value();
    }

    private UnsignedByte secondRegisterValue(final Cpu.Registers registers) {
        return registers.vX(this.secondRegisterId).value();
    }

    @Override
    public String toString() {
        return "D%X%X%X".formatted(
                this.firstRegisterId.asInteger(),
                this.secondRegisterId.asInteger(),
                this.spriteHeight.asInteger()
        );
    }
}
