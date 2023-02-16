package ui.pantalla.mazmorra;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
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
        int size = 75;

        Image mago = new Image(getClass().getResourceAsStream("/img/Mago.jpg"));
        Image enemigo = new Image(getClass().getResourceAsStream("/img/Enemigo.jpg"));
        Image puerta = new Image(getClass().getResourceAsStream("/img/Puerta.jpg"));
        Image cofre = new Image(getClass().getResourceAsStream("/img/Cofre.jpg"));
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
                if ((rowIndex >= 1 && rowIndex <= 3) && (columnIndex >= 1 && columnIndex <= 3))
                    imgView = new ImageView(suelo1);
                imgView.setFitWidth(size);
                imgView.setFitHeight(size);
                button.setGraphic(imgView);
                button.setPadding(Insets.EMPTY);
            }
        });

        //segun la room, pintar en los distintas posiciones las imagenes que toquen ademas de agregarle la accion al boton
        //para eso, le tiene que llegar una room a esta funcion y a la funcion del principal controller le tiene que llegar el Demiurge
    }
}

