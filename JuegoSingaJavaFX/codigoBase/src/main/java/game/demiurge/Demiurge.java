package game.demiurge;

import game.DungeonLoader;
import game.character.Wizard;
import game.conditions.Condition;
import game.conditions.SimpleCondition;
import game.dungeon.Dungeon;
import game.dungeon.Home;
import game.objectContainer.CrystalCarrier;
import game.objectContainer.JewelryBag;
import game.objectContainer.Wearables;

public class Demiurge {
    private int day = 0;
    private Dungeon dungeon;
    private Home home;


//    quitar esto
//    Wearables wearables = new Wearables(1,2,3);
//    CrystalCarrier carrier = new CrystalCarrier(1);
//    JewelryBag bag = new JewelryBag(1);
//    private Wizard wizard = new Wizard("Pepe", 10, 100, 9999, 10000, wearables, carrier, bag);
    private Wizard wizard;

    DungeonConfiguration dungeonConfiguration = new DungeonConfiguration();


//    y esto
//    DemiurgeHomeManager homeManager = new DemiurgeHomeManager(dungeonConfiguration, wizard, home, containerManager);
    DemiurgeHomeManager homeManager;

    DemiurgeContainerManager containerManager;


    DemiurgeDungeonManager dungeonManager;
    DemiurgeEndChecker endChecker;

    public Demiurge() {
        endChecker = new DemiurgeEndChecker();
        endChecker.addCondition(new SimpleCondition());
    }

    public void setDungeon(Dungeon dungeon) { this.dungeon = dungeon; }
    public void setHome(Home home) {
        this.home = home;
    }
    public void setWizard(Wizard wizard) {
        this.wizard = wizard;
    }
    public void addCondition(Condition condition){ endChecker.addCondition(condition); }


    public DemiurgeHomeManager getHomeManager() { return homeManager; }
    public DemiurgeContainerManager getContainerManager() { return containerManager; }
    public DemiurgeDungeonManager getDungeonManager() { return dungeonManager; }

    //TODO: INICIO CAMBIO
    public Home getHome(){return home;}
    public Wizard getWizard(){return wizard;}
    public Dungeon getDungeon(){return dungeon;}
    public DemiurgeEndChecker getEndChecker() {
        return endChecker;
    }
    //TODO: FIN CAMBIO

    public void loadEnvironment(DungeonLoader dungeonLoader) {
        dungeonLoader.load(this, dungeonConfiguration);
        containerManager = new DemiurgeContainerManager(wizard.getWearables(), wizard.getJewelryBag(), home.getContainer());
        homeManager =  new DemiurgeHomeManager(dungeonConfiguration, wizard, home, containerManager);
        dungeonManager = new DemiurgeDungeonManager(dungeonConfiguration, wizard, home, containerManager, endChecker);
        nextDay();
    }

    public int getDay() {
        return day;
    }

    public void nextDay() {
        wizard.sleep(home.getComfort() * dungeonConfiguration.getComfortModifierForEnergy());
        dungeon.generateCrystals(dungeonConfiguration.getCrystalsPerDay(), dungeonConfiguration.getSingaPerCrystal());
        day++;
    }
}
