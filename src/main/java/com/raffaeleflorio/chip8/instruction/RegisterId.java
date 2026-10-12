package com.raffaeleflorio.chip8.instruction;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.UnsignedByte;

public enum RegisterId {
    V0,
    V1,
    V2,
    V3,
    V4,
    V5,
    V6,
    V7,
    V8,
    V9,
    VA,
    VB,
    VC,
    VD,
    VE;

    Cpu.Register<UnsignedByte> toRegister(final Cpu.Registers registers) {
        return switch (this) {
            case V0 -> registers.v0();
            case V1 -> registers.v1();
            case V2 -> registers.v2();
            case V3 -> registers.v3();
            case V4 -> registers.v4();
            case V5 -> registers.v5();
            case V6 -> registers.v6();
            case V7 -> registers.v7();
            case V8 -> registers.v8();
            case V9 -> registers.v9();
            case VA -> registers.vA();
            case VB -> registers.vB();
            case VC -> registers.vC();
            case VD -> registers.vD();
            case VE -> registers.vE();
        };
    }
}
