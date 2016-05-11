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
            printer += ("\n" + super.getName(i) + "\t" + money[i] + "zm");
        }
        return printer;
    }
    public int amount(int currentPlayer)
    {
        return money[currentPlayer];
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
    public void emptyPot()
    {
        pot = 0;
    }
    public boolean isValid(int currentPlayer, int bet)
    {
        if (bet <= 0)
        {
            return false;
        }
        if (bet % 100 != 0)
        {
            return false;
        }
        if (bet > money[currentPlayer])
        {
            return false;
        }
        return true;
    }
    public void add(int currentPlayer, int bet)
    {
        money[currentPlayer] -= bet;
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
                money[highestIndex] += pot;
                return super.getName(highestIndex) + " wins the round with " + highest;
            } else
            {
                String people = "We have a tie with " + highest;
                people += "\nif the pot isn't a multiple of 100 after it is divided evenly, some or";
                people += "\nall of the money will go away";
                int ties = indexOfTies.size();
                int split = pot / ties;
                int multiple = split / 100;
                int average = multiple * 100;
                for (Integer i : indexOfTies)
                {
                    people += ("\n" + super.getName(i));
                    money[i] += average;
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
                return super.getName(index) + " wins the game with " + money[index] + "zm";
            } else
            {
                String people = "We have a tie with" + highest + "zm\n";
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