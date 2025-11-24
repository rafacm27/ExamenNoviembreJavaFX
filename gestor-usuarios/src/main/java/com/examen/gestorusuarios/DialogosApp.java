package com.examen.gestorusuarios;


import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public final class DialogosApp {

    private DialogosApp() {}

    public static void mostrarFaltanDatos() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Aviso");
        alert.setHeaderText(null);
        alert.setContentText("Faltan datos");
        alert.showAndWait();
    }

    public static boolean confirmarVaciado() {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Confirmación");
        alert.setHeaderText("¿Vaciar todos los datos?");
        alert.setContentText("Se eliminarán todos los usuarios.");
        alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    public static void mostrarDetallesUsuario(Usuarios usuario) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Detalles del usuario");
        alert.setHeaderText(null);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String content = String.format(
                "Correo: %s%nPlataforma: %s%nVersión: %s%nAdministrador: %s%nCreado: %s",
                usuario.getEmail(),
                usuario.getPlataforma(),
                usuario.getVersion(),
                usuario.isAdmin() ? "Sí" : "No",
                usuario.getCreado().format(fmt)
        );
        alert.setContentText(content);
        alert.showAndWait();
    }
}
