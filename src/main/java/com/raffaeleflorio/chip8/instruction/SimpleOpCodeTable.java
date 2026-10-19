package com.raffaeleflorio.chip8.instruction;

import com.raffaeleflorio.chip8.Instruction;
import com.raffaeleflorio.chip8.OpCodeTable;
import com.raffaeleflorio.chip8.UnsignedShort;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

final class SimpleOpCodeTable implements OpCodeTable {

    private final Map<Predicate<UnsignedShort>, Function<UnsignedShort, Instruction>> table;

    SimpleOpCodeTable(final Map<Predicate<UnsignedShort>, Function<UnsignedShort, Instruction>> table) {
        this.table = table;
    }

    @Override
    public Instruction instruction(final UnsignedShort opcode) {
        return this.table.entrySet().stream()
                .filter(entry -> entry.getKey().test(opcode))
                .findFirst()
                .map(entry -> entry.getValue().apply(opcode))
                .orElseThrow(IllegalArgumentException::new);
    }
}
