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
        this.isDone = new boolean[numPlayers];
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
                handValue[i][0] = 1;
                handValue[i][1] = 11;
                numAces[i]++;
                publicHand[i] = "?";
                privateHand[i] = ("\tA" + shortened);
            } else if (card >= 11 && card <= 13)
            {
                handValue[i][0] = 10;
                handValue[i][1] = 10;
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
                    privateHand[i] = ("\tK" + shortened);
                }
            } else
            {
                handValue[i][0] = card;
                handValue[i][1] = card;
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
        int til21 = 21 - handValue[currentPlayer][1];
        if (til21 >= 10)
        {
            System.out.println(players[currentPlayer] + ": Hit");
            this.hitandprint(currentPlayer);
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
                System.out.println(players[currentPlayer] + ": Hit");
                this.hitandprint(currentPlayer);
            } else
            {
                System.out.println(players[currentPlayer] + ": Stay");
                this.stay(currentPlayer);
            }
        }
    }
    public void hit(int currentPlayer)
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
                handValue[currentPlayer][1] = temp + 11;
                handValue[currentPlayer][0] += 1;
            } else
            {
                handValue[currentPlayer][0] += 1;
                handValue[currentPlayer][1] += 11;
                numAces[currentPlayer]++;
            }
            publicHand[currentPlayer] += ("\tA" + shortened);
            privateHand[currentPlayer] += ("\tA" + shortened);
        } else if (card >= 11 && card <= 13)
        {
            handValue[currentPlayer][0] += 10;
            handValue[currentPlayer][1] += 10;
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
                publicHand[currentPlayer] += ("\tK" + shortened);
                privateHand[currentPlayer] += ("\tK" + shortened);
            }
        } else
        {
            handValue[currentPlayer][0] += card;
            handValue[currentPlayer][1] += card;
            publicHand[currentPlayer] += ("\t" + card + shortened);
            privateHand[currentPlayer] += ("\t" + card + shortened);
        }
        cards.remove(pos);
        cardsuit.remove(pos);
    }
    public void hitandprint(int currentPlayer)
    {
        Random rand = new Random();
        int pos = rand.nextInt(cards.size());
        int card = cards.get(pos);
        String cardstr = "";
        String suit = cardsuit.get(pos);
        String shortened = suit.substring(0,1);
        if (card == 1) // the 1 equals an ace, which can be either a 1 or 11
        {
            if (numAces[currentPlayer] >= 2)
            {
                int temp = handValue[currentPlayer][0];
                handValue[currentPlayer][1] = temp + 11;
                handValue[currentPlayer][0] += 1;
            } else
            {
                handValue[currentPlayer][0] += 1;
                handValue[currentPlayer][1] += 11;
                numAces[currentPlayer]++;
            }
            cardstr = "A" + shortened;
            publicHand[currentPlayer] += ("\tA" + shortened);
            privateHand[currentPlayer] += ("\tA" + shortened);
        } else if (card >= 11 && card <= 13)
        {
            handValue[currentPlayer][0] += 10;
            handValue[currentPlayer][1] += 10;
            if (card == 11) // jack
            {
                cardstr = "J" + shortened;
                publicHand[currentPlayer] += ("\tJ" + shortened);
                privateHand[currentPlayer] += ("\tJ" + shortened);
            } else if (card == 12) // queen
            {
                cardstr = "Q" + shortened;
                publicHand[currentPlayer] += ("\tQ" + shortened);
                privateHand[currentPlayer] += ("\tQ" + shortened);
            } else if (card == 13) // king
            {
                cardstr = "K" + shortened;
                publicHand[currentPlayer] += ("\tK" + shortened);
                privateHand[currentPlayer] += ("\tK" + shortened);
            }
        } else
        {
            cardstr = card + shortened;
            handValue[currentPlayer][0] += card;
            handValue[currentPlayer][1] += card;
            publicHand[currentPlayer] += ("\t" + card + shortened);
            privateHand[currentPlayer] += ("\t" + card + shortened);
        }
        cards.remove(pos);
        cardsuit.remove(pos);
        System.out.println("You got a(n) " + cardstr);
    }
    public boolean isBusted(int currentPlayer)
    {
        if (handValue[currentPlayer][1] > 21)
        {
            if (handValue[currentPlayer][0] > 21)
            {
                return true;
            } else
            {
                handValue[currentPlayer][1] = handValue[currentPlayer][0];
                return false;
            }
        } else
        {
            return false;
        }
    }
    public String declareWinner()
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
                return players[highestIndex] + " wins with " + handValue[highestIndex][1];
            } else
            {
                String people = "We have a tie with " + highest + "\n";
                for (Integer i : indexOfTies)
                {
                    people += players[i];
                }
            }
        } else
        {
            return "No Winner. Everyone has busted";
        }
        return "Nothing";
    }
}