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
            //int gameChoice;
            int players;
            int users;
            /*System.out.println("Choose game mode\n1-Quick game (1 game)\n2-Series game (set number of games)\n3-Score game (reach a certain score)");
            do {
                System.out.print("Your choice: ");
                while (!in.hasNextInt()) {
                    System.out.println("That's not an integer!");
                    System.out.print("Your choice: ");
                    in.next();
                }
                gameChoice = in.nextInt();
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
            boolean[] realPlayer = new boolean[players];
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
                realPlayer[i] = true;
            }
            int comNamer = 1;
            for (int i = users; i < players; i++)
            {
                names[i] = "CPU" + comNamer;
                comNamer++;
                realPlayer[i] = false;
            }
            boolean firstTime = true;
            response = 1;
            while (response == 1)
            {
                BlackJack game = new BlackJack(players, users, names, realPlayer);
                if (firstTime == true)
                {
                    System.out.println("\nThese are the people who will be playing:");
                    for (int i = 0; i < players; i++)
                    {
                        System.out.println(game.getName(i));
                    }
                    firstTime = false;
                }
                GameSim.simulateGame(game);
                response = BlackJackRunner.postgameChoice();
                System.out.println();
            }
        }
    }
    public static int postgameChoice()
    {
        int response;
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
        return response;
    }
}