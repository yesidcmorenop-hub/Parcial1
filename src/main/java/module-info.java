module org.example.parcial1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.parcial1 to javafx.fxml;
    exports org.example.parcial1;
    exports org.example.parcial1;
    opens org.example.parcial1 to javafx.fxml;
    exports org.example.parcial1.app;
    opens org.example.parcial1.app to javafx.fxml;
    exports org.example.parcial1.controller;
    opens org.example.parcial1.controller to javafx.fxml;
    exports org.example.parcial1.model;
    opens org.example.parcial1.model to javafx.fxml;
}