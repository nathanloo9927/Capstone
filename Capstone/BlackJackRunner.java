import java.util.*;
import java.awt.*;
import javax.swing.*;
public class BlackJackRunner
{
    public static void main(String[] args)
    {
        int response = 2;
        System.out.println("Welcome to BlackJack");
        while (response >= 1 && response <= 2)
        {
            Scanner in = new Scanner(System.in);
            int gameChoice;
            int players;
            int users;
            /*do {
                System.out.println("Choose game mode\n1-Quick game (1 game)\n2-Series game (set number of games)\n3-Score game (reach a certain score)");
                
            } while (gameChoice < 1 || gameChoice > 3);*/
            do {
                System.out.print("How many players are playing? (2-10 players): ");
                while (!in.hasNextInt()) {
                    System.out.println("That's not an integer!");
                    System.out.print("How many players are playing? (2-10 players): ");
                    in.next();
                }
                players = in.nextInt();
            } while (players < 2 || players > 10);
            String[] names = new String[players];
            do {
                System.out.println("How many of those players will be controlled by a player (At least 1");
                System.out.print("player must be controlled by the user): ");
                while (!in.hasNextInt()) {
                    System.out.println("That's not an integer!");
                    System.out.println("How many of those players will be controlled by a player (At least 1");
                    System.out.print("player must be controlled by the user): ");
                    in.next();
                }
                users = in.nextInt();
            } while (users < 1 || users > players);
            System.out.println("Enter the name(s) for the players who are actual users (cannot be blank and cannot have spaces.");
            System.out.println("If there are spaces, only the first word will be added to the list of names of players)");
            for (int i = 0; i < users; i++)
            {
                System.out.print("Player " + (i + 1) + ": ");
                names[i] = in.next();
            }
            int comNamer = 1;
            for (int i = users; i < players; i++)
            {
                names[i] = "CPU" + comNamer;
                comNamer++;
            }
            boolean firstTime = true;
            response = 1;
            while (response == 1)
            {
                BlackJack game = new BlackJack(players, users, names);
                if (firstTime == true)
                {
                    System.out.println("\nThese are the people who will be playing:");
                    for (int i = 0; i < players; i++)
                    {
                        System.out.println(game.getName(i));
                    }
                    firstTime = false;
                }
                System.out.println("Let's deal the cards and start the game!");
                System.out.print("\"ENTER\" = begin");
                Scanner sys = new Scanner(System.in);
                sys.nextLine();
                game.deal();
                for (int i = 0; i < users; i++)
                {
                    System.out.print(game.getName(i) + "'s turn (Press \"ENTER\" if this is you)");
                    Scanner out = new Scanner(System.in);
                    out.nextLine();
                    while ((game.isBusted(i) == false) && (game.isPlayerDone(i) == false) && (game.has21(i) == false))
                    {
                        System.out.println("Your Hand: " + game.getRealHand(i));
                        System.out.print("Would you like to hit or stay? (hit/any other response = stay): ");
                        String decision = in.next();
                        if (decision.equals("hit"))
                        {
                            game.hitandprint(i);
                        } else
                        {
                            game.stay(i);
                        }
                    }
                    if (game.isBusted(i) == true)
                    {
                        System.out.println("Your Hand: " + game.getRealHand(i));
                        System.out.println("Oops, you busted");
                    }
                    if (game.has21(i) == true)
                    {
                        System.out.println("Your Hand: " + game.getRealHand(i));
                        System.out.println("You have 21!");
                    }
                }
                for (int i = users; i < players; i++)
                {
                    System.out.println(game.getName(i) + "'s turn");
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                    System.out.println(game.getName(i) + "'s hand");
                    System.out.println(game.getHand(i));
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                    while ((game.isBusted(i) == false) && (game.isPlayerDone(i) == false) && (game.has21(i) == false))
                    {
                        game.computerMoves(i);
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException ex) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    if (game.isBusted(i) == true)
                    {
                        System.out.println("Your Hand: " + game.getRealHand(i));
                        System.out.println("Oops, you busted");
                    }
                    if (game.has21(i) == true)
                    {
                        System.out.println("Your Hand: " + game.getRealHand(i));
                        System.out.println("You have 21!");
                    }
                }
                System.out.println(game.declareWinner());
                System.out.println("\n1-Play again\n2-Change settings\n3-Quit");
                Scanner choice = new Scanner(System.in);
                do {
                    System.out.print("Your choice: ");
                    while (!choice.hasNextInt()) {
                        System.out.println("That's not an integer!");
                        System.out.print("Your choice: ");
                        choice.next();
                    }
                    response = choice.nextInt();
                } while (response < 1 || response > 3);
                System.out.println();
            }
        }
    }
}