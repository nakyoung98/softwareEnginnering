import java.util.ArrayList;

public class Cell {
    private Card card;
    private ArrayList<String> directionList;

    public Cell(){
        directionList = new ArrayList<>();
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Card getCard() {
        return card;
    }

    public void setDirectionList(ArrayList<String> direction) {
        this.directionList = direction;
    }
    public void addDirection(String direction){
        directionList.add(direction);
    }

    public ArrayList<String> getDirectionList() {
        return directionList;
    }
}
