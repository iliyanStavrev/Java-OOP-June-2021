package CardsWithPower;

public class Card {
    private CardRank rank;
    private CardSuit suit;
    private int cardPower;

    public Card(CardRank rank, CardSuit suit) {
        this.rank = rank;
        this.suit = suit;
        calculatePower();
    }

    public void   calculatePower(){
      cardPower = rank.getValue() + suit.getValue();
  }

    @Override
    public String toString() {
        return String.format("Card name: %s of %s; Card power: %d",rank,suit.name(),cardPower);
    }
}
