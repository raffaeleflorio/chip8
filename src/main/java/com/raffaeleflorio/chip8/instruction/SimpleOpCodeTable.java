package com.raffaeleflorio.chip8.instruction;

import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.OpCodeTable;
import com.raffaeleflorio.chip8.UnsignedByte;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

final class SimpleOpCodeTable implements OpCodeTable {

    private final Map<Predicate<UnsignedByte[]>, Function<UnsignedByte[], Instruction>> table;

    SimpleOpCodeTable(final Map<Predicate<UnsignedByte[]>, Function<UnsignedByte[], Instruction>> table) {
        this.table = table;
    }

    @Override
    public Instruction instruction(final UnsignedByte[] ubytes) {
        return this.table.entrySet().stream()
                .filter(entry -> entry.getKey().test(ubytes))
                .findFirst()
                .map(entry -> entry.getValue().apply(ubytes))
                .orElseThrow(IllegalArgumentException::new);
    }
}
