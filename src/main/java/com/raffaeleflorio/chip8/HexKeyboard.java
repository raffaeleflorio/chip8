package com.raffaeleflorio.chip8;

import java.util.Optional;

public interface HexKeyboard extends Peripheral {

    Key key();

    Optional<Key> keyNow();

    interface Key {

        UnsignedNibble asUnsignedNibble();
    }
}
