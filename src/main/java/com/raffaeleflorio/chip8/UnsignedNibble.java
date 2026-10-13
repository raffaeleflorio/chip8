package com.raffaeleflorio.chip8;

import java.util.function.Supplier;

public interface UnsignedNibble {

    <TO> TO to(
            Supplier<TO> zero,
            Supplier<TO> one,
            Supplier<TO> two,
            Supplier<TO> three,
            Supplier<TO> four,
            Supplier<TO> five,
            Supplier<TO> six,
            Supplier<TO> seven,
            Supplier<TO> eight,
            Supplier<TO> nine,
            Supplier<TO> ten,
            Supplier<TO> eleven,
            Supplier<TO> twelve,
            Supplier<TO> thirteen,
            Supplier<TO> fourteen,
            Supplier<TO> fifteen
    );

    Integer asInteger();

    final class Zero implements UnsignedNibble {
        @Override
        public <TO> TO to(
                final Supplier<TO> zero,
                final Supplier<TO> one,
                final Supplier<TO> two,
                final Supplier<TO> three,
                final Supplier<TO> four,
                final Supplier<TO> five,
                final Supplier<TO> six,
                final Supplier<TO> seven,
                final Supplier<TO> eight,
                final Supplier<TO> nine,
                final Supplier<TO> ten,
                final Supplier<TO> eleven,
                final Supplier<TO> twelve,
                final Supplier<TO> thirteen,
                final Supplier<TO> fourteen,
                final Supplier<TO> fifteen
        ) {
            return zero.get();
        }

        @Override
        public Integer asInteger() {
            return 0;
        }

        @Override
        public int hashCode() {
            return this.asInteger();
        }

        @Override
        public boolean equals(final Object obj) {
            return obj instanceof Zero;
        }
    }

    final class One implements UnsignedNibble {
        @Override
        public <TO> TO to(
                final Supplier<TO> zero,
                final Supplier<TO> one,
                final Supplier<TO> two,
                final Supplier<TO> three,
                final Supplier<TO> four,
                final Supplier<TO> five,
                final Supplier<TO> six,
                final Supplier<TO> seven,
                final Supplier<TO> eight,
                final Supplier<TO> nine,
                final Supplier<TO> ten,
                final Supplier<TO> eleven,
                final Supplier<TO> twelve,
                final Supplier<TO> thirteen,
                final Supplier<TO> fourteen,
                final Supplier<TO> fifteen
        ) {
            return one.get();
        }

        @Override
        public Integer asInteger() {
            return 1;
        }

        @Override
        public int hashCode() {
            return this.asInteger();
        }

        @Override
        public boolean equals(final Object obj) {
            return obj instanceof One;
        }
    }

    final class Two implements UnsignedNibble {
        @Override
        public <TO> TO to(
                final Supplier<TO> zero,
                final Supplier<TO> one,
                final Supplier<TO> two,
                final Supplier<TO> three,
                final Supplier<TO> four,
                final Supplier<TO> five,
                final Supplier<TO> six,
                final Supplier<TO> seven,
                final Supplier<TO> eight,
                final Supplier<TO> nine,
                final Supplier<TO> ten,
                final Supplier<TO> eleven,
                final Supplier<TO> twelve,
                final Supplier<TO> thirteen,
                final Supplier<TO> fourteen,
                final Supplier<TO> fifteen
        ) {
            return two.get();
        }

        @Override
        public Integer asInteger() {
            return 2;
        }

        @Override
        public int hashCode() {
            return this.asInteger();
        }

        @Override
        public boolean equals(final Object obj) {
            return obj instanceof Two;
        }
    }

