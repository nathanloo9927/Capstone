import java.util.*;
import java.awt.*;
import javax.swing.*;
public class GameSim
{
    public static void simulateGame(BlackJack game)
    {
        System.out.println("Let's deal the cards and start the game!");
        System.out.print("\"ENTER\" = begin");
        Scanner sys = new Scanner(System.in);
        sys.nextLine();
        game.deal();
        String[] players = game.getPlayers();
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < game.getUsers(); i++)
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
        for (int i = game.getUsers(); i < game.getNumPlayers(); i++)
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
    }
}