package ui.pantalla.mazmorra;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import ui.common.BaseScreenController;

public class MazmorraController extends BaseScreenController {

    @FXML
    private GridPane roomGridPane;
    @FXML
    private Button recogerCofre;
    @FXML
    private Button huir;
    @FXML
    private Button luchar;
    @FXML
    private Button lanzarHechizos;
    @FXML
    private Button abrirCofre;
    @FXML
    private Label vidaMago;
    @FXML
    private Label energiaMago;
    @FXML
    private Label cristalesMago;
    @FXML
    private Label vidaCriatura;
    @FXML
    private Label ataqueCriatura;
    @FXML
    private Label tipoCriatura;
    @FXML
    private Label tiempo;

    @Override
    public void principalCargado() {
        super.principalCargado();
        //tener un lugar donde se vea la vida del mago, la energia y el inventario
        //en vez de una caja de texto para ver los datos de la habitacion usar imagenes

        //cargar los datos de la habitacion
        //cada puerta se escriba en un boton
        //añadir la funcionalidad al resto de botones, y que si se pulsan cuando no se puede
        //si hay enemigo solo puedes pegarte o salir popr la puerta que has entrado
        //si no hay cofre el boton de abrir cofre, salte un mensaje
        //si no al buscar en la habitacion no se encuentra nada, salte un mensaje
        //si se encuentra algo que refresque la imagen de la habitacion con un dibujo con el objeto y que al clicar se recoja

    }

    @FXML
    private void recogerCofre(ActionEvent actionEvent) {
    }

    @FXML
    private void huir(ActionEvent actionEvent) {
    }

    @FXML
    private void luchar(ActionEvent actionEvent) {
    }

    @FXML
    private void lanzarHechizos(ActionEvent actionEvent) {
    }

    @FXML
    private void abrirCofre(ActionEvent actionEvent) {
    }
}

