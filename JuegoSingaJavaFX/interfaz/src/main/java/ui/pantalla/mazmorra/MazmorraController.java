package ui.pantalla.mazmorra;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
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
        setImagenes();
        //cargar las imagenes que toquen y activar las funciones de los botones segun la habitacion en la que esté
        //en los que se pongan muros/suelos se desactivan
        //en los que se pongan puertas, te envie a la habitacion en esa direccion
        //en el que se ponga un cofre, abre el cofre
        //en el que se ponga el enemigo, empieza la lucha y se abre un alert con botones de los posibles ataques (pegarse o usar los hechizos)
        //en el que se ponga el mago, pues busca objetos y le salga un alert con una lista de los objetos(que puedas seleccionar varios) y un boton de recoger

        //que la vida y la energia se muestren como barras de colores y debajo un boton cuadrado con un icono que te abra su inventario
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

    private void setImagenes(){
        Image mago = new Image(getClass().getResourceAsStream("/img/Mago.jpg"));
        Image enemigo = new Image(getClass().getResourceAsStream("/img/Enemigo.jpg"));
        Image cofre = new Image(getClass().getResourceAsStream("/img/Cofre.jpg"));
        Image puerta = new Image(getClass().getResourceAsStream("/img/Puerta.jpg"));
        Image muro = new Image(getClass().getResourceAsStream("/img/Muro.jpg"));
        Image suelo1 = new Image(getClass().getResourceAsStream("/img/Suelo1.jpg"));
        Image suelo2 = new Image(getClass().getResourceAsStream("/img/Suelo2.jpg"));
        Image suelo3 = new Image(getClass().getResourceAsStream("/img/Suelo3.jpg"));

        ImageView magoView = new ImageView(mago);
        ImageView enemigoView = new ImageView(enemigo);
        ImageView cofreView = new ImageView(cofre);
        ImageView puertaNorte = new ImageView(puerta);
        ImageView puertaOeste = new ImageView(puerta);
        ImageView puertaEste = new ImageView(puerta);
        ImageView puertaSur = new ImageView(puerta);
        ImageView muroView1 = new ImageView(muro);
        ImageView muroView2 = new ImageView(muro);
        ImageView muroView3 = new ImageView(muro);
        ImageView muroView4 = new ImageView(muro);
        ImageView muroView5 = new ImageView(muro);
        ImageView muroView6 = new ImageView(muro);
        ImageView muroView7 = new ImageView(muro);
        ImageView muroView8 = new ImageView(muro);
        ImageView muroView9 = new ImageView(muro);
        ImageView muroView10 = new ImageView(muro);
        ImageView muroView11 = new ImageView(muro);
        ImageView muroView12 = new ImageView(muro);
        ImageView sueloView1 = new ImageView(suelo1);
        ImageView sueloView2 = new ImageView(suelo2);
        ImageView sueloView3 = new ImageView(suelo3);
        ImageView sueloView4 = new ImageView(suelo1);
        ImageView sueloView5 = new ImageView(suelo2);
        ImageView sueloView6 = new ImageView(suelo3);
        ImageView sueloView7 = new ImageView(suelo1);
        ImageView sueloView8 = new ImageView(suelo2);
        ImageView sueloView9 = new ImageView(suelo3);

        int size = 75;
        magoView.setFitWidth(size);
        magoView.setFitHeight(size);
        enemigoView.setFitWidth(size);
        enemigoView.setFitHeight(size);
        cofreView.setFitWidth(size);
        cofreView.setFitHeight(size);

        puertaNorte.setFitWidth(size);
        puertaNorte.setFitHeight(size);
        puertaOeste.setFitWidth(size);
        puertaOeste.setFitHeight(size);
        puertaEste.setFitWidth(size);
        puertaEste.setFitHeight(size);
        puertaSur.setFitWidth(size);
        puertaSur.setFitHeight(size);

        muroView1.setFitWidth(size);
        muroView1.setFitHeight(size);
        muroView2.setFitWidth(size);
        muroView2.setFitHeight(size);
        muroView3.setFitWidth(size);
        muroView3.setFitHeight(size);
        muroView4.setFitWidth(size);
        muroView4.setFitHeight(size);
        muroView5.setFitWidth(size);
        muroView5.setFitHeight(size);
        muroView6.setFitWidth(size);
        muroView6.setFitHeight(size);
        muroView7.setFitWidth(size);
        muroView7.setFitHeight(size);
        muroView8.setFitWidth(size);
        muroView8.setFitHeight(size);
        muroView9.setFitWidth(size);
        muroView9.setFitHeight(size);
        muroView10.setFitWidth(size);
        muroView10.setFitHeight(size);
        muroView11.setFitWidth(size);
        muroView11.setFitHeight(size);
        muroView12.setFitWidth(size);
        muroView12.setFitHeight(size);

        sueloView1.setFitWidth(size);
        sueloView1.setFitHeight(size);
        sueloView2.setFitWidth(size);
        sueloView2.setFitHeight(size);
        sueloView3.setFitWidth(size);
        sueloView3.setFitHeight(size);
        sueloView4.setFitWidth(size);
        sueloView4.setFitHeight(size);
        sueloView5.setFitWidth(size);
        sueloView5.setFitHeight(size);
        sueloView6.setFitWidth(size);
        sueloView6.setFitHeight(size);
        sueloView7.setFitWidth(size);
        sueloView7.setFitHeight(size);
        sueloView8.setFitWidth(size);
        sueloView8.setFitHeight(size);
        sueloView9.setFitWidth(size);
        sueloView9.setFitHeight(size);

        r0c0.setGraphic(muroView1);
        r0c0.setPadding(Insets.EMPTY);
        r0c1.setGraphic(muroView2);
        r0c1.setPadding(Insets.EMPTY);
        r0c2.setGraphic(puertaNorte);
        r0c2.setPadding(Insets.EMPTY);
        r0c3.setGraphic(muroView3);
        r0c3.setPadding(Insets.EMPTY);
        r0c4.setGraphic(muroView4);
        r0c4.setPadding(Insets.EMPTY);

        r1c0.setGraphic(muroView5);
        r1c0.setPadding(Insets.EMPTY);
        r1c1.setGraphic(cofreView);
        r1c1.setPadding(Insets.EMPTY);
        r1c2.setGraphic(sueloView1);
        r1c2.setPadding(Insets.EMPTY);
        r1c3.setGraphic(sueloView2);
        r1c3.setPadding(Insets.EMPTY);
        r1c4.setGraphic(muroView6);
        r1c4.setPadding(Insets.EMPTY);

        r2c0.setGraphic(puertaOeste);
        r2c0.setPadding(Insets.EMPTY);
        r2c1.setGraphic(sueloView3);
        r2c1.setPadding(Insets.EMPTY);
        r2c2.setGraphic(enemigoView);
        r2c2.setPadding(Insets.EMPTY);
        r2c3.setGraphic(sueloView4);
        r2c3.setPadding(Insets.EMPTY);
        r2c4.setGraphic(puertaEste);
        r2c4.setPadding(Insets.EMPTY);

        r3c0.setGraphic(muroView7);
        r3c0.setPadding(Insets.EMPTY);
        r3c1.setGraphic(sueloView5);
        r3c1.setPadding(Insets.EMPTY);
        r3c2.setGraphic(magoView);
        r3c2.setPadding(Insets.EMPTY);
        r3c3.setGraphic(sueloView6);
        r3c3.setPadding(Insets.EMPTY);
        r3c4.setGraphic(muroView8);
        r3c4.setPadding(Insets.EMPTY);

        r4c0.setGraphic(muroView9);
        r4c0.setPadding(Insets.EMPTY);
        r4c1.setGraphic(muroView10);
        r4c1.setPadding(Insets.EMPTY);
        r4c2.setGraphic(puertaSur);
        r4c2.setPadding(Insets.EMPTY);
        r4c3.setGraphic(muroView11);
        r4c3.setPadding(Insets.EMPTY);
        r4c4.setGraphic(muroView12);
        r4c4.setPadding(Insets.EMPTY);
    }
}

