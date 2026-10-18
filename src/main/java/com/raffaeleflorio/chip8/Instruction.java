package com.raffaeleflorio.chip8;

public interface Instruction {

    void execute(Cpu.Registers registers, Stack stack, Memory memory, Peripherals peripherals);

    default void ifJump(Runnable runnable) {
        this.ifJumpOrElse(runnable, () -> {
        });
    }

    default void ifNotJump(Runnable runnable) {
        this.ifJumpOrElse(
                () -> {
                },
                runnable
        );
    }

    void ifJumpOrElse(Runnable whenJump, Runnable whenNotJump);
}
