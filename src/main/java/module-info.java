module org.example.parcial1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.parcial1 to javafx.fxml;
    exports org.example.parcial1;
    exports org.example.parcial1.model;
    opens org.example.parcial1.model to javafx.fxml;
}