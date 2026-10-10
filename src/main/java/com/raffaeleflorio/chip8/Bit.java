package com.raffaeleflorio.chip8;

public interface Bit {

    void ifOneOrElse(Runnable whenOne, Runnable whenZero);

    UnsignedByte asUnsignedByte();

    final class Zero implements Bit {

        @Override
        public void ifOneOrElse(final Runnable whenOne, final Runnable whenZero) {
            whenZero.run();
        }

        @Override
        public UnsignedByte asUnsignedByte() {
            return new UnsignedByte.From(0);
        }

        @Override
        public int hashCode() {
            return 0;
        }

        @Override
        public boolean equals(final Object obj) {
            return obj instanceof Zero;
        }
    }

    final class One implements Bit {

        @Override
        public void ifOneOrElse(final Runnable whenOne, final Runnable whenZero) {
            whenOne.run();
        }

        @Override
        public UnsignedByte asUnsignedByte() {
            return new UnsignedByte.From(1);
        }

        @Override
        public int hashCode() {
            return 1;
        }

        @Override
        public boolean equals(final Object obj) {
            return obj instanceof One;
        }
    }
}
