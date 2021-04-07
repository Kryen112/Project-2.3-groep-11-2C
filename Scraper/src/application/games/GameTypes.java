package application.games;

import application.games.attributes.Board;

public interface GameTypes {
    Game execute(Board b);
    String title();
}
