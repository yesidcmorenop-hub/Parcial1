module org.example.parcial1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens org.example.parcial1 to javafx.fxml;
    opens org.example.parcial1.app to javafx.fxml;
    opens org.example.parcial1.controller to javafx.fxml;
    opens org.example.parcial1.model to javafx.fxml;
    opens org.example.parcial1.factory to javafx.fxml;

    exports org.example.parcial1.app;
    exports org.example.parcial1.controller;
    exports org.example.parcial1.model;
    exports org.example.parcial1.factory;
}