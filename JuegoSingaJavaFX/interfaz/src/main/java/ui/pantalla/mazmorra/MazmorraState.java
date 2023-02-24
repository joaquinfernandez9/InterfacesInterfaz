package ui.pantalla.mazmorra;

import game.character.Creature;
import game.character.Wizard;
import game.dungeon.Room;
import lombok.Data;

@Data
public class MazmorraState {
    private Room room;
    private Wizard wizard;
    private Creature creature;

    public MazmorraState(Room room, Wizard wizard, Creature creature) {
        this.room = room;
        this.wizard = wizard;
        this.creature = creature;
    }
}
