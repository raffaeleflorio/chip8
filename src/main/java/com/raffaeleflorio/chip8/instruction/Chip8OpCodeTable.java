package com.raffaeleflorio.chip8.instruction;

import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.OpCodeTable;
import com.raffaeleflorio.chip8.UnsignedByte;
import com.raffaeleflorio.chip8.UnsignedNibble;
import com.raffaeleflorio.chip8.UnsignedShort;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public final class Chip8OpCodeTable implements OpCodeTable {

    private final static Map<Predicate<UnsignedShort>, Function<UnsignedShort, Instruction>> TABLE = Map.ofEntries(
            Map.entry(
                    opcode -> startsWith(opcode, 0x0) && !is(opcode, 0x00E0) && !is(opcode, 0x00EE),
                    x -> new NoOp()
            ),
            Map.entry(
                    opcode -> is(opcode, 0x00E0),
                    x -> new Clear()
            ),
            Map.entry(
                    opcode -> is(opcode, 0x00EE),
                    x -> new Return()
            ),
            Map.entry(
                    opcode -> startsWith(opcode, 0x1),
                    opcode -> new Goto(address(opcode))
            ),
            Map.entry(
                    opcode -> startsWith(opcode, 0x2),
                    opcode -> new Call(address(opcode))
            ),
            Map.entry(
                    opcode -> startsWith(opcode, 0x3),
                    opcode -> new IfVxEq(vX(opcode), scalar(opcode))
            ),
            Map.entry(
                    opcode -> startsWith(opcode, 0x4),
                    opcode -> new IfVxNeq(vX(opcode), scalar(opcode))
            ),
            Map.entry(
                    opcode -> startsWith(opcode, 0x5) && endsWith(opcode, 0x0),
                    opcode -> new IfVxEqVy(vX(opcode), vY(opcode))
            ),
            Map.entry(
                    opcode -> startsWith(opcode, 0x6),
                    opcode -> new SetVx(vX(opcode), scalar(opcode))
            ),
            Map.entry(
                    opcode -> startsWith(opcode, 0x7),
                    opcode -> new AddToVx(vX(opcode), scalar(opcode))
            ),
            Map.entry(
                    opcode -> startsWith(opcode, 0x8) && endsWith(opcode, 0x0),
                    opcode -> new SetVxToVy(vX(opcode), vY(opcode))
            ),
            Map.entry(
                    opcode -> startsWith(opcode, 0x8) && endsWith(opcode, 0x1),
                    opcode -> new SetVxToVxOrVy(vX(opcode), vY(opcode))
            ),
            Map.entry(
                    opcode -> startsWith(opcode, 0x8) && endsWith(opcode, 0x2),
                    opcode -> new SetVxToVxAndVy(vX(opcode), vY(opcode))
            ),
            Map.entry(
                    opcode -> startsWith(opcode, 0x8) && endsWith(opcode, 0x3),
                    opcode -> new SetVxToVxXorVy(vX(opcode), vY(opcode))
            ),
            Map.entry(
                    opcode -> startsWith(opcode, 0x8) && endsWith(opcode, 0x4),
                    opcode -> new AddVyToVx(vX(opcode), vY(opcode))
            ),
            Map.entry(
                    opcode -> startsWith(opcode, 0x8) && endsWith(opcode, 0x5),
                    opcode -> new SubVyFromVx(vX(opcode), vY(opcode))
            ),
            Map.entry(
                    opcode -> startsWith(opcode, 0x8) && endsWith(opcode, 0x6),
                    opcode -> new ShiftRightVx(vX(opcode), vY(opcode))
            ),
            Map.entry(
                    opcode -> startsWith(opcode, 0x8) && endsWith(opcode, 0x7),
                    opcode -> new SetVxToVyMinusVx(vX(opcode), vY(opcode))
            ),
            Map.entry(
                    opcode -> startsWith(opcode, 0x8) && endsWith(opcode, 0xE),
                    opcode -> new ShiftLeftVx(vX(opcode), vY(opcode))
            ),
            Map.entry(
                    opcode -> startsWith(opcode, 0x9) && endsWith(opcode, 0x0),
                    opcode -> new IfVxNeqVy(vX(opcode), vY(opcode))
            ),
            Map.entry(
                    opcode -> startsWith(opcode, 0xA),
                    opcode -> new SetI(address(opcode))
            ),
            Map.entry(
                    opcode -> startsWith(opcode, 0xB),
                    opcode -> new GotoWithOffset(address(opcode))
            ),
            Map.entry(
                    opcode -> startsWith(opcode, 0xC),
                    opcode -> new SetVxToMaskedRand(vX(opcode), scalar(opcode))
            ),
            Map.entry(
                    opcode -> startsWith(opcode, 0xD),
                    opcode -> new Draw(vX(opcode), vY(opcode), nibble(opcode))
            ),
            Map.entry(
                    opcode -> startsWith(opcode, 0xE) && endsWith(opcode, 0x9E),
                    opcode -> new IfKeyEq(vX(opcode))
            ),
            Map.entry(
                    opcode -> startsWith(opcode, 0xE) && endsWith(opcode, 0xA1),
                    opcode -> new IfKeyNeq(vX(opcode))
            ),
            Map.entry(
                    opcode -> startsWith(opcode, 0xF) && endsWith(opcode, 0x7),
                    opcode -> new GetDelay(vX(opcode))
            ),
            Map.entry(
                    opcode -> startsWith(opcode, 0xF) && endsWith(opcode, 0xA),
                    opcode -> new GetKey(vX(opcode))
            ),
            Map.entry(
                    opcode -> startsWith(opcode, 0xF) && endsWith(opcode, 0x15),
                    opcode -> new SetDelay(vX(opcode))
            ),
            Map.entry(
                    opcode -> startsWith(opcode, 0xF) && endsWith(opcode, 0x18),
                    opcode -> new SetSound(vX(opcode))
            ),
            Map.entry(
                    opcode -> startsWith(opcode, 0xF) && endsWith(opcode, 0x1E),
                    opcode -> new AddVxToI(vX(opcode))
            ),
            Map.entry(
                    opcode -> startsWith(opcode, 0xF) && endsWith(opcode, 0x29),
                    opcode -> new SpriteAddr(vX(opcode))
            ),
            Map.entry(
                    opcode -> startsWith(opcode, 0xF) && endsWith(opcode, 0x33),
                    opcode -> new BCDVx(vX(opcode))
            ),
            Map.entry(
                    opcode -> startsWith(opcode, 0xF) && endsWith(opcode, 0x55),
                    opcode -> new RegStore(vX(opcode))
            ),
            Map.entry(
                    opcode -> startsWith(opcode, 0xF) && endsWith(opcode, 0x65),
                    opcode -> new RegLoad(vX(opcode))
            )
    );

    private static Boolean startsWith(final UnsignedShort opcode, final Integer prefix) {
        return opcode.and(new UnsignedShort.From(0xF000)).equals(new UnsignedShort.From(prefix << 12));
    }

    private static Boolean is(final UnsignedShort opcode, final Integer value) {
        return opcode.equals(new UnsignedShort.From(value));
    }

    private static UnsignedShort address(final UnsignedShort opcode) {
        return opcode.and(new UnsignedShort.From(0x0FFF));
    }

    private static UnsignedNibble vX(final UnsignedShort opcode) {
        return opcode.mostSignificantByte().leastSignificantNibble();
    }

    private static UnsignedByte scalar(final UnsignedShort opcode) {
        return opcode.leastSignificantByte();
    }

    private static Boolean endsWith(final UnsignedShort opcode, final Integer suffix) {
        if (suffix < 0x10) {
            return endsWith(opcode, 0xF, suffix);
        }
        return endsWith(opcode, 0xFF, suffix);
    }

    private static Boolean endsWith(final UnsignedShort opcode, final Integer mask, final Integer suffix) {
        return opcode.and(new UnsignedShort.From(mask)).equals(new UnsignedShort.From(suffix));
    }

    private static UnsignedNibble vY(final UnsignedShort opcode) {
        return opcode.leastSignificantByte().mostSignificantNibble();
    }

    private static UnsignedNibble nibble(final UnsignedShort opcode) {
        return opcode.leastSignificantByte().leastSignificantNibble();
    }

    private final OpCodeTable origin;

    public Chip8OpCodeTable() {
        this(
                new SimpleOpCodeTable(TABLE)
        );
    }

    Chip8OpCodeTable(final OpCodeTable origin) {
        this.origin = origin;
    }

    @Override
    public Instruction instruction(final UnsignedShort opcode) {
        return this.origin.instruction(opcode);
    }
}
