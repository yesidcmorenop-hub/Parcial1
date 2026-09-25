package org.example.parcial1.controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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

import org.example.parcial1.model.Cliente;
import org.example.parcial1.model.Gimnasio;
import org.example.parcial1.model.Inscripcion;
import org.example.parcial1.model.PlanEntrenamiento;
import org.example.parcial1.model.ServicioAdicional;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ReportesViewController implements Initializable {

    @FXML private DatePicker dpFechaInicio;
    @FXML private DatePicker dpFechaFin;

    @FXML private Label lblIngresosPlanes;
    @FXML private Label lblIngresosServicios;
    @FXML private Label lblTotalAcumulado;

    @FXML private TableView<Inscripcion> tblReportes;
    @FXML private TableColumn<Inscripcion, String> colFecha;
    @FXML private TableColumn<Inscripcion, String> colCliente;
    @FXML private TableColumn<Inscripcion, String> colPlan;
    @FXML private TableColumn<Inscripcion, Integer> colCantServicios;
    @FXML private TableColumn<Inscripcion, Double> colTotalInscripcion;

    private Gimnasio gimnasio;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gimnasio = Gimnasio.getInstance();

        // Configurar fechas por defecto: desde el primer día del mes actual hasta hoy
        dpFechaInicio.setValue(LocalDate.now().withDayOfMonth(1));
        dpFechaFin.setValue(LocalDate.now());

        configurarTabla();
        onConsultarIngresos(null); // Ejecutar consulta inicial
    }

    private void configurarTabla() {
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaInscripcion"));

        colCliente.setCellValueFactory(cellData -> {
            Cliente c = cellData.getValue().getCliente();
            return new SimpleStringProperty(c != null ? c.getNombre() : "");
        });

        colPlan.setCellValueFactory(cellData -> {
            PlanEntrenamiento p = cellData.getValue().getPlanEntrenamiento();
            return new SimpleStringProperty(p != null ? p.getNombre() : "");
        });

        colCantServicios.setCellValueFactory(cellData -> {
            ArrayList<ServicioAdicional> servs = cellData.getValue().getListServiciosAdicionales();
            return new SimpleIntegerProperty(servs != null ? servs.size() : 0).asObject();
        });

        colTotalInscripcion.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().calcularValorTotal()).asObject()
        );
    }

    @FXML
    void onConsultarIngresos(ActionEvent event) {
        LocalDate fechaInicio = dpFechaInicio.getValue();
        LocalDate fechaFin = dpFechaFin.getValue();

        if (fechaInicio == null || fechaFin == null) {
            mostrarAlerta("Atención", "Por favor seleccione ambas fechas para realizar la consulta.", Alert.AlertType.WARNING);
            return;
        }

        if (fechaFin.isBefore(fechaInicio)) {
            mostrarAlerta("Rango Inválido", "La fecha fin no puede ser anterior a la fecha de inicio.", Alert.AlertType.ERROR);
            return;
        }

        List<Inscripcion> todasInscripciones = gimnasio.getListInscripcion();
        List<Inscripcion> filtradas = new ArrayList<>();

        double totalPlanes = 0.0;
        double totalServicios = 0.0;

        if (todasInscripciones != null) {
            for (Inscripcion ins : todasInscripciones) {
                LocalDate f = ins.getFechaInscripcion();
                // Verificar si la fecha está dentro del rango inclusivo
                if (f != null && (f.isEqual(fechaInicio) || f.isEqual(fechaFin) || (f.isAfter(fechaInicio) && f.isBefore(fechaFin)))) {
                    filtradas.add(ins);

                    // Sumar valor base del plan
                    if (ins.getPlanEntrenamiento() != null) {
                        totalPlanes += ins.getPlanEntrenamiento().calcularValorBase();
                    }

                    // Sumar servicios adicionales de esta inscripción
                    if (ins.getListServiciosAdicionales() != null) {
                        for (ServicioAdicional s : ins.getListServiciosAdicionales()) {
                            totalServicios += s.getPrecio();
                        }
                    }
                }
            }
        }

        double totalGeneral = totalPlanes + totalServicios;

        // Actualizar UI
        lblIngresosPlanes.setText(String.format("$ %.2f", totalPlanes));
        lblIngresosServicios.setText(String.format("$ %.2f", totalServicios));
        lblTotalAcumulado.setText(String.format("$ %.2f", totalGeneral));

        tblReportes.setItems(FXCollections.observableArrayList(filtradas));
        tblReportes.refresh();
    }

    @FXML
    void onVolverMenu(ActionEvent event) {
        try {
            URL fxmlUrl = getClass().getResource("/org/example/parcial1/main-view.fxml");
            if (fxmlUrl == null) {
                fxmlUrl = getClass().getResource("/org/example/parcial1/main.fxml");
            }

            FXMLLoader loader = new FXMLLoader(fxmlUrl);
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Menú Principal - Gimnasio");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
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