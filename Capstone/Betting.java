import java.util.*;
import java.awt.*;
import javax.swing.*;
public class Betting extends BlackJack
{
    private int gamesPlayed;
    private int pot;
    private int[] money;
    public Betting(int numPlayers, int users, String[] names, boolean[] real, int starting)
    {
        super(numPlayers, users, names, real);
        this.gamesPlayed = 0;
        this.pot = 0;
        this.money = new int[numPlayers];
        for (int i = 0; i < numPlayers; i++)
        {
            money[i] = starting;
        }
    }
    public String getScores()
    {
        String printer = "Name\tMoney";
        for (int i = 0; i < money.length; i++)
        {
            printer += ("\n" + super.getName(i) + "\t" + money[i]);
        }
        return printer;
    }
    public void incrementGames()
    {
        gamesPlayed++;
    }
    public int getGamesPlayed()
    {
        return gamesPlayed;
    }
    public int getPot()
    {
        return pot;
    }
    public void add(int bet)
    {
        pot += bet;
    }
    public String roundWinner()
    {
        int highest = 0;
        int highestIndex = -1;
        ArrayList<Integer> indexOfTies = new ArrayList<Integer>();
        for (int i = 0; i < super.getNumPlayers(); i++)
        {
            int value = super.getHandValue(i);
            if (value > highest && value <= 21)
            {
                highest = value;
                highestIndex = i;
                indexOfTies.clear();
                indexOfTies.add(i);
            } else if (value == highest && value <= 21)
            {
                indexOfTies.add(i);
            }
        }
        for (int i = 0; i < super.getNumPlayers(); i++)
        {
            System.out.println(super.getName(i) + "'s hand: " + super.getRealHand(i));
        }
        if (highestIndex >= 0)
        {
            if (indexOfTies.size() == 1)
            {
                money[highestIndex]++;
                return super.getName(highestIndex) + " wins the round with " + highest;
            } else
            {
                String people = "We have a tie with " + highest;
                int ties = 0;
                for (Integer i : indexOfTies)
                {
                    ties++;
                }
                int split = pot / ties;
                for (Integer i : indexOfTies)
                {
                    people += ("\n" + super.getName(i));
                    money[i] += split;
                }
                return people;
            }
        }
        return "No Winner. Everyone busted";
    }
    public boolean bankrupt()
    {
        for (int i = 0; i < money.length; i++)
        {
            if (money[i] <= 0)
            {
                return true;
            }
        }
        return false;
    }
    public String declareWinner()
    {
        int index = -1;
        int highest = 0;
        ArrayList<Integer> indexOfTies = new ArrayList<Integer>();
        for (int i = 0; i < money.length; i++)
        {
            if (money[i] > highest)
            {
                highest = money[i];
                index = i;
                indexOfTies.clear();
                indexOfTies.add(i);
            } else if (money[i] == highest)
            {
                indexOfTies.add(i);
            }
        }
        if (index >= 0)
        {
            if (indexOfTies.size() == 1)
            {
                return super.getName(index) + " wins the game";
            } else
            {
                String people = "We have a tie\n";
                for (Integer i : indexOfTies)
                {
                    people += (super.getName(i) + "\n");
                }
                return people;
            }
        }
        return "No Winner";
    }
}