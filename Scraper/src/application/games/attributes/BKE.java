package application.games.attributes;



public class BKE {

    public char isWonBKE(Board board) {//player = player who did last set
        // vertical row check
        char player;
        for(int i = 0; i<3; i++) {
            int row = 0;
            player = board.getSpaceValue(board.getSpace(i, 0));
            for(int j = 0; j<3; j++) {
                if(board.getSpaceValue(board.getSpace(i, j)) == player) {
                    row++;
                }                
            }

            if(row == 3) {
                return player;
            }
        }
        //horizontal
        for(int i = 0; i<3; i++) {
            int row = 0;
            player = board.getSpaceValue(board.getSpace(0, i));
            for(int j = 0; j<3; j++) {
                if(board.getSpaceValue(board.getSpace(j, i)) == player) {
                    row++;
                }
                
            }

            if(row == 3) {
                return player;
            }
        }

        // diagonal top left to bottom right
        player = board.getSpaceValue(board.getSpace(0, 0));
        if(board.getSpaceValue(board.getSpace(0, 0)) ==  board.getSpaceValue(board.getSpace(1, 1)) && board.getSpaceValue(board.getSpace(1, 1)) == board.getSpaceValue(board.getSpace(2, 2))){
            return player;
        }
        player = board.getSpaceValue(board.getSpace(2, 0));
        if(board.getSpaceValue(board.getSpace(2, 0)) == board.getSpaceValue(board.getSpace(1, 1)) && board.getSpaceValue(board.getSpace(1, 1)) ==board.getSpaceValue(board.getSpace(0, 2))){
            return player;
        }

        return '.';
    }
}
