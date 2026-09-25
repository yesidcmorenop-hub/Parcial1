package org.example.parcial1.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.parcial1.model.Entrenador;
import org.example.parcial1.model.Gimnasio;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EntrenadorViewController implements Initializable {

    @FXML private TextField txtIdentificacion;
    @FXML private TextField txtNombre;
    @FXML private TextField txtEspecialidad;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtTarifa;

    @FXML private TableView<Entrenador> tblEntrenadores;
    @FXML private TableColumn<Entrenador, Integer> colIdentificacion;
    @FXML private TableColumn<Entrenador, String> colNombre;
    @FXML private TableColumn<Entrenador, String> colEspecialidad;
    @FXML private TableColumn<Entrenador, Long> colTelefono;
    @FXML private TableColumn<Entrenador, Double> colTarifa;

    private Gimnasio gimnasio;
    private ObservableList<Entrenador> listaEntrenadoresObservable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gimnasio = Gimnasio.getInstance();

        // Mapeo exacto con los getters de Persona y Entrenador:
        // getDocumento(), getNombre(), getEspecialidad(), getTelefono(), getTarifa()
        colIdentificacion.setCellValueFactory(new PropertyValueFactory<>("documento"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colEspecialidad.setCellValueFactory(new PropertyValueFactory<>("especialidad"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colTarifa.setCellValueFactory(new PropertyValueFactory<>("tarifa"));

        actualizarTabla();
    }

    @FXML
    void onGuardarEntrenador(ActionEvent event) {
        String docStr = txtIdentificacion.getText().trim();
        String nombre = txtNombre.getText().trim();
        String especialidad = txtEspecialidad.getText().trim();
        String telStr = txtTelefono.getText().trim();
        String tarifaStr = txtTarifa.getText().trim();

        if (docStr.isEmpty() || nombre.isEmpty() || especialidad.isEmpty() || telStr.isEmpty() || tarifaStr.isEmpty()) {
            mostrarAlerta("Campos Incompletos", "Por favor complete todos los campos.", Alert.AlertType.WARNING);
            return;
        }

        try {
            int documento = Integer.parseInt(docStr);
            long telefono = Long.parseLong(telStr);
            double tarifa = Double.parseDouble(tarifaStr);

            // Instancia respetando el orden de tu constructor: (nombre, documento, telefono, especialidad, tarifa)
            Entrenador nuevoEntrenador = new Entrenador(nombre, documento, (int) telefono, especialidad, tarifa);

            gimnasio.registrarEntrenador(nuevoEntrenador);
            mostrarAlerta("Éxito", "El entrenador se ha registrado correctamente.", Alert.AlertType.INFORMATION);
            limpiarFormulario();
            actualizarTabla();

        } catch (NumberFormatException e) {
            mostrarAlerta("Error de Formato", "Asegúrate de ingresar valores numéricos válidos para Documento (entero), Teléfono (numérico) y Tarifa.", Alert.AlertType.ERROR);
        }
    }

    public void actualizarTabla() {
        if (gimnasio != null && gimnasio.getListEntrenador() != null) {
            listaEntrenadoresObservable = FXCollections.observableArrayList(gimnasio.getListEntrenador());
            tblEntrenadores.setItems(listaEntrenadoresObservable);
            tblEntrenadores.refresh();
        }
    }

    private void limpiarFormulario() {
        txtIdentificacion.clear();
        txtNombre.clear();
        txtEspecialidad.clear();
        txtTelefono.clear();
        txtTarifa.clear();
    }

    @FXML
    void onVolverMenu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/parcial1/main.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Menú Principal - Gimnasio");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.err.println("Error al regresar al menú principal: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(String titulo, String contenido, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}