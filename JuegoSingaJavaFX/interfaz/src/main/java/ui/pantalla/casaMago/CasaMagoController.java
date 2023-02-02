package ui.pantalla.casaMago;

import game.character.exceptions.WizardNotEnoughEnergyException;
import game.character.exceptions.WizardSpellKnownException;
import game.character.exceptions.WizardTiredException;
import game.demiurge.Demiurge;
import game.dungeon.HomeNotEnoughSingaException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    private void abrirAlmacen(ActionEvent actionEvent) {
        getPrincipalController().cargarPantalla(Screens.ALMACEN);
    }

    @FXML
    private  void mejorarAlmacen(ActionEvent actionEvent) throws WizardTiredException, WizardNotEnoughEnergyException, HomeNotEnoughSingaException {
        getPrincipalController().getDemiurge().getHomeManager().upgradeSingaMax();
    }

    @FXML
    private  void mejorarConfort(ActionEvent actionEvent) throws WizardTiredException, WizardNotEnoughEnergyException, HomeNotEnoughSingaException {
        getPrincipalController().getDemiurge().getHomeManager().upgradeComfort();
    }

    @FXML
    private  void aprenderHechizo(ActionEvent actionEvent) throws WizardTiredException, WizardSpellKnownException, WizardNotEnoughEnergyException, HomeNotEnoughSingaException {
        int index = listaHechizos.getSelectionModel().getSelectedIndex();
        getPrincipalController().getDemiurge().getHomeManager().learnSpell(index);
        System.out.println("dormio");
    }

    @FXML
    private  void zzz() {
        getPrincipalController().getDemiurge().nextDay();
        System.out.println("dormio");
        principalCargado();
    }
}


