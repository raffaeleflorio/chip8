package com.raffaeleflorio.chip8;

import java.util.List;
import java.util.function.Consumer;

public interface Cpu {

    default void execute(Instruction... instructions) {
        this.execute(List.of(instructions));
    }

    void execute(List<Instruction> instructions);

    interface Registers {

        default Register<UnsignedByte> vX(UnsignedNibble nibble) {
            return nibble.to(
                    this::v0,
                    this::v1,
                    this::v2,
                    this::v3,
                    this::v4,
                    this::v5,
                    this::v6,
                    this::v7,
                    this::v8,
                    this::v9,
                    this::vA,
                    this::vB,
                    this::vC,
                    this::vD,
                    this::vE,
                    this::vF
            );
        }

        Register<UnsignedByte> v0();

        Register<UnsignedByte> v1();

        Register<UnsignedByte> v2();

        Register<UnsignedByte> v3();

        Register<UnsignedByte> v4();

        Register<UnsignedByte> v5();

        Register<UnsignedByte> v6();

        Register<UnsignedByte> v7();

        Register<UnsignedByte> v8();

        Register<UnsignedByte> v9();

        Register<UnsignedByte> vA();

        Register<UnsignedByte> vB();

        Register<UnsignedByte> vC();

        Register<UnsignedByte> vD();

        Register<UnsignedByte> vE();

        Register<UnsignedByte> vF();

        Register<UnsignedShort> i();

        Register<UnsignedShort> pc();
    }

    interface Register<TO> {

        TO value();

        void value(TO value);

        void add(TO value);

        void sub(TO value);

        void or(TO value);

        void and(TO value);

        void xor(TO value);

        void shiftRight(Consumer<Bit> leastSignificantBit);

        void shiftLeft(Consumer<Bit> mostSignificantBit);
    }
}