    final class Three implements UnsignedNibble {
        @Override
        public <TO> TO to(
                final Supplier<TO> zero,
                final Supplier<TO> one,
                final Supplier<TO> two,
                final Supplier<TO> three,
                final Supplier<TO> four,
                final Supplier<TO> five,
                final Supplier<TO> six,
                final Supplier<TO> seven,
                final Supplier<TO> eight,
                final Supplier<TO> nine,
                final Supplier<TO> ten,
                final Supplier<TO> eleven,
                final Supplier<TO> twelve,
                final Supplier<TO> thirteen,
                final Supplier<TO> fourteen,
                final Supplier<TO> fifteen
        ) {
            return three.get();
        }

        @Override
        public Integer asInteger() {
            return 3;
        }

        @Override
        public int hashCode() {
            return this.asInteger();
        }

        @Override
        public boolean equals(final Object obj) {
            return obj instanceof Three;
        }
    }

    final class Four implements UnsignedNibble {
        @Override
        public <TO> TO to(
                final Supplier<TO> zero,
                final Supplier<TO> one,
                final Supplier<TO> two,
                final Supplier<TO> three,
                final Supplier<TO> four,
                final Supplier<TO> five,
                final Supplier<TO> six,
                final Supplier<TO> seven,
                final Supplier<TO> eight,
                final Supplier<TO> nine,
                final Supplier<TO> ten,
                final Supplier<TO> eleven,
                final Supplier<TO> twelve,
                final Supplier<TO> thirteen,
                final Supplier<TO> fourteen,
                final Supplier<TO> fifteen
        ) {
            return four.get();
        }

        @Override
        public Integer asInteger() {
            return 4;
        }

        @Override
        public int hashCode() {
            return this.asInteger();
        }

        @Override
        public boolean equals(final Object obj) {
            return obj instanceof Four;
        }
    }

    final class Five implements UnsignedNibble {
        @Override
        public <TO> TO to(
                final Supplier<TO> zero,
                final Supplier<TO> one,
                final Supplier<TO> two,
                final Supplier<TO> three,
                final Supplier<TO> four,
                final Supplier<TO> five,
                final Supplier<TO> six,
                final Supplier<TO> seven,
                final Supplier<TO> eight,
                final Supplier<TO> nine,
                final Supplier<TO> ten,
                final Supplier<TO> eleven,
                final Supplier<TO> twelve,
                final Supplier<TO> thirteen,
                final Supplier<TO> fourteen,
                final Supplier<TO> fifteen
        ) {
            return five.get();
        }

        @Override
        public Integer asInteger() {
            return 5;
        }

        @Override
        public int hashCode() {
            return this.asInteger();
        }

        @Override
        public boolean equals(final Object obj) {
            return obj instanceof Five;
        }
    }

    final class Six implements UnsignedNibble {
        @Override
        public <TO> TO to(
                final Supplier<TO> zero,
                final Supplier<TO> one,
                final Supplier<TO> two,
                final Supplier<TO> three,
                final Supplier<TO> four,
                final Supplier<TO> five,
                final Supplier<TO> six,
                final Supplier<TO> seven,
                final Supplier<TO> eight,
                final Supplier<TO> nine,
                final Supplier<TO> ten,
                final Supplier<TO> eleven,
                final Supplier<TO> twelve,
                final Supplier<TO> thirteen,
                final Supplier<TO> fourteen,
                final Supplier<TO> fifteen
        ) {
            return six.get();
        }

        @Override
        public Integer asInteger() {
            return 6;
        }

        @Override
        public int hashCode() {
            return this.asInteger();
        }

        @Override
        public boolean equals(final Object obj) {
            return obj instanceof Six;
        }
    }

    final class Seven implements UnsignedNibble {
        @Override
        public <TO> TO to(
                final Supplier<TO> zero,
                final Supplier<TO> one,
                final Supplier<TO> two,
                final Supplier<TO> three,
                final Supplier<TO> four,
                final Supplier<TO> five,
                final Supplier<TO> six,
                final Supplier<TO> seven,
                final Supplier<TO> eight,
                final Supplier<TO> nine,
                final Supplier<TO> ten,
                final Supplier<TO> eleven,
                final Supplier<TO> twelve,
                final Supplier<TO> thirteen,
                final Supplier<TO> fourteen,
                final Supplier<TO> fifteen
        ) {
            return seven.get();
        }

