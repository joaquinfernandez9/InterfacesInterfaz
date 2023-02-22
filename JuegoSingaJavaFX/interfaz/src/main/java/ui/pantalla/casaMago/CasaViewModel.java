package ui.pantalla.casaMago;

import game.character.exceptions.WizardNotEnoughEnergyException;
import game.character.exceptions.WizardTiredException;
import game.demiurge.Demiurge;
import game.demiurge.DemiurgeHomeManager;
import game.dungeon.HomeNotEnoughSingaException;
import game.util.ValueOverMaxException;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class CasaViewModel {
    //Mejorar almacen singa -> upgradeSingaMax
    //mejorar confort -> upgradeComfort
    //Ir almacen -> cambiar pantalla
    //Dormir -> ?
    //aprender hechizos -> learnSpell


    private final Demiurge demiurge;
    private final ObjectProperty<CasaState> state;
    DemiurgeHomeManager demiurgeHomeManager = new DemiurgeHomeManager();

    @Inject
    public CasaViewModel(Demiurge demiurge) {
        this.demiurge = demiurge;
        this.state = new SimpleObjectProperty<>(
                new CasaState(null, demiurgeHomeManager.homeInfo(), /*demiurgeHomeManager.homeInfo(),*/ false));
    }

    public void reloadState() {
        state.setValue(new CasaState(null, demiurgeHomeManager.homeInfo(), !state.get().isCambio()));
    }


    public void upgradeSingaMax() throws WizardTiredException, WizardNotEnoughEnergyException, HomeNotEnoughSingaException {
        demiurgeHomeManager.upgradeSingaMax();
        reloadState();
    }

    public void upgradeComfort() throws WizardTiredException, WizardNotEnoughEnergyException, HomeNotEnoughSingaException {
        demiurgeHomeManager.upgradeComfort();
        reloadState();
    }

    public void sleep() {
        demiurge.nextDay();
        reloadState();
    }

    //el getLibrary tiene que aparecer en la pantalla para que seleccione el hechizo a aprender
//    public void learnSpell() throws WizardTiredException, WizardNotEnoughEnergyException {
//        demiurgeHomeManager.learnSpell();
//        reloadState();
//    }

    //get spells


    public ObjectProperty<CasaState> getState() {
        return state;
    }


}
