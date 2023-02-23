package ui.pantalla.mazmorra;

import game.dungeon.Door;
import game.dungeon.Room;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import ui.common.BaseScreenController;

import java.util.Random;

public class MazmorraController extends BaseScreenController {
    @FXML
    private Label tiempo;
    @FXML
    private Label vidaMago;
    @FXML
    private Label energiaMago;
    @FXML
    private Label cristalesMago;
    @FXML
    private GridPane roomGridPane;
    @FXML
    private Button r0c3;
    @FXML
    private Button r3c0;
    @FXML
    private Button r3c6;
    @FXML
    private Button r6c3;
    @FXML
    private TextArea logMazmorra;
    @FXML
    private Button atacar;
    @FXML
    private Button hechizos;
    @FXML
    private Button huir;
    @FXML
    private Button buscarObjetos;
    @FXML
    private Button recogerObjetos;
    private int buttonSize = 50;


    @Override
    public void principalCargado() {
        super.principalCargado();
        setImagenes();
        setLogMazmorra(logMazmorra);
        setButton(atacar);
        setButton(hechizos);
        setButton(huir);
        setButton(buscarObjetos);
        setButton(recogerObjetos);

        //TODO al cancelar el cargar la mazmorra se  cancela la carga de la pantalla xq si no sale un nullpointer

        //fondos de https://www.artstation.com/artwork/VdErn4
        //cargar las imagenes que toquen y activar las funciones de los botones segun la habitacion en la que esté
        //en los que se pongan muros/suelos se desactivan
        //en los que se pongan puertas, te envie a la habitacion en esa direccion
        //en el que se ponga un cofre, abre el cofre
        //en el que se ponga el enemigo, empieza la lucha y se abre un alert con botones de los posibles ataques (pegarse o usar los hechizos)
        //en el que se ponga el mago, pues busca objetos y le salga un alert con una lista de los objetos(que puedas seleccionar varios) y un boton de recoger

        //que la vida y la energia se muestren como barras de colores y debajo un boton cuadrado con un icono que te abra su inventario
    }

    private void setImagenes() {
        Random random = new Random();
        Image mago = new Image(getClass().getResourceAsStream("/img/Mago.jpg"));
        Image muro = new Image(getClass().getResourceAsStream("/img/Muro.jpg"));
        Image suelo1 = new Image(getClass().getResourceAsStream("/img/Suelo1.jpg"));
        Image suelo2 = new Image(getClass().getResourceAsStream("/img/Suelo2.jpg"));
        Image suelo3 = new Image(getClass().getResourceAsStream("/img/Suelo3.jpg"));

        //pinta vacia la habitacion
        roomGridPane.getChildren().forEach((Node node) -> {
            int rowIndex = GridPane.getRowIndex(node) == null ? 0 : GridPane.getRowIndex(node);
            int columnIndex = GridPane.getColumnIndex(node) == null ? 0 : GridPane.getColumnIndex(node);

            if (node instanceof Button button) {
                ImageView imgView = new ImageView(muro);
                if ((rowIndex >= 1 && rowIndex <= 5) && (columnIndex >= 1 && columnIndex <= 5)) {
                    switch (random.nextInt(3)) {
                        case 0 -> imgView = new ImageView(suelo1);
                        case 1 -> imgView = new ImageView(suelo2);
                        case 2 -> imgView = new ImageView(suelo3);
                    }
                    if (rowIndex == 3 && columnIndex == 3) {
                        imgView = new ImageView(mago);
                    }
                }
                imgView.setFitWidth(buttonSize);
                imgView.setFitHeight(buttonSize);
                button.setGraphic(imgView);
                button.setDisable(true);
                button.setOpacity(100);
                button.setPadding(Insets.EMPTY);
                button.setBorder(Border.EMPTY);
                button.setContentDisplay(ContentDisplay.CENTER);
                button.setStyle("-fx-background-color: transparent");
            }
        });
        //segun la room, pintar en los distintas posiciones las imagenes que toquen ademas de agregarle la accion al boton
        //para eso, le tiene que llegar una room a esta funcion y a la funcion del principal controller le tiene que llegar el Demiurge
    }

    private void setLogMazmorra(TextArea textArea) {
        textArea.setStyle("-fx-background-color: #464b82");
        textArea.setStyle("-fx-text-fill: #f3d3ac");
    }

    private void setButton(Button bt) {
        Image reborde = new Image(getClass().getResourceAsStream("/img/Boton.jpg"));
        ImageView rebordeView = new ImageView(reborde);
        rebordeView.setFitHeight(bt.getWidth());
        rebordeView.setFitHeight(bt.getHeight());
        bt.setGraphic(rebordeView);
        bt.setPadding(Insets.EMPTY);
        bt.setBorder(Border.EMPTY);
        bt.setContentDisplay(ContentDisplay.CENTER);
        bt.setStyle("-fx-background-color: transparent");
    }

    private void loadRoom(Room room) {
        roomGridPane.getChildren().forEach((Node node) -> {
            int rowIndex = GridPane.getRowIndex(node) == null ? 0 : GridPane.getRowIndex(node);
            int columnIndex = GridPane.getColumnIndex(node) == null ? 0 : GridPane.getColumnIndex(node);
            if (node instanceof Button button) {
                if (room.isAlive() && rowIndex == 3 && columnIndex == 3) {
                    Image enemigo = new Image(getClass().getResourceAsStream("/img/Enemigo.jpg"));
                    ImageView imgView = new ImageView(enemigo);
                    imgView.setFitWidth(buttonSize);
                    imgView.setFitHeight(buttonSize);
                    button.setDisable(false);
                    button.setGraphic(imgView);

                    //TODO setOnClickListener al usarlo se empieza la lucha
                }

                if (room.isEmpty() && rowIndex == 1 && columnIndex == 5) {
                    Image crystalFarm = new Image(getClass().getResourceAsStream("/img/CrystalFarm.jpg"));
                    ImageView imgView = new ImageView(crystalFarm);
                    imgView.setFitWidth(buttonSize);
                    imgView.setFitHeight(buttonSize);
                    button.setDisable(false);
                    button.setGraphic(imgView);

                    //TODO setOnClickListener al usarlo se recogen los cristales (salta un alert con los cristales que se han recogido
                }

                if (room.getContainer().isEmpty() && rowIndex == 1 && columnIndex == 1) {
                    Image cofre = new Image(getClass().getResourceAsStream("/img/Cofre.jpg"));
                    ImageView imgView = new ImageView(cofre);
                    imgView.setFitWidth(buttonSize);
                    imgView.setFitHeight(buttonSize);
                    button.setDisable(false);
                    button.setGraphic(imgView);

                    //TODO setOnClickListener al usarlo se abre el cofre
                    // (en el lateral oo un alert con una lista de los objetos que hay dentro y tu inventario y un boton para mover los objetos seleccionados a la otra tabla)
                }
            }
        });

        room.iterator().forEachRemaining((Door door) -> {
            if (door.isUsed()){
                Image puertaAbierta = new Image(getClass().getResourceAsStream("/img/PuertaAbierta.jpg"));

            }else{
                Image puertaCerrada = new Image(getClass().getResourceAsStream("/img/Puerta.jpg"));

            }
        });
    }
}

