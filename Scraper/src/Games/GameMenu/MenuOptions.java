package games.gamemenu;

import games.gamemenu.Menu;

public interface MenuOptions extends Menu {
    void execute();
    String title();
}
