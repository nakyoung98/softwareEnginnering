import java.util.ArrayList;

public class Player {
    private String name;
    private Coordinate location;
    private ArrayList<Card> cards;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLocation(Coordinate location){
        this.location = location;
    }

    public Coordinate getLocation() {
        return location;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
}
