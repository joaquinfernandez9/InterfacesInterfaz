package ui.pantalla.menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import ui.common.BaseScreenController;
import ui.common.Screens;

public class MenuController extends BaseScreenController {

    @FXML
    public void newGame(ActionEvent actionEvent) {
        getPrincipalController().loadXMLFile();
        getPrincipalController().cargarPantalla(Screens.CASA_MAGO);
    }

    @FXML
    private void loadGame(ActionEvent actionEvent) {
    }
}