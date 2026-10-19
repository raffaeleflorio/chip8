package com.raffaeleflorio.chip8;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatException;
import static org.junit.jupiter.api.Assertions.assertAll;

class UnsignedByteTest {

    @Test
    void default_value_is_zero() {
        var ubyte = new UnsignedByte.From();

        assertThat(ubyte.asInteger()).isEqualTo(0);
    }

    @Test
    void maximum_value_is_255() {
        var ubyte = new UnsignedByte.From(255);

        assertThat(ubyte.asInteger()).isEqualTo(255);
    }

    @Test
    void sums_two_numbers_arithmetically() {
        var first = new UnsignedByte.From(1);
        var second = new UnsignedByte.From(1);

        assertThat(first.sum(second).asInteger()).isEqualTo(2);
    }

    @Test
    void overflows_when_sum_is_greater_than_the_maximum_value() {
        var first = new UnsignedByte.From(254);
        var second = new UnsignedByte.From(3);
        var sum = first.sum(second);

        assertAll(
                () -> assertThat(sum.asInteger()).isEqualTo(1),
                () -> assertThatException().isThrownBy(() -> sum.ifOverflowedThrows(new IllegalStateException()))
        );
    }

    @Test
    void subtracts_two_value_arithmentically() {
        var first = new UnsignedByte.From(1);
        var second = new UnsignedByte.From(1);

        assertThat(first.difference(second).asInteger()).isEqualTo(0);
    }

    @Test
    void underflows_when_difference_is_negative() {
        var first = new UnsignedByte.From(1);
        var second = new UnsignedByte.From(2);
        var difference = first.difference(second);

        assertAll(
                () -> assertThat(difference.asInteger()).isEqualTo(255),
                () -> assertThatException().isThrownBy(() -> difference.ifUnderflowedThrows(new IllegalStateException()))
        );
    }

    @Test
    void applies_bitwise_and_logic() {
        var first = new UnsignedByte.From(0b0101);
        var second = new UnsignedByte.From(0b1111);

        assertThat(first.and(second).asInteger()).isEqualTo(0b0101);
    }

    @Test
    void applies_bitwise_or_logic() {
        var first = new UnsignedByte.From(0b0101);
        var second = new UnsignedByte.From(0b1010);

        assertThat(first.or(second).asInteger()).isEqualTo(0b1111);
    }

    @Test
    void applies_bitwise_xor_logic() {
        var first = new UnsignedByte.From(0b0101);
        var second = new UnsignedByte.From(0b1110);

        assertThat(first.xor(second).asInteger()).isEqualTo(0b1011);
    }

    @Test
    void shifts_right_by_one_bit() {
        var ubyte = new UnsignedByte.From(0b0101);

        assertThat(ubyte.shiftedRight().asInteger()).isEqualTo(0b0010);
    }

    @Test
    void shifts_left_by_one_bit() {
        var ubyte = new UnsignedByte.From(0b0101);

        assertThat(ubyte.shiftedLeft().asInteger()).isEqualTo(0b01010);
    }

    @Test
    void most_significant_bit_is_the_leftmost() {
        var ubyte = new UnsignedByte.From(0b10000000);

        assertThat(ubyte.mostSignificantBit()).isEqualTo(new Bit.One());
    }

    @Test
    void least_significant_bit_is_the_rightmost() {
        var ubyte = new UnsignedByte.From(0b00000001);

        assertThat(ubyte.leastSignificantBit()).isEqualTo(new Bit.One());
    }

    @Test
    void most_significant_nibble_is_the_leftmost() {
        var ubyte = new UnsignedByte.From(0xAB);

        assertThat(ubyte.mostSignificantNibble()).isEqualTo(new UnsignedNibble.Ten());
    }

    @Test
    void least_significant_nibble_is_the_rightmost() {
        var ubyte = new UnsignedByte.From(0xAB);

        assertThat(ubyte.leastSignificantNibble()).isEqualTo(new UnsignedNibble.Eleven());
    }

    @Test
    void its_unsigned_short_representation_has_the_same_integer_representation() {
        var ubyte = new UnsignedByte.From(42);

        assertThat(ubyte.asUnsignedShort().asInteger()).isEqualTo(42);
    }

    @Test
    void runs_the_overflow_code_if_it_overflowed() {
        var ubyte = new UnsignedByte.From(256);

        assertThatException().isThrownBy(
                () -> ubyte.ifOverflowedOrElse(
                        () -> {
                            throw new IllegalStateException();
                        },
                        () -> {
                        })
        );
    }

    @Test
    void runs_the_not_overflowed_code_if_it_did_not_overflow() {
        var ubyte = new UnsignedByte.From(128);

        assertThatException().isThrownBy(
                () -> ubyte.ifOverflowedOrElse(
                        () -> {
                        },
                        () -> {
                            throw new IllegalStateException();
                        }
                )
        );
    }

    @Test
    void runs_the_underflow_code_if_it_underflowed() {
        var ubyte = new UnsignedByte.From(-1);

        assertThatException().isThrownBy(
                () -> ubyte.ifUnderflowedOrElse(
                        () -> {
                            throw new IllegalStateException();
                        },
                        () -> {
                        }
                )
        );
    }

    @Test
    void runs_the_not_underflow_code_if_it_did_not_underflow() {
        var ubyte = new UnsignedByte.From(0);

        assertThatException().isThrownBy(
                () -> ubyte.ifUnderflowedOrElse(
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
        var ubyte = new UnsignedByte.From(257);

        assertThat(ubyte.hashCode()).isEqualTo(1);
    }

    @Test
    void two_unsigned_byte_values_are_equal_if_they_have_the_same_integer_representation() {
        var first = new UnsignedByte.From(10);
        var second = new UnsignedByte.From(266);

        assertThat(first).isEqualTo(second);
    }

    @Test
    void its_BCD_representation_is_hundreds_tens_and_ones() {
        var ubyte = new UnsignedByte.From(123);

        assertThat(ubyte.asBCD())
                .containsExactly(
                        new UnsignedByte.From(0x1),
                        new UnsignedByte.From(0x2),
                        new UnsignedByte.From(0x3)
                );
    }
}