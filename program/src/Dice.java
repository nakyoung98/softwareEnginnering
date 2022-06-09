import java.util.Random;

public class Dice {
    public final static int MAX = 6;

    public static int rollDice(){
        Random random = new Random();
        int result = random.nextInt(MAX) + 1;
        return result;
    }
}
