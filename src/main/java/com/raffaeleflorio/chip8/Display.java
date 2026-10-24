package com.raffaeleflorio.chip8;

import java.util.Set;

public interface Display extends Peripheral {

    void clear();

    default void xor(Integer x, Integer y, Runnable whenCollision, Runnable whenNoCollision) {
        this.xor(new Coordinate(x, y), whenCollision, whenNoCollision);
    }

    default void xor(Coordinate coordinate, Runnable whenCollision, Runnable whenNoCollision) {
        this.xor(Set.of(coordinate), whenCollision, whenNoCollision);
    }

    void xor(Set<Coordinate> coordinates, Runnable whenCollision, Runnable whenNoCollision);

    record Coordinate(Integer x, Integer y) {

    }
}
