package games;

import games.attributes.Board;

public interface GameTypes {
    Game execute(Board b);
    String title();
}
