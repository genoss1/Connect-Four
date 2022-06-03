package connectfour;

import connectfour.logic.Player;

public class Storage {
    private final Player winner;
    private final Player looser;

    public Storage(Player winner, Player looser) {
        this.winner = winner;
        this.looser = looser;
    }

    public Player getWinner() {
        return winner;
    }

    public Player getLooser() {
        return looser;
    }
}
