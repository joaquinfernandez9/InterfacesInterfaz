module JuegoSingaJavaFx {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.cdi;
    requires jakarta.inject;
    requires lombok;
    requires org.apache.logging.log4j;


    exports game.objectContainer.exceptions;
    exports game.character.exceptions;
    exports game.dungeon;
    exports game.demiurge;
    exports game.demiurge.exceptions;
    exports game.object;
    exports game.conditions;
    exports game.spell;
    exports game.spellContainer;
    exports game.util;
    exports game;
    exports console;
}