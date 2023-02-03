package ui.pantalla.almacen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import ui.common.BaseScreenController;

import java.util.Iterator;

public class AlmacenController extends BaseScreenController {
    @FXML
    private ListView items_almacen;
    @FXML
    private ListView items_mago;

    @Override
    public void principalCargado() {
        super.principalCargado();

        Iterator it_almacen;
        Iterator it_mago;
        it_almacen = getPrincipalController().getDemiurge().getHome().getContainer().iterator();
        it_mago = getPrincipalController().getDemiurge().getWizard().getWearables().iterator();

        items_almacen.getItems().clear();
        while (it_almacen.hasNext()) {
            items_almacen.getItems().add(it_almacen.next().toString());
        }
        items_mago.getItems().clear();
        while (it_mago.hasNext()) {
            items_mago.getItems().add(it_mago.next().toString());
        }

    }

    @FXML
    private void moverAlmacen() {
    }

    @FXML
    private void soltar() {
    }

    @FXML
    private void moverInventario() {
    }
}
