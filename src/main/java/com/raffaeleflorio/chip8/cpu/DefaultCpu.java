package com.raffaeleflorio.chip8.cpu;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.UnsignedByte;
import com.raffaeleflorio.chip8.UnsignedShort;

import java.util.List;

public final class DefaultCpu implements Cpu {

    private final UnsignedShort ZERO = new UnsignedShort.From(0);
    private final UnsignedShort TWO = new UnsignedShort.From(2);

    private final List<Register<UnsignedByte>> vxRegisters;
    private final List<Register<UnsignedShort>> memoryRegisters;

    public DefaultCpu() {
        this(
                List.of(
                        new VXRegister(),
                        new VXRegister(),
                        new VXRegister(),
                        new VXRegister(),
                        new VXRegister(),
                        new VXRegister(),
                        new VXRegister(),
                        new VXRegister(),
                        new VXRegister(),
                        new VXRegister(),
                        new VXRegister(),
                        new VXRegister(),
                        new VXRegister(),
                        new VXRegister(),
                        new VXRegister(),
                        new VXRegister()
                ),
                List.of(
                        new IRegister(),
                        new PCRegister()
                )
        );
    }

    DefaultCpu(final List<Register<UnsignedByte>> vxRegisters, final List<Register<UnsignedShort>> memoryRegisters) {
        this.vxRegisters = vxRegisters;
        this.memoryRegisters = memoryRegisters;
    }

    @Override
    public void execute(final List<Instruction> instructions) {
        this.pc().value(ZERO);
        while (this.pc().value().asInteger() / 2 < instructions.size()) {
            var currentPc = this.pc().value();
            instructions.get(this.pc().value().asInteger() / 2).execute(this);
            if (currentPc.equals(this.pc().value())) {
                this.pc().add(TWO);
            }
        }
    }

    @Override
    public Register<UnsignedByte> v0() {
        return this.vxRegisters.getFirst();
    }

    @Override
    public Register<UnsignedByte> v1() {
        return this.vxRegisters.get(1);
    }

    @Override
    public Register<UnsignedByte> v2() {
        return this.vxRegisters.get(2);
    }

    @Override
    public Register<UnsignedByte> v3() {
        return this.vxRegisters.get(3);
    }

    @Override
    public Register<UnsignedByte> v4() {
        return this.vxRegisters.get(4);
    }

    @Override
    public Register<UnsignedByte> v5() {
        return this.vxRegisters.get(5);
    }

    @Override
    public Register<UnsignedByte> v6() {
        return this.vxRegisters.get(6);
    }

    @Override
    public Register<UnsignedByte> v7() {
        return this.vxRegisters.get(7);
    }

    @Override
    public Register<UnsignedByte> v8() {
        return this.vxRegisters.get(8);
    }

    @Override
    public Register<UnsignedByte> v9() {
        return this.vxRegisters.get(9);
    }

    @Override
    public Register<UnsignedByte> vA() {
        return this.vxRegisters.get(10);
    }

    @Override
    public Register<UnsignedByte> vB() {
        return this.vxRegisters.get(11);
    }

    @Override
    public Register<UnsignedByte> vC() {
        return this.vxRegisters.get(12);
    }

    @Override
    public Register<UnsignedByte> vD() {
        return this.vxRegisters.get(13);
    }

    @Override
    public Register<UnsignedByte> vE() {
        return this.vxRegisters.get(14);
    }

    @Override
    public Register<UnsignedByte> vF() {
        return this.vxRegisters.getLast();
    }

    @Override
    public Register<UnsignedShort> i() {
        return this.memoryRegisters.getFirst();
    }

    @Override
    public Register<UnsignedShort> pc() {
        return this.memoryRegisters.getLast();
    }
}
