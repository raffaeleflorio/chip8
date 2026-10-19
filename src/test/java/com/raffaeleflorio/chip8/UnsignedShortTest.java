package com.raffaeleflorio.chip8;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatException;
import static org.junit.jupiter.api.Assertions.assertAll;

class UnsignedShortTest {

    @Test
    void default_value_is_zero() {
        var ushort = new UnsignedShort.From();

        assertThat(ushort.asInteger()).isEqualTo(0);
    }

    @Test
    void maximum_value_is_65535() {
        var ushort = new UnsignedShort.From(65535);

        assertThat(ushort.asInteger()).isEqualTo(65535);
    }

    @Test
    void concatenates_two_bytes() {
        var ushort = new UnsignedShort.From(
                new UnsignedByte.From(0x12),
                new UnsignedByte.From(0x34)
        );

        assertThat(ushort.asInteger()).isEqualTo(0x1234);
    }

    @Test
    void sums_two_numbers_arithmetically() {
        var first = new UnsignedShort.From(5);
        var second = new UnsignedShort.From(7);

        assertThat(first.sum(second).asInteger()).isEqualTo(12);
    }

    @Test
    void applies_bitwise_and_logic() {
        var first = new UnsignedShort.From(0b10101010);
        var second = new UnsignedShort.From(0b11111111);

        assertThat(first.and(second).asInteger()).isEqualTo(0b10101010);
    }

    @Test
    void most_significant_byte_is_the_leftmost() {
        var ushort = new UnsignedShort.From(0xABCD);

        assertThat(ushort.mostSignificantByte()).isEqualTo(new UnsignedByte.From(0xAB));
    }

    @Test
    void least_significant_byte_is_the_rightmost() {
        var ushort = new UnsignedShort.From(0xABCD);

        assertThat(ushort.leastSignificantByte()).isEqualTo(new UnsignedByte.From(0xCD));
    }

    @Test
    void overflows_when_sum_is_greater_than_the_maximum_value() {
        var first = new UnsignedShort.From(65530);
        var second = new UnsignedShort.From(10);
        var sum = first.sum(second);

        assertAll(
                () -> assertThat(sum.asInteger()).isEqualTo(4),
                () -> assertThatException().isThrownBy(() -> sum.ifOverflowedThrows(new IllegalStateException()))
        );
    }

    @Test
    void runs_the_overflow_code_if_it_overflowed() {
        var ushort = new UnsignedShort.From(65536);

        assertThatException().isThrownBy(
                () -> ushort.ifOverflowedOrElse(
                        () -> {
                            throw new IllegalStateException();
                        },
                        () -> {
                        }
                )
        );
    }

    @Test
    void runs_the_not_overflow_code_if_it_did_not_overflow() {
        var ushort = new UnsignedShort.From(4200);

        assertThatException().isThrownBy(
                () -> ushort.ifOverflowedOrElse(
                        () -> {
                        },
                        () -> {
                            throw new IllegalStateException();
                        }
                )
        );
    }

    @Test
    void hash_code_is_the_integer_representation() {
        var ushort = new UnsignedShort.From(65546);

        assertThat(ushort.hashCode()).isEqualTo(10);
    }

    @Test
    void two_unsigned_short_values_are_equal_if_they_have_the_same_integer_representation() {
        var first = new UnsignedShort.From(65536);
        var second = new UnsignedShort.From(0);

        assertThat(first).isEqualTo(second);
    }
}