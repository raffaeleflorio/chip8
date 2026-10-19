package com.raffaeleflorio.chip8.cpu;

import com.raffaeleflorio.chip8.Bit;
import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.UnsignedByte;

import java.util.function.Consumer;

final class VXRegister implements Cpu.Register<UnsignedByte> {

    private UnsignedByte value;

    VXRegister() {
        this(new UnsignedByte.From());
    }

    VXRegister(final UnsignedByte value) {
        this.value = value;
    }

    @Override
    public UnsignedByte value() {
        return this.value;
    }

    @Override
    public void value(final UnsignedByte value) {
        this.value = value;
    }

    @Override
    public void add(final UnsignedByte value) {
        this.value = this.value.sum(value);
    }

    @Override
    public void sub(final UnsignedByte value) {
        this.value = this.value.difference(value);
    }

    @Override
    public void or(final UnsignedByte value) {
        this.value = this.value.or(value);
    }

    @Override
    public void and(final UnsignedByte value) {
        this.value = this.value.and(value);
    }

    @Override
    public void xor(final UnsignedByte value) {
        this.value = this.value.xor(value);
    }

    @Override
    public void shiftRight(final Consumer<Bit> leastSignificantBit) {
        leastSignificantBit.accept(this.value.leastSignificantBit());
        this.value = this.value.shiftedRight();
    }

    @Override
    public void shiftLeft(final Consumer<Bit> mostSignificantBit) {
        mostSignificantBit.accept(this.value.mostSignificantBit());
        this.value = this.value.shiftedRight();
    }
}
