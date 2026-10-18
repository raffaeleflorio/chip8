package com.raffaeleflorio.chip8;

import java.nio.file.Path;

public interface Memory {

    void value(UnsignedShort address, UnsignedByte value);

    UnsignedByte value(UnsignedShort address);

    void load(UnsignedShort address, Path dump);
}
