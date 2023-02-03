package ui.pantalla.casaMago;

import game.character.exceptions.WizardNotEnoughEnergyException;
import game.character.exceptions.WizardSpellKnownException;
import game.character.exceptions.WizardTiredException;
import game.demiurge.Demiurge;
import game.dungeon.HomeNotEnoughSingaException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import ui.common.BaseScreenController;
import ui.common.Screens;

import java.util.Iterator;

public class CasaMagoController extends BaseScreenController {


    @FXML
    private ListView<String> listaHechizos;
    @FXML
    private Label dia;
    @FXML
    private Label cristales;
    @FXML
    private Label vida;
    @FXML
    private Label energia;
    @FXML
    private Label singa;



    @Override
    public void principalCargado() {
        super.principalCargado();
        cristales.setText("Cristales "+getPrincipalController().getDemiurge().getWizard().getCrystalCarrier().size());
        vida.setText("Vida "+getPrincipalController().getDemiurge().getWizard().getLife());
        energia.setText("Energia "+getPrincipalController().getDemiurge().getWizard().getEnergy());
        singa.setText("Singa "+getPrincipalController().getDemiurge().getHomeManager().getSinga());
        dia.setText("Dia "+getPrincipalController().getDemiurge().getDay());

        listaHechizos.getItems().clear();
        Iterator it = getPrincipalController().getDemiurge().getHomeManager().getLibrary().iterator();
        while (it.hasNext()) {
            listaHechizos.getItems().add(it.next().toString());
        }

    }

    @FXML
    private void abrirAlmacen() {
        getPrincipalController().cargarPantalla(Screens.ALMACEN);
    }

    @FXML
    private  void mejorarAlmacen() {
        try {
            getPrincipalController().getDemiurge().getHomeManager().upgradeSingaMax();
            principalCargado();

        }catch (WizardTiredException| WizardNotEnoughEnergyException | HomeNotEnoughSingaException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Mejorar Almacen");
            alert.setHeaderText("No se puede mejorar el almacen");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }

    @FXML
    private  void mejorarConfort() {
        try {
            getPrincipalController().getDemiurge().getHomeManager().upgradeComfort();
            principalCargado();

        } catch (WizardTiredException | WizardNotEnoughEnergyException | HomeNotEnoughSingaException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Mejorar Confort");
            alert.setHeaderText("No se puede mejorar el confort");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private  void aprenderHechizo() {
        int index = listaHechizos.getSelectionModel().getSelectedIndex();

        try {
            if (index != -1) {
                getPrincipalController().getDemiurge().getHomeManager().learnSpell(index);
                System.out.println("dormio");
                principalCargado();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Aprender Hechizo");
                alert.setHeaderText("No se puede aprender el hechizo");
                alert.setContentText("No se ha seleccionado ningun hechizo");
                alert.showAndWait();
            }

        } catch (WizardTiredException | WizardSpellKnownException | WizardNotEnoughEnergyException | HomeNotEnoughSingaException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Aprender Hechizo");
            alert.setHeaderText("No se puede aprender el hechizo");
            if(e instanceof WizardSpellKnownException) {
                alert.setContentText("Ya sabes el hechizo");
            } else if (e instanceof WizardTiredException) {
                alert.setContentText("Estas muy cansado");
            } else if (e instanceof WizardNotEnoughEnergyException) {
                alert.setContentText(e.getMessage());
            } else if (e instanceof HomeNotEnoughSingaException) {
                alert.setContentText(e.getMessage());
            }
            alert.showAndWait();
        }
    }

    @FXML
    private  void zzz() {
        getPrincipalController().getDemiurge().nextDay();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Dormir");
        alert.setHeaderText("Estás durmiendo");
        alert.setContentText("Estás durmiendo");
        alert.showAndWait();
        principalCargado();
    }

    @FXML
    private void irMazmorra() {
        getPrincipalController().cargarPantalla(Screens.MAZMORRA);
    }
}


