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
import java.util.ResourceBundle;

public class InscripcionViewController implements Initializable {

    @FXML private ComboBox<Cliente> cbCliente;
    @FXML private ComboBox<PlanEntrenamiento> cbPlan;
    @FXML private DatePicker dpFechaInscripcion;

    @FXML private ListView<ServicioAdicional> lvServiciosDisponibles;
    @FXML private ListView<ServicioAdicional> lvServiciosAgregados;

    @FXML private Label lblValorPlan;
    @FXML private Label lblTotalPagar;

    @FXML private TableView<Inscripcion> tblInscripciones;
    @FXML private TableColumn<Inscripcion, String> colCliente;
    @FXML private TableColumn<Inscripcion, String> colPlan;
    @FXML private TableColumn<Inscripcion, String> colFecha;
    @FXML private TableColumn<Inscripcion, Integer> colServicios;
    @FXML private TableColumn<Inscripcion, Double> colTotal;

    private Gimnasio gimnasio;
    private ObservableList<ServicioAdicional> serviciosSeleccionados = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gimnasio = Gimnasio.getInstance();
        dpFechaInscripcion.setValue(LocalDate.now());

        configurarFormatoListViews();
        cargarCombosYListas();
        configurarTabla();
    }

    private void configurarFormatoListViews() {
        // Formateador visual personalizado para que el ListView muestre Nombre y Precio claramente
        ListCell<ServicioAdicional> cellFactory = new ListCell<>() {
            @Override
            protected void updateItem(ServicioAdicional item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getNombre() + " (" + item.getTipoServicio() + ") - $" + String.format("%.2f", item.getPrecio()));
                }
            }
        };

        lvServiciosDisponibles.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(ServicioAdicional item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getNombre() + " - $" + String.format("%.2f", item.getPrecio()));
                }
            }
        });

        lvServiciosAgregados.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(ServicioAdicional item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getNombre() + " - $" + String.format("%.2f", item.getPrecio()));
                }
            }
        });
    }

    private void cargarCombosYListas() {
        if (gimnasio.getListCliente() != null) {
            cbCliente.setItems(FXCollections.observableArrayList(gimnasio.getListCliente()));
        }
        if (gimnasio.getListPlanEntrenamiento() != null) {
            cbPlan.setItems(FXCollections.observableArrayList(gimnasio.getListPlanEntrenamiento()));
        }
        if (gimnasio.getListServiciosAdicionales() != null) {
            lvServiciosDisponibles.setItems(FXCollections.observableArrayList(gimnasio.getListServiciosAdicionales()));
        }

        lvServiciosAgregados.setItems(serviciosSeleccionados);
    }

    private void configurarTabla() {
        colCliente.setCellValueFactory(cellData -> {
            Cliente c = cellData.getValue().getCliente();
            return new SimpleStringProperty(c != null ? c.getNombre() : "");
        });

        colPlan.setCellValueFactory(cellData -> {
            PlanEntrenamiento p = cellData.getValue().getPlanEntrenamiento();
            return new SimpleStringProperty(p != null ? p.getNombre() : "");
        });

        colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaInscripcion"));

        colServicios.setCellValueFactory(cellData -> {
            ArrayList<ServicioAdicional> servs = cellData.getValue().getListServiciosAdicionales();
            return new SimpleIntegerProperty(servs != null ? servs.size() : 0).asObject();
        });

        colTotal.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().calcularValorTotal()).asObject()
        );

        actualizarTablaInscripciones();
    }

    private void actualizarTablaInscripciones() {
        if (gimnasio.getListInscripcion() != null) {
            tblInscripciones.setItems(FXCollections.observableArrayList(gimnasio.getListInscripcion()));
            tblInscripciones.refresh();
        }
    }

    @FXML
    void onPlanSeleccionado(ActionEvent event) {
        recalcularTotal();
    }

    @FXML
    void onAgregarServicio(ActionEvent event) {
        ServicioAdicional seleccionado = lvServiciosDisponibles.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            if (!serviciosSeleccionados.contains(seleccionado)) {
                serviciosSeleccionados.add(seleccionado);
                recalcularTotal();
            } else {
                mostrarAlerta("Información", "El servicio ya ha sido agregado a esta inscripción.", Alert.AlertType.INFORMATION);
            }
        } else {
            mostrarAlerta("Atención", "Seleccione un servicio adicional disponible.", Alert.AlertType.WARNING);
        }
    }

    private void recalcularTotal() {
        PlanEntrenamiento plan = cbPlan.getValue();
        double valorPlan = (plan != null) ? plan.calcularValorBase() : 0.0;
        lblValorPlan.setText(String.format("$ %.2f", valorPlan));

        double totalServicios = 0.0;
        for (ServicioAdicional s : serviciosSeleccionados) {
            totalServicios += s.getPrecio();
        }

        double totalPagar = valorPlan + totalServicios;
        lblTotalPagar.setText(String.format("$ %.2f", totalPagar));
    }

    @FXML
    void onRegistrarInscripcion(ActionEvent event) {
        Cliente cliente = cbCliente.getValue();
        PlanEntrenamiento plan = cbPlan.getValue();
        LocalDate fecha = dpFechaInscripcion.getValue();

        if (cliente == null || plan == null || fecha == null) {
            mostrarAlerta("Campos Incompletos", "Por favor seleccione Cliente, Plan y Fecha.", Alert.AlertType.WARNING);
            return;
        }

        Inscripcion nuevaInscripcion = new Inscripcion(fecha, cliente, plan);

        for (ServicioAdicional s : serviciosSeleccionados) {
            nuevaInscripcion.agregarServicioAdicional(s);
        }

        gimnasio.registrarInscripcion(nuevaInscripcion);

        double totalFinal = nuevaInscripcion.calcularValorTotal();

        mostrarAlerta("Éxito", "Inscripción registrada correctamente con un valor de $ " + totalFinal, Alert.AlertType.INFORMATION);

        serviciosSeleccionados.clear();
        cbCliente.setValue(null);
        cbPlan.setValue(null);
        recalcularTotal();
        actualizarTablaInscripciones();
    }

    @FXML
    void onVolverMenu(ActionEvent event) {
        try {
            URL fxmlUrl = getClass().getResource("/org/example/parcial1/main.fxml");
            if (fxmlUrl == null) {
                fxmlUrl = getClass().getResource("/org/example/parcial1/main-view.fxml");
            }

            if (fxmlUrl == null) {
                System.err.println("Error: No se encontró la vista principal.");
                return;
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
