import javax.swing.*;

public class GameFrame extends JFrame {
    private Game game;
    public GameFrame(){

    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
}
