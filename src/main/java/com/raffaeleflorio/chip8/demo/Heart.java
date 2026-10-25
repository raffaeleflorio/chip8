package com.raffaeleflorio.chip8.demo;

import com.raffaeleflorio.chip8.UnsignedByte;
import com.raffaeleflorio.chip8.UnsignedNibble;
import com.raffaeleflorio.chip8.UnsignedShort;
import com.raffaeleflorio.chip8.cpu.DefaultCpu;
import com.raffaeleflorio.chip8.instruction.AddToVx;
import com.raffaeleflorio.chip8.instruction.AddVyToVx;
import com.raffaeleflorio.chip8.instruction.Chip8OpCodeTable;
import com.raffaeleflorio.chip8.instruction.Clear;
import com.raffaeleflorio.chip8.instruction.Draw;
import com.raffaeleflorio.chip8.instruction.GetDelay;
import com.raffaeleflorio.chip8.instruction.Goto;
import com.raffaeleflorio.chip8.instruction.IfVxEq;
import com.raffaeleflorio.chip8.instruction.IfVxNeq;
import com.raffaeleflorio.chip8.instruction.RegStore;
import com.raffaeleflorio.chip8.instruction.SetDelay;
import com.raffaeleflorio.chip8.instruction.SetI;
import com.raffaeleflorio.chip8.instruction.SetVx;
import com.raffaeleflorio.chip8.instruction.SetVxToMaskedRand;
import com.raffaeleflorio.chip8.instruction.SubVyFromVx;
import com.raffaeleflorio.chip8.memory.Chip8Memory;
import com.raffaeleflorio.chip8.peripherals.Chip8Peripherals;
import com.raffaeleflorio.chip8.stack.Chip8Stack;
import javafx.application.Application;
import javafx.stage.Stage;

public final class Heart extends Application {

