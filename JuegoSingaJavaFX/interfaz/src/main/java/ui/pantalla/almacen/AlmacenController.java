package ui.pantalla.almacen;

import game.objectContainer.Container;
import game.objectContainer.exceptions.ContainerFullException;
import game.objectContainer.exceptions.ContainerUnacceptedItemException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import ui.common.BaseScreenController;

import java.util.Iterator;

public class AlmacenController extends BaseScreenController {
    @FXML
    private ListView<String> jew_bag;
    @FXML
    private ListView<String> items_almacen;
    @FXML
    private ListView<String> items_mago;

    @Override
    public void principalCargado() {
        super.principalCargado();

        Iterator it_almacen;
        Iterator it_mago;
        Iterator inv_mago;
        it_almacen = getPrincipalController().getDemiurge().getHome().getContainer().iterator();
        it_mago = getPrincipalController().getDemiurge().getWizard().getWearables().iterator();
        inv_mago = getPrincipalController().getDemiurge().getWizard().getJewelryBag().iterator();

        items_almacen.getItems().clear();
        while (it_almacen.hasNext()) {
            items_almacen.getItems().add(it_almacen.next().toString());
        }
        items_mago.getItems().clear();
        while (it_mago.hasNext()) {
            items_mago.getItems().add(it_mago.next().toString());
        }

        jew_bag.getItems().clear();
        while (inv_mago.hasNext()){
            jew_bag.getItems().add(inv_mago.next().toString());
        }


    }

    @FXML
    private void moverAlmacen() {
        // de inventario -> almacen
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Mover Almacen");
        try {
            if (items_mago.getSelectionModel().getSelectedItem() != null) {
                Container a = getPrincipalController().getDemiurge().getWizard().getWearables();
                Container b = getPrincipalController().getDemiurge().getHome().getContainer();
                getPrincipalController().getDemiurge().getContainerManager().addItem(a, items_mago.getSelectionModel().getSelectedIndex(), b);
            } else if (jew_bag.getSelectionModel().getSelectedItem() != null) {
                Container a = getPrincipalController().getDemiurge().getWizard().getJewelryBag();
                Container b = getPrincipalController().getDemiurge().getHome().getContainer();
                getPrincipalController().getDemiurge().getContainerManager().addItem(a, jew_bag.getSelectionModel().getSelectedIndex(), b);
            }else {
                alert.setHeaderText("No se ha seleccionado ningun item");
                alert.showAndWait();
            }
        } catch (ContainerUnacceptedItemException | ContainerFullException e) {
            if (e instanceof ContainerUnacceptedItemException) {
                alert.setHeaderText("El item no es valido para el almacen");
                alert.showAndWait();
            } else {
                alert.setHeaderText("El almacen esta lleno");
                alert.showAndWait();
            }
        }
        principalCargado();

    }

    @FXML
    private void soltar() {
        if (items_mago.getSelectionModel().getSelectedItem() != null) {
            Container a = getPrincipalController().getDemiurge().getWizard().getWearables();
            int index = items_mago.getSelectionModel().getSelectedIndex();
            getPrincipalController().getDemiurge().getContainerManager().deleteItem(a, index);
        } else if (items_almacen.getSelectionModel().getSelectedItem() != null) {
            Container a = getPrincipalController().getDemiurge().getHome().getContainer();
            int index = items_almacen.getSelectionModel().getSelectedIndex();
            getPrincipalController().getDemiurge().getContainerManager().deleteItem(a, index);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Mover Almacen");
            alert.setHeaderText("No se ha seleccionado ningun item");
            alert.showAndWait();
        }
        principalCargado();
    }

    @FXML
    private void moverInventario() {
        // de almacen -> inventario
        try {
            if (items_almacen.getSelectionModel().getSelectedItem() != null) {
                //a la jewelry bag
                Container a = getPrincipalController().getDemiurge().getHome().getContainer();
                Container b = getPrincipalController().getDemiurge().getWizard().getJewelryBag();
                getPrincipalController().getDemiurge().getContainerManager().addItem(a, items_almacen.getSelectionModel().getSelectedIndex(), b);
            } else {
                //no se ha seleccionado nada alert
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Mover Almacen");
                alert.setHeaderText("No se ha seleccionado ningun item");
                alert.showAndWait();
            }
        } catch (ContainerUnacceptedItemException | ContainerFullException e){
            if (e instanceof ContainerUnacceptedItemException) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Mover Almacen");
                alert.setHeaderText("El item no es valido para el inventario");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Mover Almacen");
                alert.setHeaderText("El inventario esta lleno");
                alert.showAndWait();
            }
        }
        principalCargado();
    }
}
