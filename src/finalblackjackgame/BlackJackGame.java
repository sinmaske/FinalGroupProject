package finalblackjackgame;

import java.util.Scanner;

public class BlackJackGame {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        Player player = new Player(1000);  // Initial funds set to 1000
        Dealer dealer = new Dealer();

        while (true) {
            Deck deck = new Deck();
            deck.shuffle();

            // Reset hands for a new round
            player.getHand().cards.clear();
            dealer.getHand().cards.clear();

            // Betting system
            System.out.println("You have funds: $" + player.getFunds());
            System.out.print("Place your bet: ");
            int bet = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            
            if (bet > player.getFunds()) {
                System.out.println("You cannot bet more than you have. Try again.");
                continue;
            }
            player.placeBet(bet);

            // Dealing initial cards
            player.getHand().addCard(deck.drawCard());
            dealer.getHand().addCard(deck.drawCard());
            player.getHand().addCard(deck.drawCard());
            dealer.getHand().addCard(deck.drawCard());

            System.out.println("\nPlayer's Hand:");
            player.getHand().display();
            System.out.println("Player's Hand Value: " + player.getHand().getValue());

            System.out.println("\nDealer's Hand:");
            System.out.println(dealer.getHand().cards.get(0));
            System.out.println("[Hidden Card]");

            while (true) {
                System.out.print("\nDo you want to hit or stand? (h/s): ");
                String choice = scanner.nextLine();

                if (choice.equalsIgnoreCase("h")) {
                    player.getHand().addCard(deck.drawCard());
                    System.out.println("Player's Hand:");
                    player.getHand().display();
                    System.out.println("Player's Hand Value: " + player.getHand().getValue());

                    if (player.getHand().getValue() > 21) {
                        System.out.println("Player busts! You lose $" + bet + ".");
                        break;
                    }
                } else if (choice.equalsIgnoreCase("s")) {
                    System.out.println("Dealer reveals the hidden card.");
                    dealer.getHand().display();

                    while (dealer.getHand().getValue() < 17) {
                        dealer.getHand().addCard(deck.drawCard());
                        System.out.println("Dealer draws a card.");
                        dealer.getHand().display();
                    }

                    System.out.println("Dealer's Hand Value: " + dealer.getHand().getValue());

                    if (dealer.getHand().getValue() > 21 || dealer.getHand().getValue() < player.getHand().getValue()) {
                        System.out.println("You win! You gain $" + bet + ".");
                        player.receiveWinnings(2 * bet);
                    } else if (dealer.getHand().getValue() > player.getHand().getValue()) {
                        System.out.println("Dealer wins! You lose $" + bet + ".");
                    } else {
                        System.out.println("It's a tie! You get back your bet.");
                        player.receiveWinnings(bet);
                    }

                    break;
                }
            }

            System.out.print("\nDo you want to play another round? (y/n): ");
            if (!scanner.nextLine().equalsIgnoreCase("y")) {
                break;
            }
        }
        
        System.out.println("Thanks for playing! You leave with $" + player.getFunds() + ".");
        scanner.close();
    }
}


