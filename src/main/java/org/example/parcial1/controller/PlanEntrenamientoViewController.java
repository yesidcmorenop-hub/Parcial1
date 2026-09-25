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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.parcial1.factory.*;
import org.example.parcial1.model.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PlanEntrenamientoViewController implements Initializable {

    // CAMPOS DEL FORMULARIO PRINCIPAL
    @FXML private TextField txtNombrePlan;
    @FXML private TextField txtPrecioPlan;
    @FXML private TextField txtCodigoPlan;
    @FXML private ComboBox<TipoPlan> cmbTipoPlan;

    // CAMPOS DEL PANEL PERSONALIZADO (VBox)
    @FXML private VBox panelPersonalizado;
    @FXML private TextField txtCantSesiones;
    @FXML private TextField txtEspecialidad;
    @FXML private TextField txtObjetivos;
    @FXML private ComboBox<Entrenador> cmbEntrenador;

    // TABLA Y COLUMNAS
    @FXML private TableView<PlanEntrenamiento> tblPlanes;
    @FXML private TableColumn<PlanEntrenamiento, String> colNombre;
    @FXML private TableColumn<PlanEntrenamiento, Double> colPrecio;
    @FXML private TableColumn<PlanEntrenamiento, String> colTipo;
    @FXML private TableColumn<PlanEntrenamiento, Integer> colCodigo;

    private Gimnasio gimnasio;
    private ObservableList<PlanEntrenamiento> listaPlanesObservable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gimnasio = Gimnasio.getInstance();

        // Cargar Enum TipoPlan en el ComboBox
        cmbTipoPlan.setItems(FXCollections.observableArrayList(TipoPlan.values()));

        // Cargar lista de entrenadores disponibles
        if (gimnasio != null && gimnasio.getListEntrenador() != null) {
            cmbEntrenador.setItems(FXCollections.observableArrayList(gimnasio.getListEntrenador()));
        }

        // Mapeo de columnas estándar
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("valorMensual"));
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));

        colTipo.setCellValueFactory(cellData -> {
            PlanEntrenamiento plan = cellData.getValue();
            if (plan instanceof PlanBasico) {
                return new javafx.beans.property.SimpleStringProperty(TipoPlan.BASICO.getNombreMostrar());
            } else if (plan instanceof PlanPremium) {
                return new javafx.beans.property.SimpleStringProperty(TipoPlan.PREMIUM.getNombreMostrar());
            } else if (plan instanceof PlanPersonalizado) {
                return new javafx.beans.property.SimpleStringProperty(TipoPlan.PERSONALIZADO.getNombreMostrar());
            }
            return new javafx.beans.property.SimpleStringProperty("Desconocido");
        });

        // Asegurar que el panel dinámico inicie oculto
        if (panelPersonalizado != null) {
            panelPersonalizado.setVisible(false);
        }

        actualizarTabla();
    }

    /**
     * Muestra u oculta el VBox al seleccionar el Tipo de Plan
     */
    @FXML
    void onTipoPlanSeleccionado(ActionEvent event) {
        TipoPlan seleccionado = cmbTipoPlan.getValue();
        if (seleccionado == TipoPlan.PERSONALIZADO) {
            panelPersonalizado.setVisible(true);
        } else {
            panelPersonalizado.setVisible(false);
        }
    }

    /**
     * Procesa la creacion del plan usando la Factory correspondiente
     */
    @FXML
    void onGuardarPlan(ActionEvent event) {
        String nombre = txtNombrePlan.getText().trim();
        String precioStr = txtPrecioPlan.getText().trim();
        String codigoStr = txtCodigoPlan.getText().trim();
        TipoPlan tipo = cmbTipoPlan.getValue();

        if (nombre.isEmpty() || precioStr.isEmpty() || codigoStr.isEmpty() || tipo == null) {
            mostrarAlerta("Campos Incompletos", "Por favor ingrese Nombre, Precio, Código y Tipo de Plan.", Alert.AlertType.WARNING);
            return;
        }

        try {
            double precio = Double.parseDouble(precioStr);
            int codigo = Integer.parseInt(codigoStr);

            // Valores por defecto para atributos generales no capturados en el formulario
            String descripcion = "Plan " + tipo.getNombreMostrar();
            int duracionMeses = 1;
            Estado estado = Estado.ACTIVO;

            FactoryPlan factory = null;

            if (tipo == TipoPlan.BASICO) {
                factory = new FactoryPLanBasico();

            } else if (tipo == TipoPlan.PREMIUM) {
                factory = new FactoryPlanPremium();

            } else if (tipo == TipoPlan.PERSONALIZADO) {
                String sesionesStr = txtCantSesiones.getText().trim();
                String especialidad = txtEspecialidad.getText().trim();
                String objetivos = txtObjetivos.getText().trim();
                Entrenador entrenador = cmbEntrenador.getValue();

                if (sesionesStr.isEmpty() || especialidad.isEmpty() || objetivos.isEmpty()) {
                    mostrarAlerta("Campos Requeridos", "Complete los campos del Plan Personalizado.", Alert.AlertType.WARNING);
                    return;
                }

                int sesiones = Integer.parseInt(sesionesStr);

                // Se usa el constructor parametrizado del Factory personalizado que diseñaste
                factory = new FactoryPLanPersonalizado(sesiones, especialidad, objetivos, entrenador);
            }

            if (factory != null) {
                // Creacion polimorfica del plan a traves de la interfaz FactoryPlan
                PlanEntrenamiento nuevoPlan = factory.crearPlan(nombre, codigo, descripcion, duracionMeses, precio, estado);

                gimnasio.registrarPlan(nuevoPlan);
                mostrarAlerta("Éxito", "El plan se ha registrado correctamente.", Alert.AlertType.INFORMATION);
                limpiarFormulario();
                actualizarTabla();
            }

        } catch (NumberFormatException e) {
            mostrarAlerta("Error de Formato", "El precio debe ser un número decimal y el código/sesiones deben ser enteros.", Alert.AlertType.ERROR);
        }
    }

    /**
     * Refresca la TableView con los datos del gimnasio
     */
    public void actualizarTabla() {
        if (gimnasio != null && gimnasio.getListPlanEntrenamiento() != null) {
            listaPlanesObservable = FXCollections.observableArrayList(gimnasio.getListPlanEntrenamiento());
            tblPlanes.setItems(listaPlanesObservable);
            tblPlanes.refresh();
        }
    }

    /**
     * Resetea el formulario a su estado inicial
     */
    private void limpiarFormulario() {
        txtNombrePlan.clear();
        txtPrecioPlan.clear();
        txtCodigoPlan.clear();
        cmbTipoPlan.getSelectionModel().clearSelection();
        txtCantSesiones.clear();
        txtEspecialidad.clear();
        txtObjetivos.clear();
        cmbEntrenador.getSelectionModel().clearSelection();
        panelPersonalizado.setVisible(false);
    }

    /**
     * Navegacion de regreso al Dashboard
     */
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
