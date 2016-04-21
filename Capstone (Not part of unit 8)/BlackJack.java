import java.util.*;
import java.awt.*;
import javax.swing.*;
public class BlackJack
{
    private int numplayers; // total number of players
    private int users; // number of players who are actually users
    private String[] players; // list of all the players, including the computers
    private int[][] handValue; // the second array is for alta. value if Ace is drawn
    private int[] numAces; // number of aces in each player's hand
    private String[] publicHand; // the list of cards each player has. Others can see
    private String[] privateHand; // the list of cards each player has. Only the player can see
    private ArrayList<Integer> cards = new ArrayList<Integer>(52); // Deck of cards
    private ArrayList<String> cardsuit = new ArrayList<String>(52); // Suit of cards
    private boolean[] isDone; // checks if the player is finished with his/her turn
    public BlackJack(int numPlayers, int users, String[] names)
    {
        this.numplayers = numPlayers;
        this.users = users;
        this.players = names;
        this.numAces = new int[numPlayers];
        for (int i = 0; i < numPlayers; i++)
        {
            numAces[i] = 0;
            isDone[i] = false;
        }
        for (int i = 1; i <= 4; i++)
        {
            for (int j = 1; j <= 13; j++)
            {
                cards.add(j);
                if (i == 1)
                {
                    cardsuit.add("Spade");
                } else if (i == 2)
                {
                    cardsuit.add("Club");
                } else if (i == 3)
                {
                    cardsuit.add("Diamond");
                } else if (i == 4)
                {
                    cardsuit.add("Heart");
                }
            }
        }
        this.handValue = new int[numPlayers][2];
        this.publicHand = new String[numPlayers];
        this.privateHand = new String[numPlayers];
    }
    public String getName(int pos)
    {
        return players[pos];
    }
    public int getDeckSize()
    {
        return cards.size();
    }
    public String getHand(int currentPlayer) // the cards
    {
        return publicHand[currentPlayer];
    }
    public String getRealHand(int currentPlayer)
    {
        return privateHand[currentPlayer];
    }
    public void getHandValue(int currentPlayer) // how much the cards in the player's hand would
                                                // total.
    {
        if (handValue[currentPlayer][1] != handValue[currentPlayer][2])
        {
            System.out.println(handValue[currentPlayer][1] + "/" + handValue[currentPlayer][2]);
        } else
        {
            System.out.println(handValue[currentPlayer][2]); // using the second because it is
                                                             // usually the highest
        }
    }
    public boolean isPlayerDone(int currentPlayer)
    {
        return isDone[currentPlayer];
    }
    public void stay(int currentPlayer)
    {
        isDone[currentPlayer] = true;
    }
    public void removeAllCards()
    {
        cards.clear();
    }
    public void deal()
    {
        for (int i = 0; i < numplayers; i++)
        {
            // first card, only visible to the current player
            
            Random rand = new Random();
            int pos = rand.nextInt(cards.size());
            int card = cards.get(pos);
            String suit = cardsuit.get(pos);
            String shortened = suit.substring(0,1);
            if (card == 1) // the 1 equals an ace, which can be either a 1 or 11
            {
                handValue[i][1] = 1;
                handValue[i][2] = 11;
                numAces[i]++;
                publicHand[i] = "?";
                privateHand[i] = ("\tA" + shortened);
            } else if (card >= 11 && card <= 13)
            {
                handValue[i][1] = 10;
                handValue[i][2] = 10;
                if (card == 11) // jack
                {
                    publicHand[i] = "?";
                    privateHand[i] = ("\tJ" + shortened);
                } else if (card == 12) // queen
                {
                    publicHand[i] = "?";
                    privateHand[i] = ("\tQ" + shortened);
                } else if (card == 13) // king
                {
                    publicHand[i] = "?";
                    privateHand[i] = ("\tQ" + shortened);
                }
            } else
            {
                handValue[i][1] = card;
                handValue[i][2] = card;
                publicHand[i] = "?";
                privateHand[i] = ("\t" + card + shortened);
            }
            cards.remove(pos);
            cardsuit.remove(pos);
            
            // second card
            this.hit(i);
        }
    }
    public void computerMoves(int currentPlayer)
    {
        int til21 = 21 - handValue[currentPlayer][2];
        if (til21 >= 10)
        {
            this.hit(currentPlayer);
        } else
        {
            int stillAliveCards = 0;
            for (int i = 1; i <= til21; i++)
            {
                stillAliveCards += Collections.frequency(cards, i);
            }
            double probAlive = (stillAliveCards / cards.size()) * 100;
            if (probAlive >= 60.0)
            {
                this.hit(currentPlayer);
            } else
            {
                this.stay(currentPlayer);
            }
        }
    }
    public int hit(int currentPlayer)
    {
        Random rand = new Random();
        int pos = rand.nextInt(cards.size());
        int card = cards.get(pos);
        String suit = cardsuit.get(pos);
        String shortened = suit.substring(0,1);
        if (card == 1) // the 1 equals an ace, which can be either a 1 or 11
        {
            if (numAces[currentPlayer] >= 2)
            {
                int temp = handValue[currentPlayer][1];
                handValue[currentPlayer][2] = temp + 11;
                handValue[currentPlayer][1] += 1;
            } else
            {
                handValue[currentPlayer][1] += 1;
                handValue[currentPlayer][2] += 11;
                numAces[currentPlayer]++;
            }
            publicHand[currentPlayer] += ("\tA" + shortened);
            privateHand[currentPlayer] += ("\tA" + shortened);
        } else if (card >= 11 && card <= 13)
        {
            handValue[currentPlayer][1] += 10;
            handValue[currentPlayer][2] += 10;
            if (card == 11) // jack
            {
                publicHand[currentPlayer] += ("\tJ" + shortened);
                privateHand[currentPlayer] += ("\tJ" + shortened);
            } else if (card == 12) // queen
            {
                publicHand[currentPlayer] += ("\tQ" + shortened);
                privateHand[currentPlayer] += ("\tQ" + shortened);
            } else if (card == 13) // king
            {
                publicHand[currentPlayer] += ("\tQ" + shortened);
                privateHand[currentPlayer] += ("\tQ" + shortened);
            }
        } else
        {
            handValue[currentPlayer][1] += card;
            handValue[currentPlayer][2] += card;
            publicHand[currentPlayer] += ("\t" + card + shortened);
            privateHand[currentPlayer] += ("\t" + card + shortened);
        }
        cards.remove(pos);
        cardsuit.remove(pos);
        return card;
    }
    public boolean isBusted(int currentPlayer)
    {
        if (handValue[currentPlayer][2] > 21)
        {
            if (handValue[currentPlayer][1] > 21)
            {
                return true;
            } else
            {
                handValue[currentPlayer][2] = handValue[currentPlayer][1];
                return false;
            }
        } else
        {
            return false;
        }
    }
    public String nameOfWinner()
    {
        int highest = handValue[0][2];
        int highestIndex = 0;
        for (int i = 1; i < numplayers; i++)
        {
            if (handValue[i][2] > highest)
            {
                highest = handValue[i][2];
                highestIndex = i;
            }
        }
        return players[highestIndex];
    }
}