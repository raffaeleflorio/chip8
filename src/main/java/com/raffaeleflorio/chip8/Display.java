package com.raffaeleflorio.chip8;

public interface Display {

    void on();

    void off();

    void clear();

    void xor(Integer x, Integer y, Runnable whenCollision, Runnable whenNoCollision);
}
