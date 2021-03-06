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
            int games = 3;
            int score = 2;
            int money = 501;
            System.out.println("Choose game mode\n1-Quick game (1 game)\n2-Match game (play until the score limit is reached)");
            System.out.println("3-Tournament (play a certain number of games)");
            System.out.println("4-Betting (bet money and play until a person goes bankrupt)\n");
            do {
                System.out.print("Your choice: ");
                while (!in.hasNextInt()) {
                    System.out.println("That's not an integer!");
                    System.out.print("Your choice: ");
                    in.next();
                }
                gameChoice = in.nextInt();
            } while (gameChoice < 1 || gameChoice > 4);
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
                        System.out.println();
                    }
                    GameSim.sim1(game);
                } else if (gameChoice == 2)
                {
                    if (firstTime == true)
                    {
                        do {
                            System.out.print("Enter the score limit (2-10): ");
                            while (!in.hasNextInt()) {
                                System.out.println("That's not an integer!");
                                System.out.print("Enter the score limit (2-10): ");
                                in.next();
                            }
                            score = in.nextInt();
                        } while (score < 2 || score > 10);
                    }
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
                        System.out.println();
                    }
                    while (g.thereWinner() == false)
                    {
                        GameSim.sim2(g);
                    }
                    System.out.println(g.declareWinner());
                    System.out.println("Games Played: " + g.getGamesPlayed());
                } else if (gameChoice == 3)
                {
                    if (firstTime == true)
                    {
                        do {
                            System.out.print("Enter the number of games (3-20): ");
                            while (!in.hasNextInt()) {
                                System.out.println("That's not an integer!");
                                System.out.print("Enter the number of games (3-20): ");
                                in.next();
                            }
                            games = in.nextInt();
                        } while (games < 3 || games > 20);
                    }
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
                        System.out.println();
                    }
                    while (gm.gamesReached() == false)
                    {
                        GameSim.sim3(gm);
                    }
                    System.out.println(gm.declareWinner());
                } else if (gameChoice == 4)
                {
                    if (firstTime == true)
                    {
                        do {
                            System.out.print("Enter the money each player starts with in a multiple of 100 (500-5000): ");
                            while (!in.hasNextInt()) {
                                System.out.println("That's not an integer!");
                                System.out.print("Enter the money each player starts with in a multiple of 100 (500-5000): ");
                                in.next();
                            }
                            money = in.nextInt();
                        } while (money % 100 != 0 || (money < 500 || money > 5000));
                    }
                    Betting ga = new Betting(players, users, names, realPlayer, money);
                    System.out.print('\u000C');
                    if (firstTime == true)
                    {
                        System.out.println("Players: " + players);
                        System.out.println("Starting money: " + money + "zm");
                        System.out.println("Names:");
                        for (int i = 0; i < players; i++)
                        {
                            System.out.println(ga.getName(i));
                        }
                        firstTime = false;
                        System.out.println();
                    }
                    while (ga.bankrupt() == false)
                    {
                        for (int i = 0; i < users; i++)
                        {
                            int bet;
                            System.out.println(ga.getName(i) + " has " + ga.amount(i) + "zm");
                            do {
                                System.out.print(ga.getName(i) + ", make a valid bet: ");
                                while (!in.hasNextInt()) {
                                    System.out.println("That's not an integer!");
                                    System.out.print(ga.getName(i) + ", make a valid bet: ");
                                    in.next();
                                }
                                bet = in.nextInt();
                            } while (ga.isValid(i, bet) == false);
                            ga.add(i, bet);
                            System.out.println();
                        }
                        for (int i = users; i < players; i++)
                        {
                            System.out.println(ga.getName(i) + " has " + ga.amount(i) + "zm");
                            if (ga.amount(i) < 1000)
                            {
                                System.out.println(ga.getName(i) + ": I bet 100zm");
                                ga.add(i, 100);
                            } else if (ga.amount(i) < 2000)
                            {
                                System.out.println(ga.getName(i) + ": I bet 200zm");
                                ga.add(i, 200);
                            } else if (ga.amount(i) < 3000)
                            {
                                System.out.println(ga.getName(i) + ": I bet 300zm");
                                ga.add(i, 300);
                            } else if (ga.amount(i) < 4000)
                            {
                                System.out.println(ga.getName(i) + ": I bet 400zm");
                                ga.add(i, 400);
                            } else
                            {
                                System.out.println(ga.getName(i) + ": I bet 500zm");
                                ga.add(i, 500);
                            }
                            try {
                                Thread.sleep(1500);
                            } catch (InterruptedException ex) {
                                Thread.currentThread().interrupt();
                            }
                            System.out.println();
                        }
                        GameSim.sim4(ga);
                    }
                    System.out.println(ga.declareWinner());
                    System.out.println("Games Played: " + ga.getGamesPlayed());
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