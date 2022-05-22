module com.game.coup {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires com.google.gson;
    requires json.simple;

    opens com.game.coup to javafx.fxml;
    exports com.game.coup;
    exports com.game.coup.gui;
    exports com.game.coup.model;
    exports com.game.coup.game;

    opens com.game.coup.gui to javafx.fxml;
}