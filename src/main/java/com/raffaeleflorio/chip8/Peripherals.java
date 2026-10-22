package com.raffaeleflorio.chip8;

public interface Peripherals {

    Display display();

    Timer delayTimer();

    Timer soundTimer();

    HexKeyboard hexKeyboard();
}
