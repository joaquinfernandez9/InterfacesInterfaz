module interfaz {
    requires jakarta.cdi;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;
    requires jakarta.inject;
    requires JuegoSingaJavaFx;
    requires lombok;
    requires org.apache.logging.log4j;

    exports ui.main;
    exports ui.common;
    exports ui.pantalla.principal;
    exports ui;
    exports ui.pantalla.casaMago;
    exports ui.pantalla.menu;
    exports ui.pantalla.mazmorra;

    opens ui.main;
    opens ui.pantalla.casaMago;
    opens ui.pantalla.menu;
    opens ui.common;
    opens ui.pantalla.principal;
}