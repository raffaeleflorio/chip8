package com.raffaeleflorio.chip8;

public interface UnsignedShort {

    UnsignedShort sum(UnsignedShort other);

    default void ifOverflowedThrows(final RuntimeException exception) {
        this.ifOverflowed(() -> {
                    throw exception;
                }
        );
    }

    void ifOverflowed(Runnable runnable);

    Integer asInteger();

    final class From implements UnsignedShort {

        private final Integer value;

        public From() {
            this(0);
        }

        public From(final Integer value) {
            this.value = value;
        }

        @Override
        public UnsignedShort sum(final UnsignedShort other) {
            return new UnsignedShort.From(this.asInteger() + other.asInteger());
        }

        @Override
        public void ifOverflowed(final Runnable runnable) {
            if (this.value > 0xFFFF) {
                runnable.run();
            }
        }

        @Override
        public Integer asInteger() {
            return this.value & 0xFFFF;
        }

        @Override
        public int hashCode() {
            return this.asInteger();
        }

        @Override
        public boolean equals(final Object obj) {
            return obj instanceof UnsignedShort && ((UnsignedShort) obj).asInteger().equals(this.asInteger());
        }
    }
}