    @Override
    public void start(final Stage stage) {
        var cpu = new DefaultCpu(
                new Chip8Stack(),
                new Chip8Memory(),
                new Chip8Peripherals(),
                new Chip8OpCodeTable()
        );

        new Thread(() -> {
            cpu.execute(
                    new SetI(new UnsignedShort.From(0xAAA)),
                    new SetVx(new UnsignedNibble.Zero(), new UnsignedByte.From(0b00111100)),
                    new SetVx(new UnsignedNibble.One(), new UnsignedByte.From(0b01000010)),
                    new SetVx(new UnsignedNibble.Two(), new UnsignedByte.From(0b10000001)),
                    new SetVx(new UnsignedNibble.Three(), new UnsignedByte.From(0b10000000)),
                    new SetVx(new UnsignedNibble.Four(), new UnsignedByte.From(0b10000000)),
                    new SetVx(new UnsignedNibble.Five(), new UnsignedByte.From(0b01000000)),
                    new SetVx(new UnsignedNibble.Six(), new UnsignedByte.From(0b00100000)),
                    new SetVx(new UnsignedNibble.Seven(), new UnsignedByte.From(0b00010000)),
                    new SetVx(new UnsignedNibble.Eight(), new UnsignedByte.From(0b00001000)),
                    new SetVx(new UnsignedNibble.Nine(), new UnsignedByte.From(0b00000100)),
                    new SetVx(new UnsignedNibble.Ten(), new UnsignedByte.From(0b00000010)),
                    new SetVx(new UnsignedNibble.Eleven(), new UnsignedByte.From(0b00000001)),
                    new RegStore(new UnsignedNibble.Eleven()),

                    new SetI(new UnsignedShort.From(0xAAA + 0xC)),
                    new SetVx(new UnsignedNibble.Zero(), new UnsignedByte.From(0b00111100)),
                    new SetVx(new UnsignedNibble.One(), new UnsignedByte.From(0b01000010)),
                    new SetVx(new UnsignedNibble.Two(), new UnsignedByte.From(0b10000001)),
                    new SetVx(new UnsignedNibble.Three(), new UnsignedByte.From(0b00000001)),
                    new SetVx(new UnsignedNibble.Four(), new UnsignedByte.From(0b000000001)),
                    new SetVx(new UnsignedNibble.Five(), new UnsignedByte.From(0b00000010)),
                    new SetVx(new UnsignedNibble.Six(), new UnsignedByte.From(0b00000100)),
                    new SetVx(new UnsignedNibble.Seven(), new UnsignedByte.From(0b00001000)),
                    new SetVx(new UnsignedNibble.Eight(), new UnsignedByte.From(0b00010000)),
                    new SetVx(new UnsignedNibble.Nine(), new UnsignedByte.From(0b00100000)),
                    new SetVx(new UnsignedNibble.Ten(), new UnsignedByte.From(0b01000000)),
                    new SetVx(new UnsignedNibble.Eleven(), new UnsignedByte.From(0b10000000)),
                    new RegStore(new UnsignedNibble.Eleven()),

                    new SetVxToMaskedRand(new UnsignedNibble.Four(), new UnsignedByte.From(0xF)),
                    new SetVxToMaskedRand(new UnsignedNibble.Five(), new UnsignedByte.From(0xF)),

                    new SetVx(new UnsignedNibble.Six(), new UnsignedByte.From(0x0)),
                    new SetVx(new UnsignedNibble.Seven(), new UnsignedByte.From(0x0)),

                    new Clear(),

                    new SetVx(new UnsignedNibble.Zero(), new UnsignedByte.From(0x0)),
                    new SetVx(new UnsignedNibble.One(), new UnsignedByte.From(0x0)),
                    new SetVx(new UnsignedNibble.Two(), new UnsignedByte.From(0x8)),
                    new SetVx(new UnsignedNibble.Three(), new UnsignedByte.From(0x0)),

                    new AddVyToVx(new UnsignedNibble.Zero(), new UnsignedNibble.Four()),
                    new AddVyToVx(new UnsignedNibble.One(), new UnsignedNibble.Five()),
                    new AddVyToVx(new UnsignedNibble.Two(), new UnsignedNibble.Four()),
                    new AddVyToVx(new UnsignedNibble.Three(), new UnsignedNibble.Five()),

                    new SetI(new UnsignedShort.From(0xAAA)),
                    new Draw(new UnsignedNibble.Zero(), new UnsignedNibble.One(), new UnsignedNibble.Twelve()),

                    new SetI(new UnsignedShort.From(0xAAA + 0xC)),
                    new Draw(new UnsignedNibble.Two(), new UnsignedNibble.Three(), new UnsignedNibble.Twelve()),

                    new SetVx(new UnsignedNibble.Eight(), new UnsignedByte.From(0x3)),
                    new SetDelay(new UnsignedNibble.Eight()),
                    new GetDelay(new UnsignedNibble.Eight()),
                    new IfVxEq(new UnsignedNibble.Eight(), new UnsignedByte.From(0x0)),
                    new Goto(new UnsignedShort.From(0x5E)),

                    new IfVxNeq(new UnsignedNibble.Four(), new UnsignedByte.From(0x30)),
                    new SetVx(new UnsignedNibble.Six(), new UnsignedByte.From(0x1)),
                    new IfVxNeq(new UnsignedNibble.Four(), new UnsignedByte.From(0x0)),
                    new SetVx(new UnsignedNibble.Six(), new UnsignedByte.From(0x0)),

                    new IfVxNeq(new UnsignedNibble.Six(), new UnsignedByte.From(0x0)),
                    new AddToVx(new UnsignedNibble.Four(), new UnsignedByte.From(0x1)),
                    new IfVxNeq(new UnsignedNibble.Six(), new UnsignedByte.From(0x1)),
                    new SubVyFromVx(new UnsignedNibble.Four(), new UnsignedNibble.Six()),

                    new IfVxNeq(new UnsignedNibble.Five(), new UnsignedByte.From(0x14)),
                    new SetVx(new UnsignedNibble.Seven(), new UnsignedByte.From(0x1)),
                    new IfVxNeq(new UnsignedNibble.Five(), new UnsignedByte.From(0x0)),
                    new SetVx(new UnsignedNibble.Seven(), new UnsignedByte.From(0x0)),

                    new IfVxNeq(new UnsignedNibble.Seven(), new UnsignedByte.From(0x0)),
                    new AddToVx(new UnsignedNibble.Five(), new UnsignedByte.From(0x1)),
                    new IfVxNeq(new UnsignedNibble.Seven(), new UnsignedByte.From(0x1)),
                    new SubVyFromVx(new UnsignedNibble.Five(), new UnsignedNibble.Seven()),

                    new Goto(new UnsignedShort.From(0x40))
            );
        }).start();
    }
}