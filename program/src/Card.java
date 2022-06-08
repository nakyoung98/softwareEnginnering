public class Card {

    private CardInfo cardInfo;
    public Card(String cardType){
        cardInfo = new CardInfo(cardType);
    }

    public CardInfo getCardInfo() {
        return cardInfo;
    }
}

class CardInfo{
    private int score;
    private String cardType;

    public CardInfo(String cardType){
        this.cardType = cardType;
    }

    public String getCardType() {
        return cardType;
    }
}

class HammerCard extends Card{
    public HammerCard(String cardType){
        super(cardType);
    }
}

class SawCard extends Card{
    public SawCard(String cardType){
        super(cardType);
    }
}

class PhilipsDriverCard extends Card{
    public PhilipsDriverCard(String cardType){
        super(cardType);
    }
}

class BridgeCard extends Card{
    public BridgeCard(String cardType){
        super(cardType);
    }
}