module JuegoSingaJavaFx {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.cdi;
    requires jakarta.inject;
    requires lombok;
    requires org.apache.logging.log4j;

    exports ui.main;

    exports ui.common;
    exports ui.pantalla.principal;
    exports game.objectContainer.exceptions;
    exports game.character.exceptions;
    exports game.dungeon;
    exports game.demiurge;
    exports game.demiurge.exceptions;
    exports game.object;
    exports game.conditions;exports game.spell;
    exports ui;
    exports loaderManual;
    exports game.spellContainer;
    exports game.util;
    exports ui.pantalla.casaMago;
    exports ui.pantalla.menu;
    exports ui.pantalla.mazmorra;



    opens ui.main;
    opens ui.pantalla.casaMago;
    opens ui.pantalla.menu;
    opens ui.common;
    opens ui.pantalla.principal;

}