package com.raffaeleflorio.chip8.display;

import com.raffaeleflorio.chip8.Display;
import javafx.scene.Scene;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public final class FxDisplay implements Display {

    private final static Integer COLUMNS = 64;
    private final static Integer ROWS = 32;
    private final static Integer PIXEL_SIZE = 10;
    private final static Color PIXEL_OFF_COLOR = Color.BLACK;
    private final static Color PIXEL_ON_COLOR = Color.WHITE;

    private final Stage stage;

    public FxDisplay() {
        this(new Stage());
    }

    public FxDisplay(final Stage stage) {
        this.stage = stage;
    }

    @Override
    public void on() {
        if (!this.initialized()) {
            this.clear();
            this.stage.setResizable(false);
        }
        this.stage.show();
    }

    private Boolean initialized() {
        return this.stage.getScene() != null;
    }

    @Override
    public void clear() {
        this.stage.setScene(this.blankScene());
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

    private Rectangle turnedOffPixel() {
        return new Rectangle(PIXEL_SIZE, PIXEL_SIZE, PIXEL_OFF_COLOR);
    }

    @Override
    public void off() {
        this.stage.hide();
    }

    @Override
    public void xor(final Integer x, final Integer y, final Runnable collision) {
        var pixel = this.pixel(x, y);
        if (this.on(pixel)) {
            this.turnOff(pixel);
            collision.run();
        } else {
            this.turnOn(pixel);
        }
    }

    private Rectangle pixel(final Integer x, final Integer y) {
        if (x > COLUMNS || y > ROWS) {
            throw new IllegalArgumentException();
        }
        if (!this.initialized()) {
            throw new IllegalStateException();
        }
        var root = (TilePane) this.stage.getScene().getRoot();
        return (Rectangle) root.getChildren().get(x * y);
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
