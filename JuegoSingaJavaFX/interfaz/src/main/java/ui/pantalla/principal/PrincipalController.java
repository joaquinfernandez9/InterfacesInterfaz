package ui.pantalla.principal;

import game.DungeonLoader;
import game.demiurge.Demiurge;
import game.demiurge.DungeonConfiguration;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import lombok.extern.log4j.Log4j2;
import ui.common.BaseScreenController;
import ui.common.Screens;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Log4j2
public class PrincipalController {
    @FXML
    private MenuBar menu;
    @FXML
    private BorderPane root;

    private Stage primaryStage;

    @FXML
    private MenuItem menuHelp;

    Instance<Object> instance;
    private Demiurge demiurge;
    private DungeonConfiguration dungeonConfiguration;

    public String actualUser;

    @Inject
    public PrincipalController(Instance<Object> instance) {
        this.instance = instance;
    }

    public void cargarPantalla(Screens pantalla) {
        cambioPantalla(cargarPantalla(pantalla.getRuta()));
    }


    private Pane cargarPantalla(String ruta) {
        Pane panePantalla = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(controller -> instance.select(controller).get());
            panePantalla = fxmlLoader.load(getClass().getResourceAsStream(ruta));
            BaseScreenController pantallaController = fxmlLoader.getController();
            pantallaController.setPrincipalController(this);
            pantallaController.principalCargado();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return panePantalla;
    }

    private void cambioPantalla(Pane pantallaNueva) {
        root.setCenter(pantallaNueva);
    }

    public void loadXMLFile() {
        try {
            //Load file from disk
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("XML Files", "*.xml"));
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            if (selectedFile != null) {
                loadEnviranment(selectedFile);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public void initialize() {
        menu.setVisible(true);
        cargarPantalla(Screens.CASA_MAGO);
    }

    private void closeWindowEvent(WindowEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.getButtonTypes().remove(ButtonType.OK);
        alert.getButtonTypes().add(ButtonType.CANCEL);
        alert.getButtonTypes().add(ButtonType.YES);
        alert.setTitle("Quit application");
        alert.setContentText("Close without saving?");
        alert.initOwner(primaryStage.getOwner());
        Optional<ButtonType> res = alert.showAndWait();


        res.ifPresent(buttonType -> {
            if (buttonType == ButtonType.CANCEL) {
                event.consume();
            }
        });
    }

    public void exit(ActionEvent actionEvent) {
        primaryStage.close();
        Platform.exit();
        primaryStage.fireEvent(new WindowEvent(primaryStage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    public void setStage(Stage stage) {
        primaryStage = stage;
        primaryStage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::closeWindowEvent);
    }


    private void loadEnviranment(File selectedFile) {
        demiurge.loadEnvironment(new DungeonLoader() {
            @Override
            public void load(Demiurge demiurge, DungeonConfiguration dungeonConfiguration) {
                //metodo load xml
            }
        });
    }

    public void guardar(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Guardar");
        alert.setHeaderText("Guardar");
        alert.setContentText("El Juego se ha guardado");
        alert.showAndWait();

    }

    public void salir(ActionEvent actionEvent) {
        Platform.runLater(() -> {
            primaryStage.close();
            Platform.exit();
        });
    }

    public void pausar(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pausa");
        alert.setHeaderText("Pausa");
        alert.setContentText("El Juego se ha pausado");
        alert.showAndWait();

    }

    public void menuPrincipal(ActionEvent actionEvent) {
        cargarPantalla(Screens.PANTALLA_CARGA);
    }

}