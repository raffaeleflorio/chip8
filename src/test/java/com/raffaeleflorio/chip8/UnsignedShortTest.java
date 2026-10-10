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
    void sums_two_numbers_arithmetically() {
        var first = new UnsignedShort.From(5);
        var second = new UnsignedShort.From(7);

        assertThat(first.sum(second).asInteger()).isEqualTo(12);
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