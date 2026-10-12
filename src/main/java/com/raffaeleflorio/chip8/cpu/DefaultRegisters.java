package com.raffaeleflorio.chip8.cpu;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.UnsignedByte;
import com.raffaeleflorio.chip8.UnsignedShort;

import java.util.List;

final class DefaultRegisters implements Cpu.Registers {

    private final List<Cpu.Register<UnsignedByte>> vxRegisters;
    private final List<Cpu.Register<UnsignedShort>> memoryRegisters;

    DefaultRegisters() {
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

    DefaultRegisters(
            final List<Cpu.Register<UnsignedByte>> vxRegisters,
            final List<Cpu.Register<UnsignedShort>> memoryRegisters
    ) {
        this.vxRegisters = vxRegisters;
        this.memoryRegisters = memoryRegisters;
    }

    @Override
    public Cpu.Register<UnsignedByte> v0() {
        return this.vxRegisters.getFirst();
    }

    @Override
    public Cpu.Register<UnsignedByte> v1() {
        return this.vxRegisters.get(1);
    }

    @Override
    public Cpu.Register<UnsignedByte> v2() {
        return this.vxRegisters.get(2);
    }

    @Override
    public Cpu.Register<UnsignedByte> v3() {
        return this.vxRegisters.get(3);
    }

    @Override
    public Cpu.Register<UnsignedByte> v4() {
        return this.vxRegisters.get(4);
    }

    @Override
    public Cpu.Register<UnsignedByte> v5() {
        return this.vxRegisters.get(5);
    }

    @Override
    public Cpu.Register<UnsignedByte> v6() {
        return this.vxRegisters.get(6);
    }

    @Override
    public Cpu.Register<UnsignedByte> v7() {
        return this.vxRegisters.get(7);
    }

    @Override
    public Cpu.Register<UnsignedByte> v8() {
        return this.vxRegisters.get(8);
    }

    @Override
    public Cpu.Register<UnsignedByte> v9() {
        return this.vxRegisters.get(9);
    }

    @Override
    public Cpu.Register<UnsignedByte> vA() {
        return this.vxRegisters.get(10);
    }

    @Override
    public Cpu.Register<UnsignedByte> vB() {
        return this.vxRegisters.get(11);
    }

    @Override
    public Cpu.Register<UnsignedByte> vC() {
        return this.vxRegisters.get(12);
    }

    @Override
    public Cpu.Register<UnsignedByte> vD() {
        return this.vxRegisters.get(13);
    }

    @Override
    public Cpu.Register<UnsignedByte> vE() {
        return this.vxRegisters.get(14);
    }

    @Override
    public Cpu.Register<UnsignedByte> vF() {
        return this.vxRegisters.getLast();
    }

    @Override
    public Cpu.Register<UnsignedShort> i() {
        return this.memoryRegisters.getFirst();
    }

    @Override
    public Cpu.Register<UnsignedShort> pc() {
        return this.memoryRegisters.getLast();
    }
}
