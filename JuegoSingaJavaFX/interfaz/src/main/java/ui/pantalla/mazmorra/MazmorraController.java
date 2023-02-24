package ui.pantalla.mazmorra;

import game.dungeon.Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
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
        getPrincipalController().getDemiurge().getDungeon().iterator().next();
        setEmptyRoom();
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

    private void setLogMazmorra(TextArea textArea) {
        textArea.setStyle("-fx-background-color: #464b82");
        textArea.setStyle("-fx-text-fill: #f3d3ac");
    }

    private void setEmptyRoom() {
        Random random = new Random();
        Image muro = new Image(getClass().getResourceAsStream("/img/Muro.jpg"));
        Image suelo1 = new Image(getClass().getResourceAsStream("/img/Suelo1.jpg"));
        Image suelo2 = new Image(getClass().getResourceAsStream("/img/Suelo2.jpg"));
        Image suelo3 = new Image(getClass().getResourceAsStream("/img/Suelo3.jpg"));

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
    }

    private void loadRoom(Room room) {
        roomGridPane.getChildren().forEach((Node node) -> {
            int rowIndex = GridPane.getRowIndex(node) == null ? 0 : GridPane.getRowIndex(node);
            int columnIndex = GridPane.getColumnIndex(node) == null ? 0 : GridPane.getColumnIndex(node);
            if (node instanceof Button button) {
                setMago(rowIndex, columnIndex, button);
                setEnemigo(room, rowIndex, columnIndex, button);
                setCofre(room, rowIndex, columnIndex, button);
                setCrystalFarm(room, rowIndex, columnIndex, button);
            }
        });
        //TODO cargar las puertas de la habitacion
    }

    private void setMago(int rowIndex, int columnIndex, Button button){
        if (rowIndex == 4 && columnIndex == 3) {
            Image mago = new Image(getClass().getResourceAsStream("/img/Mago.jpg"));
            ImageView imgView = new ImageView(mago);
            imgView.setFitWidth(buttonSize);
            imgView.setFitHeight(buttonSize);
            button.setDisable(false);
            button.setGraphic(imgView);

            //TODO setOnClickListener al usarlo se abre el inventario o algo
            button.setOnAction(actionEvent -> {

            });
        }
    }

    private void setEnemigo(Room room, int rowIndex, int columnIndex, Button button){
        if (room.isAlive() && rowIndex == 3 && columnIndex == 3) {
            Image enemigo = new Image(getClass().getResourceAsStream("/img/Enemigo.jpg"));
            ImageView imgView = new ImageView(enemigo);
            imgView.setFitWidth(buttonSize);
            imgView.setFitHeight(buttonSize);
            button.setDisable(false);
            button.setGraphic(imgView);

            //TODO setOnClickListener al usarlo se empieza la lucha
            button.setOnAction(actionEvent -> {

            });
        }
    }

    private void setCofre(Room room, int rowIndex, int columnIndex, Button button){
        if (room.getContainer().isEmpty() && rowIndex == 1 && columnIndex == 1) {
            Image cofre = new Image(getClass().getResourceAsStream("/img/Cofre.jpg"));
            ImageView imgView = new ImageView(cofre);
            imgView.setFitWidth(buttonSize);
            imgView.setFitHeight(buttonSize);
            button.setDisable(false);
            button.setGraphic(imgView);

            //TODO setOnClickListener al usarlo se abre el cofre
            // (en el lateral o un alert con una lista de los objetos que hay dentro y tu inventario y un boton para mover los objetos seleccionados a la otra tabla)
        }
    }

    private void setCrystalFarm(Room room, int rowIndex, int columnIndex, Button button){
        if (room.isEmpty() && rowIndex == 1 && columnIndex == 5) {
            Image crystalFarm = new Image(getClass().getResourceAsStream("/img/CrystalFarm.jpg"));
            ImageView imgView = new ImageView(crystalFarm);
            imgView.setFitWidth(buttonSize);
            imgView.setFitHeight(buttonSize);
            button.setDisable(false);
            button.setGraphic(imgView);

            //TODO setOnClickListener al usarlo se recogen los cristales (salta un alert con los cristales que se han recogido
            button.setOnAction((ActionEvent event) -> {
                getPrincipalController().getDemiurge().getDungeonManager().gatherCrystals();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Cristales");
                if (!getPrincipalController().getDemiurge().getWizard().getCrystalCarrier().isFull()) {
                    alert.setContentText("Has recogido los cristales:");
                }else {
                    alert.setContentText("Tu mochila esta llena, no puedes recoger mas cristales");
                }
                alert.showAndWait();
            });
        }
    }
}

