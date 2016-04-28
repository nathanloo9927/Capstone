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
}