        @Override
        public Integer asInteger() {
            return 7;
        }

        @Override
        public int hashCode() {
            return this.asInteger();
        }

        @Override
        public boolean equals(final Object obj) {
            return obj instanceof Seven;
        }
    }

    final class Eight implements UnsignedNibble {
        @Override
        public <TO> TO to(
                final Supplier<TO> zero,
                final Supplier<TO> one,
                final Supplier<TO> two,
                final Supplier<TO> three,
                final Supplier<TO> four,
                final Supplier<TO> five,
                final Supplier<TO> six,
                final Supplier<TO> seven,
                final Supplier<TO> eight,
                final Supplier<TO> nine,
                final Supplier<TO> ten,
                final Supplier<TO> eleven,
                final Supplier<TO> twelve,
                final Supplier<TO> thirteen,
                final Supplier<TO> fourteen,
                final Supplier<TO> fifteen
        ) {
            return eight.get();
        }

        @Override
        public Integer asInteger() {
            return 8;
        }

        @Override
        public int hashCode() {
            return this.asInteger();
        }

        @Override
        public boolean equals(final Object obj) {
            return obj instanceof Eight;
        }
    }

    final class Nine implements UnsignedNibble {
        @Override
        public <TO> TO to(
                final Supplier<TO> zero,
                final Supplier<TO> one,
                final Supplier<TO> two,
                final Supplier<TO> three,
                final Supplier<TO> four,
                final Supplier<TO> five,
                final Supplier<TO> six,
                final Supplier<TO> seven,
                final Supplier<TO> eight,
                final Supplier<TO> nine,
                final Supplier<TO> ten,
                final Supplier<TO> eleven,
                final Supplier<TO> twelve,
                final Supplier<TO> thirteen,
                final Supplier<TO> fourteen,
                final Supplier<TO> fifteen
        ) {
            return nine.get();
        }

        @Override
        public Integer asInteger() {
            return 9;
        }

        @Override
        public int hashCode() {
            return this.asInteger();
        }

        @Override
        public boolean equals(final Object obj) {
            return obj instanceof Nine;
        }
    }

    final class Ten implements UnsignedNibble {
        @Override
        public <TO> TO to(
                final Supplier<TO> zero,
                final Supplier<TO> one,
                final Supplier<TO> two,
                final Supplier<TO> three,
                final Supplier<TO> four,
                final Supplier<TO> five,
                final Supplier<TO> six,
                final Supplier<TO> seven,
                final Supplier<TO> eight,
                final Supplier<TO> nine,
                final Supplier<TO> ten,
                final Supplier<TO> eleven,
                final Supplier<TO> twelve,
                final Supplier<TO> thirteen,
                final Supplier<TO> fourteen,
                final Supplier<TO> fifteen
        ) {
            return ten.get();
        }

        @Override
        public Integer asInteger() {
            return 10;
        }

        @Override
        public int hashCode() {
            return this.asInteger();
        }

        @Override
        public boolean equals(final Object obj) {
            return obj instanceof Ten;
        }
    }

    final class Eleven implements UnsignedNibble {
        @Override
        public <TO> TO to(
                final Supplier<TO> zero,
                final Supplier<TO> one,
                final Supplier<TO> two,
                final Supplier<TO> three,
                final Supplier<TO> four,
                final Supplier<TO> five,
                final Supplier<TO> six,
                final Supplier<TO> seven,
                final Supplier<TO> eight,
                final Supplier<TO> nine,
                final Supplier<TO> ten,
                final Supplier<TO> eleven,
                final Supplier<TO> twelve,
                final Supplier<TO> thirteen,
                final Supplier<TO> fourteen,
                final Supplier<TO> fifteen
        ) {
            return eleven.get();
        }

        @Override
        public Integer asInteger() {
            return 11;
        }

