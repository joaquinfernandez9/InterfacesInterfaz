package ui.pantalla.mazmorra;

import game.dungeon.Room;
import game.dungeon.Site;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MazmorraState {
    private LocalDateTime time;
    private final Character character;
    private final Room room;
    private final Site site;
}
