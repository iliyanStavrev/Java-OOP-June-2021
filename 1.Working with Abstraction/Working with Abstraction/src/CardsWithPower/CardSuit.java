package CardsWithPower;

public enum CardSuit {
    CLUBS(0),
    DIAMONDS(13),
    HEARTS(26),
    SPADES(39);

    int value;

    CardSuit(int power){
        this.value = power;
    }

    public int getValue() {
        return value;
    }
}
