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
            System.out.println("Choose game mode\n1-Quick game (1 game)\n2-Match game (play until the score limit is reached)\n3-Tournament (play a certain number of games)");
            do {
                System.out.print("Your choice: ");
                while (!in.hasNextInt()) {
                    System.out.println("That's not an integer!");
                    System.out.print("Your choice: ");
                    in.next();
                }
                gameChoice = in.nextInt();
            } while (gameChoice < 1 || gameChoice > 3);
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
            System.out.println("Enter the name(s) for the players who are actual users (cannot be");
            System.out.println("blank and cannot have spaces. If there are spaces, only the first");
            System.out.println("word will be added to the list of names of players)");
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
                if (gameChoice == 1)
                {
                    BlackJack game = new BlackJack(players, users, names, realPlayer);
                    System.out.print('\u000C');
                    if (firstTime == true)
                    {
                        System.out.println("Players: " + players);
                        System.out.println("Names:");
                        for (int i = 0; i < players; i++)
                        {
                            System.out.println(game.getName(i));
                        }
                        firstTime = false;
                    }
                    GameSim.sim1(game);
                } else if (gameChoice == 2)
                {
                    int score;
                    do {
                        System.out.print("Enter the score limit (2-10): ");
                        while (!in.hasNextInt()) {
                            System.out.println("That's not an integer!");
                            System.out.println("Enter the score limit (2-10): ");
                            in.next();
                        }
                        score = in.nextInt();
                    } while (score < 2 || score > 10);
                    Match g = new Match(players, users, names, realPlayer, score);
                    System.out.print('\u000C');
                    if (firstTime == true)
                    {
                        System.out.println("Players: " + players);
                        System.out.println("Score Limit: " + score);
                        System.out.println("Names:");
                        for (int i = 0; i < players; i++)
                        {
                            System.out.println(g.getName(i));
                        }
                        firstTime = false;
                    }
                    while (g.thereWinner() == false)
                    {
                        GameSim.sim2(g);
                    }
                    System.out.println(g.declareWinner());
                } else if (gameChoice == 3)
                {
                    int games;
                    do {
                        System.out.print("Enter the number of games (3-20): ");
                        while (!in.hasNextInt()) {
                            System.out.println("That's not an integer!");
                            System.out.println("Enter the score limit (2-10): ");
                            in.next();
                        }
                        games = in.nextInt();
                    } while (games < 3 || games > 20);
                    Tournament gm = new Tournament(players, users, names, realPlayer, games);
                    System.out.print('\u000C');
                    if (firstTime == true)
                    {
                        System.out.println("Players: " + players);
                        System.out.println("Games: " + games);
                        System.out.println("Names:");
                        for (int i = 0; i < players; i++)
                        {
                            System.out.println(gm.getName(i));
                        }
                        firstTime = false;
                    }
                    while (gm.gamesReached() == false)
                    {
                        GameSim.sim3(gm);
                    }
                    System.out.println(gm.declareWinner());
                } else
                {
                    System.out.println("Something's wrong");
                }
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