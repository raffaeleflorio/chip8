package com.raffaeleflorio.chip8;

import java.util.Optional;

public interface HexKeyboard {

    Key key();

    Optional<Key> keyNow();

    void on();

    void off();

    interface Key {

        UnsignedNibble asUnsignedNibble();
    }
}
