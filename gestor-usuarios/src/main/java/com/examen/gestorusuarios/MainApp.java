package com.examen.gestorusuarios;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainApp extends Application {

    private final ObservableList<Usuarios> listaUsuarios = FXCollections.observableArrayList();
    private TableView<Usuarios> tabla;
    private TextField campoCorreo;
    private ComboBox<String> comboPlataforma;
    private TextField campoVersion;
    private CheckBox checkAdmin;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Gestor de usuarios");
        BorderPane root = new BorderPane();
        root.setCenter(crearTabla());
        root.setRight(crearFormulario());
        configurarModalDetalles();
        Scene scene = new Scene(root, 800, 400);
        stage.setScene(scene);
        stage.show();
    }

    private VBox crearTabla() {
        tabla = new TableView<>(listaUsuarios);

        TableColumn<Usuarios, String> colCorreo = new TableColumn<>("Correo");
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Usuarios, String> colPlataforma = new TableColumn<>("Plataforma");
        colPlataforma.setCellValueFactory(new PropertyValueFactory<>("plataforma"));

        TableColumn<Usuarios, String> colVersion = new TableColumn<>("Versión");
        colVersion.setCellValueFactory(new PropertyValueFactory<>("version"));

        TableColumn<Usuarios, Boolean> colAdmin = new TableColumn<>("Administrador");
        colAdmin.setCellValueFactory(new PropertyValueFactory<>("admin"));
        colAdmin.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(Boolean admin, boolean empty) {
                super.updateItem(admin, empty);
                setText(empty ? null : (admin != null && admin ? "Sí" : "No"));
            }
        });

        TableColumn<Usuarios, String> colCreado = new TableColumn<>("Fecha creación");
        colCreado.setCellValueFactory(cell -> Bindings.createStringBinding(
                () -> cell.getValue().getCreado().toString(), cell.getValue().creadoProperty()));

        tabla.getColumns().addAll(colCorreo, colPlataforma, colVersion, colAdmin, colCreado);

        return new VBox(new Label("Usuarios"), tabla);
    }

    private VBox crearFormulario() {
        campoCorreo = new TextField();
        comboPlataforma = new ComboBox<>();
        comboPlataforma.getItems().addAll("Windows", "Linux", "macOS");
        campoVersion = new TextField();
        checkAdmin = new CheckBox("Es administrador");

        Button botonAgregar = new Button("Añadir");
        botonAgregar.setOnAction(e -> agregarUsuario());

        Button botonVaciar = new Button("Vaciar");
        botonVaciar.setOnAction(e -> vaciarTabla());

        VBox form = new VBox(
                new Label("Añadir Usuario"),
                new Label("Correo:"), campoCorreo,
                new Label("Plataforma:"), comboPlataforma,
                new Label("Versión:"), campoVersion,
                checkAdmin,
                botonAgregar,
                botonVaciar
        );
        form.setSpacing(5);
        form.setPrefWidth(250);

        return form;
    }

    private void agregarUsuario() {
        String correo = campoCorreo.getText() != null ? campoCorreo.getText().trim() : "";
        String plataforma = comboPlataforma.getValue();
        String version = campoVersion.getText() != null ? campoVersion.getText().trim() : "";
        boolean esAdmin = checkAdmin.isSelected();

        if (correo.isEmpty()) {
            DialogosApp.mostrarFaltanDatos();
            return;
        }

        Usuarios nuevo = new Usuarios(correo, plataforma != null ? plataforma : "", version, esAdmin);
        listaUsuarios.add(nuevo);
        limpiarFormulario();
    }

    private void vaciarTabla() {
        if (DialogosApp.confirmarVaciado()) {
            listaUsuarios.clear();
            limpiarFormulario();
        }
    }

    private void configurarModalDetalles() {
        tabla.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                DialogosApp.mostrarDetallesUsuario(newSel);
                tabla.getSelectionModel().clearSelection();
            }
        });
    }

    private void limpiarFormulario() {
        campoCorreo.clear();
        comboPlataforma.getSelectionModel().clearSelection();
        campoVersion.clear();
        checkAdmin.setSelected(false);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
