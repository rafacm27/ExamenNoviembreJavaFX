module com.cesur.usuarios {
    requires javafx.controls;
    opens com.examen.gestorusuarios to javafx.base;
    exports com.examen.gestorusuarios;
}
