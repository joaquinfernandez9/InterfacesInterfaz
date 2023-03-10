package loaderManualXML;

import game.demiurge.Demiurge;
import game.demiurge.DungeonConfiguration;
import game.object.ItemCreationErrorException;
import game.objectContainer.exceptions.ContainerFullException;
import game.objectContainer.exceptions.ContainerUnacceptedItemException;
import game.spell.SpellUnknowableException;
import game.util.ValueOverMaxException;

import java.io.File;
import java.io.IOException;

public interface DungeonLoaderXML {
    void load(Demiurge demiurge, DungeonConfiguration dungeonConfiguration, File xmlFile)
            throws Exception, SpellUnknowableException, ItemCreationErrorException, ContainerUnacceptedItemException, ContainerFullException, ValueOverMaxException;
    void save(Demiurge demiurge, DungeonConfiguration dungeonConfiguration, File file) throws IOException;
}
