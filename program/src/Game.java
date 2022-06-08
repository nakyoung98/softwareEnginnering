public class Game {
    private GameInfo gameInfo;
    public Game(Map map, int numPlayer) {
        gameInfo = new GameInfo(map, numPlayer);
    }

    public GameInfo getGameInfo() {
        return gameInfo;
    }
}

class GameInfo {
    private Map map;
    private int numPlayer;

    public GameInfo(Map map, int numPlayer) {
        this.map = map;
        this.numPlayer = numPlayer;
    }

    public Map getMap() {
        return map;
    }

}
