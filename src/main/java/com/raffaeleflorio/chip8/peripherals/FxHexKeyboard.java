package com.raffaeleflorio.chip8.peripherals;

import com.raffaeleflorio.chip8.HexKeyboard;
import com.raffaeleflorio.chip8.UnsignedNibble;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.Optional;
import java.util.Set;

final class FxHexKeyboard implements HexKeyboard {

    private final Stage stage;

    private Key key;

    FxHexKeyboard(final Stage stage) {
        this(stage, null);
    }

    FxHexKeyboard(final Stage stage, final Key key) {
        this.stage = stage;
        this.key = key;
    }

    @Override
    public synchronized Key key() {
        while (this.key == null) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }
        return this.key;
    }

    @Override
    public synchronized Optional<Key> keyNow() {
        return Optional.ofNullable(this.key);
    }

    @Override
    public void on() {
        Platform.runLater(() -> {
            if (!this.initialized()) {
                this.stage.getScene().setOnKeyPressed(new KeyEventHandler());
            }
        });
    }

    private Boolean initialized() {
        return this.stage.getScene().getOnKeyPressed() != null;
    }

    @Override
    public void off() {
        Platform.runLater(() -> this.stage.getScene().setOnKeyPressed(null));
    }

    private final class KeyEventHandler implements EventHandler<KeyEvent> {

        private final static Set<KeyCode> ALLOWED_CODES = Set.of(
                KeyCode.DIGIT0,
                KeyCode.DIGIT1,
                KeyCode.DIGIT2,
                KeyCode.DIGIT3,
                KeyCode.DIGIT4,
                KeyCode.DIGIT5,
                KeyCode.DIGIT6,
                KeyCode.DIGIT7,
                KeyCode.DIGIT8,
                KeyCode.DIGIT9,
                KeyCode.A,
                KeyCode.B,
                KeyCode.C,
                KeyCode.D,
                KeyCode.E,
                KeyCode.F
        );

        @Override
        public void handle(final KeyEvent ev) {
            synchronized (FxHexKeyboard.this) {
                if (ev.getEventType().equals(KeyEvent.KEY_PRESSED) && ALLOWED_CODES.contains(ev.getCode())) {
                    FxHexKeyboard.this.key = new FxKey(ev.getCode());
                    FxHexKeyboard.this.notify();
                } else if (ev.getEventType().equals(KeyEvent.KEY_RELEASED) && ALLOWED_CODES.contains(ev.getCode())) {
                    FxHexKeyboard.this.key = null;
                }
            }
        }
    }

    private final static class FxKey implements Key {

        private final KeyCode code;

        private FxKey(final KeyCode code) {
            this.code = code;
        }

        @Override
        public UnsignedNibble asUnsignedNibble() {
            return switch (this.code) {
                case KeyCode.DIGIT0 -> new UnsignedNibble.Zero();
                case KeyCode.DIGIT1 -> new UnsignedNibble.One();
                case KeyCode.DIGIT2 -> new UnsignedNibble.Two();
                case KeyCode.DIGIT3 -> new UnsignedNibble.Three();
                case KeyCode.DIGIT4 -> new UnsignedNibble.Four();
                case KeyCode.DIGIT5 -> new UnsignedNibble.Five();
                case KeyCode.DIGIT6 -> new UnsignedNibble.Six();
                case KeyCode.DIGIT7 -> new UnsignedNibble.Seven();
                case KeyCode.DIGIT8 -> new UnsignedNibble.Eight();
                case KeyCode.DIGIT9 -> new UnsignedNibble.Nine();
                case KeyCode.A -> new UnsignedNibble.Ten();
                case KeyCode.B -> new UnsignedNibble.Eleven();
                case KeyCode.C -> new UnsignedNibble.Twelve();
                case KeyCode.D -> new UnsignedNibble.Thirteen();
                case KeyCode.E -> new UnsignedNibble.Fourteen();
                case KeyCode.F -> new UnsignedNibble.Fifteen();
                default -> throw new IllegalStateException();
            };
        }
    }
}