        @Override
        public int hashCode() {
            return this.asInteger();
        }

        @Override
        public boolean equals(final Object obj) {
            return obj instanceof Eleven;
        }
    }

    final class Twelve implements UnsignedNibble {
        @Override
        public <TO> TO to(
                final Supplier<TO> zero,
                final Supplier<TO> one,
                final Supplier<TO> two,
                final Supplier<TO> three,
                final Supplier<TO> four,
                final Supplier<TO> five,
                final Supplier<TO> six,
                final Supplier<TO> seven,
                final Supplier<TO> eight,
                final Supplier<TO> nine,
                final Supplier<TO> ten,
                final Supplier<TO> eleven,
                final Supplier<TO> twelve,
                final Supplier<TO> thirteen,
                final Supplier<TO> fourteen,
                final Supplier<TO> fifteen
        ) {
            return twelve.get();
        }

        @Override
        public Integer asInteger() {
            return 12;
        }

        @Override
        public int hashCode() {
            return this.asInteger();
        }

        @Override
        public boolean equals(final Object obj) {
            return obj instanceof Twelve;
        }
    }

    final class Thirteen implements UnsignedNibble {
        @Override
        public <TO> TO to(
                final Supplier<TO> zero,
                final Supplier<TO> one,
                final Supplier<TO> two,
                final Supplier<TO> three,
                final Supplier<TO> four,
                final Supplier<TO> five,
                final Supplier<TO> six,
                final Supplier<TO> seven,
                final Supplier<TO> eight,
                final Supplier<TO> nine,
                final Supplier<TO> ten,
                final Supplier<TO> eleven,
                final Supplier<TO> twelve,
                final Supplier<TO> thirteen,
                final Supplier<TO> fourteen,
                final Supplier<TO> fifteen
        ) {
            return thirteen.get();
        }

        @Override
        public Integer asInteger() {
            return 13;
        }

        @Override
        public int hashCode() {
            return this.asInteger();
        }

        @Override
        public boolean equals(final Object obj) {
            return obj instanceof Thirteen;
        }
    }

    final class Fourteen implements UnsignedNibble {
        @Override
        public <TO> TO to(
                final Supplier<TO> zero,
                final Supplier<TO> one,
                final Supplier<TO> two,
                final Supplier<TO> three,
                final Supplier<TO> four,
                final Supplier<TO> five,
                final Supplier<TO> six,
                final Supplier<TO> seven,
                final Supplier<TO> eight,
                final Supplier<TO> nine,
                final Supplier<TO> ten,
                final Supplier<TO> eleven,
                final Supplier<TO> twelve,
                final Supplier<TO> thirteen,
                final Supplier<TO> fourteen,
                final Supplier<TO> fifteen
        ) {
            return fourteen.get();
        }

        @Override
        public Integer asInteger() {
            return 14;
        }

        @Override
        public int hashCode() {
            return this.asInteger();
        }

        @Override
        public boolean equals(final Object obj) {
            return obj instanceof Fourteen;
        }
    }

    final class Fifteen implements UnsignedNibble {
        @Override
        public <TO> TO to(
                final Supplier<TO> zero,
                final Supplier<TO> one,
                final Supplier<TO> two,
                final Supplier<TO> three,
                final Supplier<TO> four,
                final Supplier<TO> five,
                final Supplier<TO> six,
                final Supplier<TO> seven,
                final Supplier<TO> eight,
                final Supplier<TO> nine,
                final Supplier<TO> ten,
                final Supplier<TO> eleven,
                final Supplier<TO> twelve,
                final Supplier<TO> thirteen,
                final Supplier<TO> fourteen,
                final Supplier<TO> fifteen
        ) {
            return fifteen.get();
        }

        @Override
        public Integer asInteger() {
            return 15;
        }

        @Override
        public int hashCode() {
            return this.asInteger();
        }

        @Override
        public boolean equals(final Object obj) {
            return obj instanceof Fifteen;
        }
    }
}
