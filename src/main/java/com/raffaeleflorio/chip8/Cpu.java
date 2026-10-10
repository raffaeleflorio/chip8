package com.raffaeleflorio.chip8;

import java.util.List;
import java.util.function.Consumer;

public interface Cpu {

    default void execute(Instruction... instructions) {
        this.execute(List.of(instructions));
    }

    void execute(List<Instruction> instructions);

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

    interface Register<VALUE> {

        VALUE value();

        void value(VALUE value);

        void add(VALUE value);

        void sub(VALUE value);

        void or(VALUE value);

        void and(VALUE value);

        void xor(VALUE value);

        void shiftRight(Consumer<Bit> leastSignificantBit);

        void shiftLeft(Consumer<Bit> mostSignificantBit);
    }
}
