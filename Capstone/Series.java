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
    public int getScore(int currentPlayer)
    {
        return scoreKeep[currentPlayer];
    }
    /*public String roundWinner()
    {
        int highest = 0;
        int highestIndex = -1;
        ArrayList<Integer> indexOfTies = new ArrayList<Integer>();
        for (int i = 0; i < numplayers; i++)
        {
            if (handValue[i][1] > highest && handValue[i][1] <= 21)
            {
                highest = handValue[i][1];
                highestIndex = i;
                indexOfTies.clear();
                indexOfTies.add(i);
            } else if (handValue[i][1] == highest && handValue[i][1] <= 21)
            {
                indexOfTies.add(i);
            }
        }
        for (int i = 0; i < numplayers; i++)
        {
            System.out.println(players[i] + "'s hand: " + privateHand[i]);
        }
        if (highestIndex >= 0)
        {
            if (indexOfTies.size() == 1)
            {
                scoreKeep[highestIndex]++;
                return players[highestIndex] + " wins with " + handValue[highestIndex][1];
            } else
            {
                String people = "We have a tie with " + highest + "\n";
                for (Integer i : indexOfTies)
                {
                    scoreKeep[i]++;
                    people += (players[i] + "\n");
                }
                return people;
            }
        }
        return "No Winner. Everyone busted";
    }*/
}
