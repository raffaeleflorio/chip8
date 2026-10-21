package com.raffaeleflorio.chip8.peripherals;

import com.raffaeleflorio.chip8.Display;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Set;
import java.util.concurrent.CountDownLatch;

final class FxDisplay implements Display {

    private final static Integer COLUMNS = 64;
    private final static Integer ROWS = 32;
    private final static Integer PIXEL_SIZE = 10;
    private final static Color PIXEL_OFF_COLOR = Color.BLACK;
    private final static Color PIXEL_ON_COLOR = Color.WHITE;

    private final Stage stage;

    FxDisplay() {
        this(new Stage());
    }

    FxDisplay(final Stage stage) {
        this.stage = stage;
    }

    @Override
    public void on() {
        var latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            if (!this.initialized()) {
                this.stage.setScene(this.blankScene());
                this.stage.setResizable(false);
            }
            this.stage.show();
            latch.countDown();
        });
        this.await(latch);
    }

    private void await(final CountDownLatch latch) {
        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    private Boolean initialized() {
        return this.stage.getScene() != null;
    }

    private Scene blankScene() {
        var root = new TilePane();
        root.setPrefColumns(COLUMNS);
        root.setPrefRows(ROWS);
        for (var i = 0; i < COLUMNS * ROWS; i++) {
            root.getChildren().add(this.turnedOffPixel());
        }
        return new Scene(root);
    }

    @Override
    public void clear() {
        var latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            for (var x = 0; x < COLUMNS; x++) {
                for (var y = 0; y < ROWS; y++) {
                    var pixel = this.pixel(x, y);
                    if (this.on(pixel)) {
                        this.turnOff(pixel);
                    }
                }
            }
            latch.countDown();
        });
        this.await(latch);
    }

    private Rectangle turnedOffPixel() {
        return new Rectangle(PIXEL_SIZE, PIXEL_SIZE, PIXEL_OFF_COLOR);
    }

    @Override
    public void off() {
        Platform.runLater(this.stage::hide);
    }

    @Override
    public void xor(final Set<Coordinate> coordinates, final Runnable whenCollision, final Runnable whenNoCollision) {
        var latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            var collision = false;
            for (var coordinate : coordinates) {
                var pixel = this.pixel(coordinate.x(), coordinate.y());
                if (this.on(pixel)) {
                    this.turnOff(pixel);
                    collision = true;
                } else {
                    this.turnOn(pixel);
                }
            }
            if (collision) {
                whenCollision.run();
            } else {
                whenNoCollision.run();
            }
            latch.countDown();
        });
        this.await(latch);
    }

    private Rectangle pixel(final Integer x, final Integer y) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException();
        }
        if (!this.initialized()) {
            throw new IllegalStateException();
        }
        var root = (TilePane) this.stage.getScene().getRoot();
        return (Rectangle) root.getChildren().get(((y % ROWS) * COLUMNS) + (x % COLUMNS));
    }

    private Boolean on(final Rectangle pixel) {
        return pixel.getFill().equals(PIXEL_ON_COLOR);
    }

    private void turnOff(final Rectangle pixel) {
        pixel.setFill(PIXEL_OFF_COLOR);
    }

    private void turnOn(final Rectangle pixel) {
        pixel.setFill(PIXEL_ON_COLOR);
    }
}
