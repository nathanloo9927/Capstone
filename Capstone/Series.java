import java.util.*;
import java.awt.*;
import javax.swing.*;
public class Series extends BlackJack
{
    private int score;
    private int[] scoreKeep;
    public Series(int numPlayers, int users, String[] names, boolean[] real, int scoreLimit)
    {
        super(numPlayers, users, names, real);
        this.score = scoreLimit;
        this.scoreKeep = new int[numPlayers];
        for (int i = 0; i < numPlayers; i++)
        {
            scoreKeep[i] = 0;
        }
    }
    public String getScores()
    {
        String printer = "Name\tScore";
        for (int i = 0; i < scoreKeep.length; i++)
        {
            printer += ("\n" + super.getName(i) + "\t" + scoreKeep[i]);
        }
        return printer;
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
                scoreKeep[highestIndex]++;
                return super.getName(highestIndex) + " wins the round with " + highest;
            } else
            {
                String people = "We have a tie with " + highest + "\n";
                for (Integer i : indexOfTies)
                {
                    scoreKeep[i]++;
                    people += (super.getName(i) + "\n");
                }
                return people;
            }
        }
        return "No Winner. Everyone busted";
    }
    public boolean thereWinner()
    {
        for (int i = 0; i < scoreKeep.length; i++)
        {
            if (scoreKeep[i] >= score)
            {
                return true;
            }
        }
        return false;
    }
    public String declareWinner()
    {
        int index = -1;
        ArrayList<Integer> indexOfTies = new ArrayList<Integer>();
        for (int i = 0; i < scoreKeep.length; i++)
        {
            if (scoreKeep[i] >= score)
            {
                index = i;
                indexOfTies.add(i);
            }
        }
        if (index >= 0)
        {
            if (indexOfTies.size() == 1)
            {
                return super.getName(index) + "wins the game";
            }
        }
        return "Something's wrong.";
    }
}
