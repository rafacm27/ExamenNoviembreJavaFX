package com.examen.gestorusuarios;

import javafx.beans.property.*;
import java.time.LocalDateTime;

public class Usuarios {

    private final StringProperty email = new SimpleStringProperty(this, "email");
    private final StringProperty plataforma = new SimpleStringProperty(this, "plataforma");
    private final StringProperty version = new SimpleStringProperty(this, "version");
    private final BooleanProperty admin = new SimpleBooleanProperty(this, "admin", false);
    private final ObjectProperty<LocalDateTime> creado = new SimpleObjectProperty<>(this, "creado");

    public Usuarios(String email, String plataforma, String version, boolean admin) {
        setEmail(email);
        setPlataforma(plataforma);
        setVersion(version);
        setAdmin(admin);
        setCreado(LocalDateTime.now());
    }

    public String getEmail() { return email.get(); }
    public void setEmail(String value) { email.set(value); }
    public StringProperty emailProperty() { return email; }

    public String getPlataforma() { return plataforma.get(); }
    public void setPlataforma(String value) { plataforma.set(value); }
    public StringProperty plataformaProperty() { return plataforma; }

    public String getVersion() { return version.get(); }
    public void setVersion(String value) { version.set(value); }
    public StringProperty versionProperty() { return version; }

    public boolean isAdmin() { return admin.get(); }
    public void setAdmin(boolean value) { admin.set(value); }
    public BooleanProperty adminProperty() { return admin; }

    public LocalDateTime getCreado() { return creado.get(); }
    public void setCreado(LocalDateTime value) { creado.set(value); }
    public ObjectProperty<LocalDateTime> creadoProperty() { return creado; }
}
