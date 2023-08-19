package finalblackjackgame;

public class Dealer {
    private Hand hand;

    public Dealer() {
        this.hand = new Hand();
    }

    public Hand getHand() {
        return hand;
    }

    @Override
    public String toString() {
        return "Dealer with hand: " + hand;
    }
}
