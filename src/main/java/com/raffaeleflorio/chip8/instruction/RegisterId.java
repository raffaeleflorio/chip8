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

    Cpu.Register<UnsignedByte> toRegister(final Cpu cpu) {
        return switch (this) {
            case V0 -> cpu.v0();
            case V1 -> cpu.v1();
            case V2 -> cpu.v2();
            case V3 -> cpu.v3();
            case V4 -> cpu.v4();
            case V5 -> cpu.v5();
            case V6 -> cpu.v6();
            case V7 -> cpu.v7();
            case V8 -> cpu.v8();
            case V9 -> cpu.v9();
            case VA -> cpu.vA();
            case VB -> cpu.vB();
            case VC -> cpu.vC();
            case VD -> cpu.vD();
            case VE -> cpu.vE();
        };
    }
}
