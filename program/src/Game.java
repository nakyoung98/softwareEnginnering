import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Game {
    private GameInfo gameInfo;

    public Game(Map map, int numPlayer) {
        gameInfo = new GameInfo(map, numPlayer);
    }

    public void ready(){
        MakeGame.setName(this.gameInfo.getPlayers()); //player 이름 정하기
        makeTurn();
        setPlayerStartLocation();
    }

    public void play(){
        currentPlayer().getLocation().right();
    }

    public Player currentPlayer(){
        return gameInfo.getPlayOrder().get(0);
    }

    private void makeTurn(){
        for(int i = 0; i<gameInfo.getNumPlayer(); i++){
            gameInfo.getPlayOrder().add(gameInfo.getPlayers().get(i));
        }
        int maxShuffle = new Random().nextInt(10);

        for(int i = 0;i<maxShuffle;i++) {
            Collections.shuffle(gameInfo.getPlayOrder());
        }

    }

    private void setPlayerStartLocation(){
        try {
            for (int i = 0; i < gameInfo.getNumPlayer(); i++) {
                gameInfo.getPlayers().get(i).setLocation(gameInfo.getMap().getStartCoordinate().clone());
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public GameInfo getGameInfo() {
        return gameInfo;
    }
}

class GameInfo {
    private Map map;
    private int numPlayer;
    private ArrayList<Player> players;
    private ArrayList<Player> playOrder;


    public GameInfo(Map map, int numPlayer) {
        this.map = map;
        this.numPlayer = numPlayer;

        playOrder = new ArrayList<>();
        players = MakeGame.makePlayers(numPlayer);
    }

    public Map getMap() {
        return map;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Player> getPlayOrder() {
//        for(int i = 0; i<playOrder.size();i++) {System.out.println(playOrder.get(i).getName());}
        return playOrder;
    }

    public int getNumPlayer() {
        return numPlayer;
    }
}
