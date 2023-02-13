package ui.pantalla.mazmorra;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import ui.common.BaseScreenController;

public class MazmorraController extends BaseScreenController {
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


    //panel visual de la interfaz
    @FXML
    private GridPane roomGridPane;
    //primera fila
    @FXML
    private Button r0c0;
    @FXML
    private Button r0c2;
    @FXML
    private Button r0c1;
    @FXML
    private Button r0c3;
    @FXML
    private Button r0c4;
    //segunda fila
    @FXML
    private Button r1c0;
    @FXML
    private Button r1c2;
    @FXML
    private Button r1c1;
    @FXML
    private Button r1c3;
    @FXML
    private Button r1c4;
    //tercera fila
    @FXML
    private Button r2c0;
    @FXML
    private Button r2c1;
    @FXML
    private Button r2c2;
    @FXML
    private Button r2c3;
    @FXML
    private Button r2c4;
    //cuarta fila
    @FXML
    private Button r3c0;
    @FXML
    private Button r3c1;
    @FXML
    private Button r3c2;
    @FXML
    private Button r3c3;
    @FXML
    private Button r3c4;
    //quinta fila
    @FXML
    private Button r4c0;
    @FXML
    private Button r4c1;
    @FXML
    private Button r4c2;
    @FXML
    private Button r4c3;
    @FXML
    private Button r4c4;

    @Override
    public void principalCargado() {
        super.principalCargado();
        Image muro = new Image(getClass().getResourceAsStream("img/Muro.jpg"));
        Image puerta = new Image(getClass().getResourceAsStream("img/Puerta.jpg"));
        Image cofre = new Image(getClass().getResourceAsStream("img/Puerta.jpg"));
        Image mago = new Image(getClass().getResourceAsStream("img/Mago.jpg"));
        Image enemigo = new Image(getClass().getResourceAsStream("img/Enemigo.jpg"));
        Image suelo1 = new Image(getClass().getResourceAsStream("img/Suelo1.jpg"));
        Image suelo2 = new Image(getClass().getResourceAsStream("img/Suelo2.jpg"));
        Image suelo3 = new Image(getClass().getResourceAsStream("img/Suelo3.jpg"));
        ImageView muroView = new ImageView(muro);
        ImageView puertaView = new ImageView(puerta);
        ImageView cofreView = new ImageView(cofre);
        ImageView magoView = new ImageView(mago);
        ImageView enemigoView = new ImageView(enemigo);
        ImageView suelo1View = new ImageView(suelo1);
        ImageView suelo2View = new ImageView(suelo2);
        ImageView suelo3View = new ImageView(suelo3);

        r0c0.setGraphic(muroView);
        r0c1.setGraphic(muroView);
        r0c2.setGraphic(puertaView);
        r0c3.setGraphic(muroView);
        r0c4.setGraphic(muroView);

        r1c0.setGraphic(muroView);
        r1c1.setGraphic(cofreView);
        r1c2.setGraphic(suelo1View);
        r1c3.setGraphic(suelo3View);
        r1c4.setGraphic(muroView);

        r2c0.setGraphic(puertaView);
        r2c1.setGraphic(suelo3View);
        r2c2.setGraphic(enemigoView);
        r2c3.setGraphic(suelo2View);
        r2c4.setGraphic(puertaView);

        r3c0.setGraphic(muroView);
        r3c1.setGraphic(suelo1View);
        r3c2.setGraphic(magoView);
        r3c3.setGraphic(suelo1View);
        r3c4.setGraphic(muroView);

        r4c0.setGraphic(muroView);
        r4c1.setGraphic(muroView);
        r4c2.setGraphic(puertaView);
        r4c3.setGraphic(muroView);
        r4c4.setGraphic(muroView);

        //TODO, al darle al boton de empezar mazmorra peta porque el xml que tenemos no es el final
        // intenta hacer una llamada a un nodo del xml que no encuentra, devuelve nulo y salta el error

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

