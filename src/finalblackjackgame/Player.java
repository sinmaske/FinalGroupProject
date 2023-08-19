package finalblackjackgame;

public class Player {
    private int funds;
    private Hand hand;

    public Player(int initialFunds) {
        this.funds = initialFunds;
        this.hand = new Hand();
    }

    public void placeBet(int amount) {
        if (amount > funds) {
            throw new IllegalArgumentException("Cannot bet more than available funds.");
        }
        funds -= amount;
    }

    public void receiveWinnings(int amount) {
        funds += amount;
    }

    public Hand getHand() {
        return hand;
    }

    public int getFunds() {
        return funds;
    }

    @Override
    public String toString() {
        return "Player with funds: $" + funds + " and hand: " + hand;
    }
}
