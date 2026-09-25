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
import org.example.parcial1.model.Cliente;
import org.example.parcial1.model.Gimnasio;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ClienteViewController implements Initializable {

    @FXML private TextField txtClienteDoc;
    @FXML private TextField txtClienteNombre;
    @FXML private TextField txtClienteTel;

    @FXML private TextField txtBuscarTel;
    @FXML private Label lblResultadoTelefono;


    @FXML private TableView<Cliente> tblClientes;
    @FXML private TableColumn<Cliente, Integer> colClienteDoc;
    @FXML private TableColumn<Cliente, String> colClienteNombre;
    @FXML private TableColumn<Cliente, Integer> colClienteTel;

    private Gimnasio gimnasio;
    private ObservableList<Cliente> listaClientesObservable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gimnasio = Gimnasio.getInstance();
        colClienteDoc.setCellValueFactory(new PropertyValueFactory<>("documento"));
        colClienteNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colClienteTel.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        actualizarTabla();
    }

    @FXML
    void onGuardarCliente(ActionEvent event) {
        String docText = txtClienteDoc.getText().trim();
        String nombreText = txtClienteNombre.getText().trim();
        String telText = txtClienteTel.getText().trim();

        if (docText.isEmpty() || nombreText.isEmpty() || telText.isEmpty()) {
            mostrarAlerta("Campos Requeridos", "Por favor, complete Documento, Nombre y Teléfono.", Alert.AlertType.WARNING);
            return;
        }
        try {
            int documento = Integer.parseInt(docText);
            int telefono = Integer.parseInt(telText);

            Cliente nuevoCliente = new Cliente(
                    nombreText,
                    documento,
                    telefono,
                    "N/A",
                    0,
                    LocalDate.now()
            );

            boolean registrado = gimnasio.registrarCliente(nuevoCliente);

            if (registrado) {
                mostrarAlerta("Éxito", "Cliente registrado correctamente.", Alert.AlertType.INFORMATION);
                limpiarCamposFormulario();
                actualizarTabla();
            } else {
                mostrarAlerta("Error", "Ya existe un cliente registrado con el documento: " + documento, Alert.AlertType.ERROR);
            }

        } catch (NumberFormatException e) {
            mostrarAlerta("Formato Incorrecto", "El documento y el teléfono deben ser números enteros válidos.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void onValidarTelefonoPerfecto(ActionEvent event) {
        String inputTel = txtBuscarTel.getText().trim();

        if (inputTel.isEmpty()) {
            lblResultadoTelefono.setText("Por favor, ingrese un número de teléfono.");
            return;
        }

        try {
            int telefono = Integer.parseInt(inputTel);

            boolean esPerfecto = gimnasio.esNumeroPerfecto(telefono);
            Cliente clienteEncontrado = gimnasio.buscarClientePorTelefono(telefono);

            StringBuilder resultado = new StringBuilder();

            if (esPerfecto) {
                resultado.append("¡El teléfono ").append(telefono).append(" ES perfecto! ");
            } else {
                resultado.append("El teléfono ").append(telefono).append(" NO es perfecto. ");
            }

            if (clienteEncontrado != null) {
                resultado.append("Cliente: ").append(clienteEncontrado.getNombre());
            } else {
                resultado.append("(Sin cliente asignado)");
            }

            lblResultadoTelefono.setText(resultado.toString());

        } catch (NumberFormatException e) {
            lblResultadoTelefono.setText("Error: Ingrese únicamente dígitos numéricos.");
        }
    }

    public void actualizarTabla() {
        if (gimnasio != null && gimnasio.getListCliente() != null) {
            listaClientesObservable = FXCollections.observableArrayList(gimnasio.getListCliente());
            tblClientes.setItems(listaClientesObservable);
            tblClientes.refresh();
        }
    }

    private void limpiarCamposFormulario() {
        txtClienteDoc.clear();
        txtClienteNombre.clear();
        txtClienteTel.clear();
    }

    @FXML
    void onVolverMenu(ActionEvent event) {
        try {
            // Intenta cargar la vista (ajusta el nombre si en tu proyecto es main-view.fxml)
            URL fxmlUrl = getClass().getResource("/org/example/parcial1/main.fxml");
            if (fxmlUrl == null) {
                fxmlUrl = getClass().getResource("/org/example/parcial1/main-view.fxml");
            }

            if (fxmlUrl == null) {
                System.err.println("¡ERROR! No se encontró el archivo FXML del menú principal.");
                return;
            }

            FXMLLoader loader = new FXMLLoader(fxmlUrl);
            Parent root = loader.load();

            // Obtiene la ventana a partir del botón que lanzó el evento
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