package com.raffaeleflorio.chip8.peripherals;

import com.raffaeleflorio.chip8.HexKeyboard;
import com.raffaeleflorio.chip8.UnsignedNibble;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.Map;
import java.util.Optional;

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
                var eventHandler = new KeyEventHandler();
                this.stage.getScene().setOnKeyPressed(eventHandler);
                this.stage.getScene().setOnKeyReleased(eventHandler);
            }
        });
    }

    private Boolean initialized() {
        var scene = this.stage.getScene();
        return scene.getOnKeyPressed() != null && scene.getOnKeyReleased() != null;
    }

    @Override
    public void off() {
        Platform.runLater(() -> {
            this.stage.getScene().setOnKeyPressed(null);
            this.stage.getScene().setOnKeyReleased(null);
        });
    }

    private final class KeyEventHandler implements EventHandler<KeyEvent> {

        private final static Map<KeyCode, UnsignedNibble> KEYCODE_NIBBLES = Map.ofEntries(
                Map.entry(KeyCode.DIGIT0, new UnsignedNibble.Zero()),
                Map.entry(KeyCode.DIGIT1, new UnsignedNibble.One()),
                Map.entry(KeyCode.DIGIT2, new UnsignedNibble.Two()),
                Map.entry(KeyCode.DIGIT3, new UnsignedNibble.Three()),
                Map.entry(KeyCode.DIGIT4, new UnsignedNibble.Four()),
                Map.entry(KeyCode.DIGIT5, new UnsignedNibble.Five()),
                Map.entry(KeyCode.DIGIT6, new UnsignedNibble.Six()),
                Map.entry(KeyCode.DIGIT7, new UnsignedNibble.Seven()),
                Map.entry(KeyCode.DIGIT8, new UnsignedNibble.Eight()),
                Map.entry(KeyCode.DIGIT9, new UnsignedNibble.Nine()),
                Map.entry(KeyCode.A, new UnsignedNibble.Ten()),
                Map.entry(KeyCode.B, new UnsignedNibble.Eleven()),
                Map.entry(KeyCode.C, new UnsignedNibble.Twelve()),
                Map.entry(KeyCode.D, new UnsignedNibble.Thirteen()),
                Map.entry(KeyCode.E, new UnsignedNibble.Fourteen()),
                Map.entry(KeyCode.F, new UnsignedNibble.Fifteen())
        );

        @Override
        public void handle(final KeyEvent ev) {
            if (ev.getEventType().equals(KeyEvent.KEY_PRESSED) && KEYCODE_NIBBLES.containsKey(ev.getCode())) {
                synchronized (FxHexKeyboard.this) {
                    FxHexKeyboard.this.key = new FxKey(ev.getCode(), KEYCODE_NIBBLES);
                    FxHexKeyboard.this.notify();
                }
            } else if (ev.getEventType().equals(KeyEvent.KEY_RELEASED) && KEYCODE_NIBBLES.containsKey(ev.getCode())) {
                synchronized (FxHexKeyboard.this) {
                    FxHexKeyboard.this.key = null;
                }
            }
        }
    }

    private final static class FxKey implements Key {

        private final KeyCode code;
        private final Map<KeyCode, UnsignedNibble> map;

        private FxKey(final KeyCode code, final Map<KeyCode, UnsignedNibble> map) {
            this.code = code;
            this.map = map;
        }

        @Override
        public UnsignedNibble asUnsignedNibble() {
            return this.map.get(this.code);
        }
    }
}
