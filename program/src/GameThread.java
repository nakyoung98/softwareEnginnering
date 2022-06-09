public class GameThread extends Thread{
    private Game game;

    public GameThread(Game game){
        this.game = game;
    }

    @Override
    public void run() {
        super.run();
    }
}
