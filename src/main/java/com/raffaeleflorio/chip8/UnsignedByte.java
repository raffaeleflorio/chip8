package com.raffaeleflorio.chip8;

import java.util.List;

public interface UnsignedByte {

    UnsignedByte sum(UnsignedByte other);

    UnsignedByte difference(UnsignedByte other);

    UnsignedByte or(UnsignedByte other);

    UnsignedByte and(UnsignedByte other);

    UnsignedByte xor(UnsignedByte other);

    UnsignedByte shiftedRight();

    UnsignedByte shiftedLeft();

    Bit mostSignificantBit();

    Bit leastSignificantBit();

    UnsignedNibble mostSignificantNibble();

    UnsignedNibble leastSignificantNibble();

    default void ifOverflowedThrows(final RuntimeException exception) {
        this.ifOverflowed(() -> {
                    throw exception;
                }
        );
    }

    default void ifOverflowed(Runnable runnable) {
        this.ifOverflowedOrElse(
                runnable,
                () -> {
                }
        );
    }

    void ifOverflowedOrElse(Runnable whenOverflowed, Runnable whenNotOverflowed);

    default void ifUnderflowedThrows(final RuntimeException exception) {
        this.ifUnderflowed(() -> {
                    throw exception;
                }
        );
    }

    default void ifUnderflowed(Runnable runnable) {
        this.ifUnderflowedOrElse(
                runnable,
                () -> {
                }
        );
    }

    void ifUnderflowedOrElse(Runnable whenUnderflowed, Runnable whenNotUnderflowed);

    Integer asInteger();

    default UnsignedShort asUnsignedShort() {
        return new UnsignedShort.From(this.asInteger());
    }

    default List<UnsignedByte> asBCD() {
        return List.of(
                new UnsignedByte.From(this.asInteger() / 100),
                new UnsignedByte.From((this.asInteger() % 100) / 10),
                new UnsignedByte.From(this.asInteger() % 10)
        );
    }

    final class From implements UnsignedByte {

        private final Integer value;

        public From() {
            this((byte) 0);
        }

        public From(final Byte value) {
            this(value.intValue());
        }

        public From(final Integer value) {
            this.value = value;
        }

        @Override
        public UnsignedByte sum(final UnsignedByte other) {
            return new From(this.asInteger() + other.asInteger());
        }

        @Override
        public Integer asInteger() {
            return this.value & 0xFF;
        }

        @Override
        public UnsignedByte difference(final UnsignedByte other) {
            return new From(this.asInteger() - other.asInteger());
        }

        @Override
        public UnsignedByte or(final UnsignedByte other) {
            return new From(this.asInteger() | other.asInteger());
        }

        @Override
        public UnsignedByte and(final UnsignedByte other) {
            return new From(this.asInteger() & other.asInteger());
        }

        @Override
        public UnsignedByte xor(final UnsignedByte other) {
            return new From(this.asInteger() ^ other.asInteger());
        }

        @Override
        public UnsignedByte shiftedRight() {
            return new From(this.asInteger() >> 1);
        }

        @Override
        public UnsignedByte shiftedLeft() {
            return new From(this.asInteger() << 1);
        }

        @Override
        public Bit mostSignificantBit() {
            return (this.asInteger() & 0x80) == 0x80 ? new Bit.One() : new Bit.Zero();
        }

        @Override
        public Bit leastSignificantBit() {
            return (this.asInteger() & 0x1) == 0x1 ? new Bit.One() : new Bit.Zero();
        }

        @Override
        public UnsignedNibble mostSignificantNibble() {
            return this.nibble((this.asInteger() & 0xF0) >> 4);
        }

        private UnsignedNibble nibble(final Integer nibble) {
            return switch (nibble) {
                case 0 -> new UnsignedNibble.Zero();
                case 1 -> new UnsignedNibble.One();
                case 2 -> new UnsignedNibble.Two();
                case 3 -> new UnsignedNibble.Three();
                case 4 -> new UnsignedNibble.Four();
                case 5 -> new UnsignedNibble.Five();
                case 6 -> new UnsignedNibble.Six();
                case 7 -> new UnsignedNibble.Seven();
                case 8 -> new UnsignedNibble.Eight();
                case 9 -> new UnsignedNibble.Nine();
                case 10 -> new UnsignedNibble.Ten();
                case 11 -> new UnsignedNibble.Eleven();
                case 12 -> new UnsignedNibble.Twelve();
                case 13 -> new UnsignedNibble.Thirteen();
                case 14 -> new UnsignedNibble.Fourteen();
                default -> new UnsignedNibble.Fifteen();
            };
        }

        @Override
        public UnsignedNibble leastSignificantNibble() {
            return this.nibble(this.asInteger() & 0xF);
        }

        @Override
        public void ifOverflowedOrElse(final Runnable whenOverflowed, final Runnable whenNotOverflowed) {
            if (this.value > 0xFF) {
                whenOverflowed.run();
            } else {
                whenNotOverflowed.run();
            }
        }

        @Override
        public void ifUnderflowedOrElse(final Runnable whenUnderflowed, final Runnable whenNotUnderflowed) {
            if (this.value < 0x0) {
                whenUnderflowed.run();
            } else {
                whenNotUnderflowed.run();
            }
        }

        @Override
        public boolean equals(final Object obj) {
            return obj instanceof UnsignedByte && ((UnsignedByte) obj).asInteger().equals(this.asInteger());
        }

        @Override
        public int hashCode() {
            return this.asInteger();
        }
    }
}
