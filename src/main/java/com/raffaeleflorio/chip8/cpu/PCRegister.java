package com.raffaeleflorio.chip8.cpu;

import com.raffaeleflorio.chip8.Bit;
import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.UnsignedShort;

import java.util.function.Consumer;

final class PCRegister implements Cpu.Register<UnsignedShort> {

    private UnsignedShort value;

    PCRegister(final UnsignedShort value) {
        this.value = value;
    }

    @Override
    public UnsignedShort value() {
        return this.value;
    }

    @Override
    public void value(final UnsignedShort value) {
        this.value = value;
    }

    @Override
    public void add(final UnsignedShort value) {
        this.value = this.value.sum(value);
    }

    @Override
    public void sub(final UnsignedShort value) {
        throw this.unsupportedOperation("sub");
    }

    private RuntimeException unsupportedOperation(final String operation) {
        return new UnsupportedOperationException("I don't support the %s operation!".formatted(operation));
    }

    @Override
    public void or(final UnsignedShort value) {
        throw this.unsupportedOperation("or");
    }

    @Override
    public void and(final UnsignedShort value) {
        throw this.unsupportedOperation("and");
    }

    @Override
    public void xor(final UnsignedShort value) {
        throw this.unsupportedOperation("xor");
    }

    @Override
    public void shiftRight(final Consumer<Bit> leastSignificantBit) {
        throw this.unsupportedOperation("shiftRight");
    }

    @Override
    public void shiftLeft(final Consumer<Bit> mostSignificantBit) {
        throw this.unsupportedOperation("shiftLeft");
    }
}
