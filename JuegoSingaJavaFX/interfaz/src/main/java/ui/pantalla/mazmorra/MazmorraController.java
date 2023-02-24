package ui.pantalla.mazmorra;

import game.actions.Attack;
import game.character.Creature;
import game.character.Wizard;
import game.character.exceptions.CharacterKilledException;
import game.character.exceptions.WizardNotEnoughEnergyException;
import game.character.exceptions.WizardTiredException;
import game.dungeon.Door;
import game.dungeon.Room;
import game.dungeon.Site;
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
import ui.common.Screens;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MazmorraController extends BaseScreenController {
    @FXML
    private Label vida;
    @FXML
    private Label vidaMax;
    @FXML
    private Label energia;
    @FXML
    private Label energiaMax;
    @FXML
    private GridPane roomGridPane;
    @FXML
    private Button norte;
    @FXML
    private Button este;
    @FXML
    private Button oeste;
    @FXML
    private Button sur;
    @FXML
    private TextArea logMazmorra;
    @FXML
    private ComboBox tipoAtaque;

    private Room roomActual;
    private Wizard wizard;
    private Creature creature;

    private int buttonSize = 50;

    @Override
    public void principalCargado() {
        super.principalCargado();
        wizard = getPrincipalController().getDemiurge().getWizard();
        setEmptyRoom();
        loadRoom(getPrincipalController().getDemiurge().getDungeon().getRoom(0));
        setLogMazmorra(logMazmorra);

        vida.setText(wizard.getLife() + "/");
        vidaMax.setText(wizard.getLifeMax() + "");
        energia.setText(wizard.getEnergy() + "/");
        energiaMax.setText(wizard.getEnergyMax() + "");

        getPrincipalController().getDemiurge().getDungeonManager().getAttacksIterator().forEachRemaining(attack -> tipoAtaque.getItems().add(attack));
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

    private void loadRoom(Room room) {
        roomActual = room;
        vida.setText(getPrincipalController().getDemiurge().getWizard().getLife() + "");
        energia.setText(getPrincipalController().getDemiurge().getWizard().getEnergy() + "");

        setPuertas(room);
        roomGridPane.getChildren().forEach((Node node) -> {
            int rowIndex = GridPane.getRowIndex(node) == null ? 0 : GridPane.getRowIndex(node);
            int columnIndex = GridPane.getColumnIndex(node) == null ? 0 : GridPane.getColumnIndex(node);
            if (node instanceof Button button) {
                setMago(rowIndex, columnIndex, button);
                setEnemigo(rowIndex, columnIndex, button);
                setCofre(room, rowIndex, columnIndex, button);
                setCrystalFarm(room, rowIndex, columnIndex, button);
            }
        });
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

    private void setPuertas(Room room) {
        List<Site> connectedSites = new ArrayList<>();
        //carga la lista de las habitaciones conectadas
        room.iterator().forEachRemaining(door ->
                connectedSites.add(door.showFrom(room))
        );

        //la primera puerta va a ser la puerta del norte, la segunda la del este, la tercera la del sur y la cuarta la del oeste
        //si hay mas de 4 puertas, no se cargan, es lo que hay
        //Ya que en el xml no tienen ningun orden
        room.iterator().forEachRemaining(door -> {
            if (connectedSites.get(0).equals(door.showFrom(room)))
                setPuerta(door, norte);
            else if (connectedSites.get(1).equals(door.showFrom(room)))
                setPuerta(door, este);
            else if (connectedSites.get(2).equals(door.showFrom(room)))
                setPuerta(door, sur);
            else if (connectedSites.get(3).equals(door.showFrom(room)))
                setPuerta(door, oeste);
        });
    }

    private void setPuerta(Door door, Button button) {
        Image puerta;
        if (door.isUsed())
            puerta = new Image(getClass().getResourceAsStream("/img/PuertaAbierta.jpg"));
        else
            puerta = new Image(getClass().getResourceAsStream("/img/Puerta.jpg"));

        ImageView imgView = new ImageView(puerta);
        imgView.setFitWidth(buttonSize);
        imgView.setFitHeight(buttonSize);
        button.setDisable(false);
        button.setGraphic(imgView);

        button.setOnAction(actionEvent -> {
            Site roomNueva = door.openFrom(roomActual);
            if (!roomNueva.isExit()) {
                if (roomNueva.getID() == -1) {
                    getPrincipalController().getDemiurge().getHomeManager().enterHome();
                    getPrincipalController().cargarPantalla(Screens.CASA_MAGO);
                } else {
                    loadRoom((Room) roomNueva);
                }
            } else {
                //end game
                getPrincipalController().getDemiurge().getEndChecker().getConditions().get(0).check();
                getPrincipalController().getDemiurge().getEndChecker().check();
            }
        });
    }

    private void setMago(int rowIndex, int columnIndex, Button button) {
        if (rowIndex == 4 && columnIndex == 3) {
            Image mago = new Image(getClass().getResourceAsStream("/img/Mago.jpg"));
            ImageView imgView = new ImageView(mago);
            imgView.setFitWidth(buttonSize);
            imgView.setFitHeight(buttonSize);
            button.setDisable(false);
            button.setGraphic(imgView);

            button.setOnAction(actionEvent -> {
                //TODO setOnClickListener al usarlo se abre el inventario o algo
            });
        }
    }

    private void setEnemigo(int rowIndex, int columnIndex, Button button) {
        //cuando el enemigo muere, lo que se quedan es el cadaver, pero al irse de la habitacion desaparecerá
        if (roomActual.isAlive() && rowIndex == 3 && columnIndex == 3) {
            if (roomActual.getCreature().getLife() > 0) {
                Image enemigo = new Image(getClass().getResourceAsStream("/img/Enemigo.jpg"));
                ImageView imgView = new ImageView(enemigo);
                imgView.setFitWidth(buttonSize);
                imgView.setFitHeight(buttonSize);
                button.setDisable(false);
                button.setGraphic(imgView);

                //setOnClickListener al usarlo te tendrias que pegar
                getPrincipalController().getDemiurge().getDungeonManager().setCreature(roomActual.getCreature());
                button.setOnAction(actionEvent -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Lucha");
                    alert.setHeaderText("No has seleccionado un tipo de ataque");
                    alert.setContentText("Selecciona un tipo de ataque");

                    if (roomActual.isAlive() && tipoAtaque.getValue() != null
                            && wizard.getLife() > 0 && wizard.getEnergy() > 1) {
                        try {
                            getPrincipalController().getDemiurge().getDungeonManager().wizardAttack((Attack) tipoAtaque.getValue());
                        } catch (CharacterKilledException | WizardNotEnoughEnergyException | WizardTiredException e) {
                            throw new RuntimeException(e);
                        }

                        try {
                            getPrincipalController().getDemiurge().getDungeonManager().creatureAttack();
                        } catch (CharacterKilledException e) {
                            throw new RuntimeException(e);
                        }

                        if (roomActual.getCreature().getLife() >= 0) {
                            alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Lucha");
                            alert.setHeaderText("Ataque hecho");
                            alert.setContentText("La criatura tiene " + roomActual.getCreature().getLife() + " de vida," +
                                    " y tu tienes " + wizard.getLife() + " de vida");
                        } else if (wizard.getLife() <= 0) {
                            alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Lucha");
                            alert.setHeaderText("Has muerto");
                            alert.setContentText("La criatura tiene " + roomActual.getCreature().getLife() + " de vida," +
                                    " y tu tienes " + wizard.getLife() + " de vida");
                        } else if (roomActual.getCreature().getLife() <= 0) {
                            alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Lucha");
                            alert.setHeaderText("Has ganado");
                            alert.setContentText("La criatura tiene " + roomActual.getCreature().getLife() + " de vida," +
                                    " y tu tienes " + wizard.getLife() + " de vida");

                        }
                    } else if (!roomActual.isAlive() || roomActual.getCreature().getLife() <= 0) {
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Lucha");
                        alert.setHeaderText("No hay enemigo");
                        alert.setContentText("Esta sala no tiene enemigos");
                    } else if (wizard.getEnergy() <= 1) {
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Lucha");
                        alert.setHeaderText("No tienes energia");
                        alert.setContentText("No tienes energia para atacar");
                    }
                    alert.showAndWait();
                    loadRoom(roomActual);
                });
            }else{
                Image suelo = new Image(getClass().getResourceAsStream("/img/suelo.jpg"));
                ImageView imgView = new ImageView(suelo);
                imgView.setFitWidth(buttonSize);
                imgView.setFitHeight(buttonSize);
                button.setDisable(true);
                button.setGraphic(imgView);
            }
        }
    }

    private void setCofre(Room room, int rowIndex, int columnIndex, Button button) {
        //esto se carga, si los objetos que hay dentro de una habitacion se cargasen en el container que tiene un Site
        if (room.getContainer().isEmpty() && rowIndex == 1 && columnIndex == 1) {
            Image cofre = new Image(getClass().getResourceAsStream("/img/Cofre.jpg"));
            ImageView imgView = new ImageView(cofre);
            imgView.setFitWidth(buttonSize);
            imgView.setFitHeight(buttonSize);
            button.setDisable(false);
            button.setGraphic(imgView);

            button.setOnAction(actionEvent -> {
                //TODO setOnClickListener al usarlo se abre el cofre
                // (en el lateral o un alert con una lista de los objetos que hay dentro y tu inventario y un boton para mover los objetos seleccionados a la otra tabla)
            });
        }
    }

    private void setCrystalFarm(Room room, int rowIndex, int columnIndex, Button button) {
        if (room.isEmpty() && rowIndex == 1 && columnIndex == 5) {
            Image crystalFarm = new Image(getClass().getResourceAsStream("/img/CrystalFarm.jpg"));
            ImageView imgView = new ImageView(crystalFarm);
            imgView.setFitWidth(buttonSize);
            imgView.setFitHeight(buttonSize);
            button.setDisable(false);
            button.setGraphic(imgView);

            //setOnClickListener al usarlo se recogen los cristales (salta un alert con los cristales que se han recogido
            button.setOnAction((ActionEvent event) -> {
                getPrincipalController().getDemiurge().getDungeonManager().gatherCrystals();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Cristales");
                if (!getPrincipalController().getDemiurge().getWizard().getCrystalCarrier().isFull()) {
                    alert.setContentText("Has recogido los cristales:");
                } else {
                    alert.setContentText("Tu mochila esta llena, no puedes recoger mas cristales");
                }
                alert.showAndWait();
            });
        }
    }
}

