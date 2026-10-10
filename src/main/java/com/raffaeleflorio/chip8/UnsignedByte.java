package com.raffaeleflorio.chip8;

public interface UnsignedByte {

    UnsignedByte sum(UnsignedByte other);

    UnsignedByte difference(UnsignedByte other);

    UnsignedByte or(UnsignedByte other);

    UnsignedByte and(UnsignedByte other);

    UnsignedByte xor(UnsignedByte other);

    UnsignedByte shiftedRight();

    UnsignedByte shiftedLeft();

    Bit mostSignificantBit();

    Bit leastSignificatBit();

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

    UnsignedShort asUnsignedShort();

    final class From implements UnsignedByte {

        private final Integer value;

        public From() {
            this(0);
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
        public Bit leastSignificatBit() {
            return (this.asInteger() & 0x1) == 0x1 ? new Bit.One() : new Bit.Zero();
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
        public UnsignedShort asUnsignedShort() {
            return new UnsignedShort.From(this.asInteger());
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
