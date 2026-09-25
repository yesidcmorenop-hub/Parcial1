package org.example.parcial1.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.parcial1.model.Gimnasio;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    @FXML private Label lblNombreGimnasio;
    @FXML private Label lblNitGimnasio;
    @FXML private Label lblContactoGimnasio;

    private Gimnasio gimnasio;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gimnasio = Gimnasio.getInstance();

        // Cargar los datos institucionales desde el Singleton
        if (gimnasio != null) {
            lblNombreGimnasio.setText("Gimnasio: " + gimnasio.getNombre());
            lblNitGimnasio.setText("NIT: " + gimnasio.getNit());
            lblContactoGimnasio.setText("Contacto: Tel: " + gimnasio.getTelefono() +
                    " | " + gimnasio.getCorreo() + " | " + gimnasio.getPaginaWeb());
        }
    }

    @FXML
    void irAClientes(ActionEvent event) {
        // CORREGIDO: Ruta exacta cliente.fxml tal como está en resources
        cambiarEscena(event, "/org/example/parcial1/cliente.fxml", "Gestión de Clientes");
    }

    @FXML
    void irAPlanes(ActionEvent event) {
        cambiarEscena(event, "/org/example/parcial1/planEntrenamiento.fxml", "Gestión de Planes de Entrenamiento");
    }

    @FXML
    void irAEntrenadores(ActionEvent event) {
        cambiarEscena(event, "/org/example/parcial1/EntrenadorView.fxml", "Gestión de Entrenadores");
    }

    @FXML
    void irAInscripciones(ActionEvent event) {
        cambiarEscena(event, "/org/example/parcial1/inscripcion-view.fxml", "Gestión de Inscripciones");
    }

    @FXML
    void irAReportes(ActionEvent event) {
        cambiarEscena(event, "/org/example/parcial1/reportes.fxml", "Gestión de Reportes e Ingresos");
    }

    /**
     * Método auxiliar para realizar la transición entre pantallas
     */
    private void cambiarEscena(ActionEvent event, String rutaFXML, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFXML));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.err.println("Error al cargar la vista: " + rutaFXML);
            e.printStackTrace();
        }
    }
}
