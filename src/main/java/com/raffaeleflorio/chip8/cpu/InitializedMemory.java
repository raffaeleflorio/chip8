package com.raffaeleflorio.chip8.cpu;

import com.raffaeleflorio.chip8.Cpu;
import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.Memory;
import com.raffaeleflorio.chip8.UnsignedByte;
import com.raffaeleflorio.chip8.UnsignedShort;

import java.util.List;
import java.util.Map;

final class InitializedMemory implements Cpu {

    private final static Integer SPRITE_HEIGHT = 5;
    private final static Map<Integer, List<UnsignedByte>> SPRITES = Map.ofEntries(
            Map.entry(
                    0, List.of(
                            new UnsignedByte.From(0xF0),
                            new UnsignedByte.From(0x90),
                            new UnsignedByte.From(0x90),
                            new UnsignedByte.From(0x90),
                            new UnsignedByte.From(0xF0)
                    )
            ),
            Map.entry(
                    1, List.of(
                            new UnsignedByte.From(0x20),
                            new UnsignedByte.From(0x60),
                            new UnsignedByte.From(0x20),
                            new UnsignedByte.From(0x20),
                            new UnsignedByte.From(0x70)
                    )
            ),
            Map.entry(
                    2, List.of(
                            new UnsignedByte.From(0xF0),
                            new UnsignedByte.From(0x10),
                            new UnsignedByte.From(0xF0),
                            new UnsignedByte.From(0x80),
                            new UnsignedByte.From(0xF0)
                    )
            ),
            Map.entry(
                    3, List.of(
                            new UnsignedByte.From(0xF0),
                            new UnsignedByte.From(0x10),
                            new UnsignedByte.From(0xF0),
                            new UnsignedByte.From(0x10),
                            new UnsignedByte.From(0xF0)
                    )
            ),
            Map.entry(
                    4, List.of(
                            new UnsignedByte.From(0x90),
                            new UnsignedByte.From(0x90),
                            new UnsignedByte.From(0xF0),
                            new UnsignedByte.From(0x10),
                            new UnsignedByte.From(0x10)
                    )
            ),
            Map.entry(
                    5, List.of(
                            new UnsignedByte.From(0xF0),
                            new UnsignedByte.From(0x80),
                            new UnsignedByte.From(0xF0),
                            new UnsignedByte.From(0x10),
                            new UnsignedByte.From(0xF0)
                    )
            ),
            Map.entry(
                    6, List.of(
                            new UnsignedByte.From(0xF0),
                            new UnsignedByte.From(0x80),
                            new UnsignedByte.From(0xF0),
                            new UnsignedByte.From(0x90),
                            new UnsignedByte.From(0xF0)
                    )
            ),
            Map.entry(
                    7, List.of(
                            new UnsignedByte.From(0xF0),
                            new UnsignedByte.From(0x10),
                            new UnsignedByte.From(0x20),
                            new UnsignedByte.From(0x40),
                            new UnsignedByte.From(0x40)
                    )
            ),
            Map.entry(
                    8, List.of(
                            new UnsignedByte.From(0xF0),
                            new UnsignedByte.From(0x90),
                            new UnsignedByte.From(0xF0),
                            new UnsignedByte.From(0x90),
                            new UnsignedByte.From(0xF0)
                    )
            ),
            Map.entry(
                    9, List.of(
                            new UnsignedByte.From(0xF0),
                            new UnsignedByte.From(0x90),
                            new UnsignedByte.From(0xF0),
                            new UnsignedByte.From(0x10),
                            new UnsignedByte.From(0xF0)
                    )
            ),
            Map.entry(
                    0xA, List.of(
                            new UnsignedByte.From(0xF0),
                            new UnsignedByte.From(0x90),
                            new UnsignedByte.From(0xF0),
                            new UnsignedByte.From(0x90),
                            new UnsignedByte.From(0x90)
                    )
            ),
            Map.entry(
                    0xB, List.of(
                            new UnsignedByte.From(0xE0),
                            new UnsignedByte.From(0x90),
                            new UnsignedByte.From(0xE0),
                            new UnsignedByte.From(0x90),
                            new UnsignedByte.From(0xE0)
                    )
            ),
            Map.entry(
                    0xC, List.of(
                            new UnsignedByte.From(0xF0),
                            new UnsignedByte.From(0x80),
                            new UnsignedByte.From(0x80),
                            new UnsignedByte.From(0x80),
                            new UnsignedByte.From(0xF0)
                    )
            ),
            Map.entry(
                    0xD, List.of(
                            new UnsignedByte.From(0xE0),
                            new UnsignedByte.From(0x90),
                            new UnsignedByte.From(0x90),
                            new UnsignedByte.From(0x90),
                            new UnsignedByte.From(0xE0)
                    )
            ),
            Map.entry(
                    0xE, List.of(
                            new UnsignedByte.From(0xF0),
                            new UnsignedByte.From(0x80),
                            new UnsignedByte.From(0xF0),
                            new UnsignedByte.From(0x80),
                            new UnsignedByte.From(0xF0)
                    )
            ),
            Map.entry(
                    0xF, List.of(
                            new UnsignedByte.From(0xF0),
                            new UnsignedByte.From(0x80),
                            new UnsignedByte.From(0xF0),
                            new UnsignedByte.From(0x80),
                            new UnsignedByte.From(0x80)
                    )
            )
    );

    private final Cpu origin;
    private final Memory memory;

    InitializedMemory(final Cpu origin, final Memory memory) {
        this.origin = origin;
        this.memory = memory;
    }

    @Override
    public void execute(final List<Instruction> instructions) {
        this.initialized(
                () -> this.origin.execute(instructions)
        );
    }

    private void initialized(final Runnable runnable) {
        this.loadSprites();
        runnable.run();
    }

    private void loadSprites() {
        for (var spriteNr = 0; spriteNr < SPRITES.size(); spriteNr++) {
            var sprite = SPRITES.get(spriteNr);
            for (var rowNr = 0; rowNr < SPRITE_HEIGHT; rowNr++) {
                this.memory.value(this.address(spriteNr, rowNr), sprite.get(rowNr));
            }
        }
    }

    private UnsignedShort address(final Integer spriteNr, final Integer row) {
        return new UnsignedShort.From((SPRITE_HEIGHT * spriteNr) + row);
    }

    @Override
    public void execute(final UnsignedShort initialPC) {
        this.initialized(
                () -> this.origin.execute(initialPC)
        );
    }
}
