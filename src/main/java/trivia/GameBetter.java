package trivia;

import java.util.*;

// REFACTOR ME
public class GameBetter implements IGame {
   private final List<String> players = new ArrayList<>();
   private final int[] places = new int[6];
   private final int[] purses = new int[6];
   private final boolean[] inPenaltyBox = new boolean[6];


   private int currentPlayer = 0;
   private boolean isGettingOutOfPenaltyBox;
   private final Questionnaire questionnaire;

   private final GameBoard gameBoard;
   public GameBetter() {
      questionnaire = new Questionnaire(50);
      gameBoard = new GameBoard();
   }

   public boolean isPlayable() {
      return (howManyPlayers() >= 2);
   }

   public boolean add(String playerName) {
      players.add(playerName);
      places[howManyPlayers()] = 0;
      purses[howManyPlayers()] = 0;
      inPenaltyBox[howManyPlayers()] = false;

      System.out.println(playerName + " was added");
      System.out.println("They are player number " + players.size());
      return true;
   }

   public int howManyPlayers() {
      return players.size();
   }


  public void roll(int roll) {
      System.out.println(players.get(currentPlayer) + " is the current player");
      System.out.println("They have rolled a " + roll);
      if (inPenaltyBox[currentPlayer]) {
         positionWhenInPenaltyBox(roll);
      } else {
         positionWhenNotInPenaltyBox(roll);
         askQuestion();
      }
   }

   private void positionWhenInPenaltyBox(int roll) {
      if (isEvenRolled(roll)) {
         moveIntoPenaltyBox();
      } else {
         moveOutOfPenaltyBox();
         positionWhenNotInPenaltyBox(roll);
         askQuestion();
      }
   }

   private void moveOutOfPenaltyBox() {
      isGettingOutOfPenaltyBox = true;
      System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");
   }

   private void moveIntoPenaltyBox() {
      System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
      isGettingOutOfPenaltyBox = false;
   }

   private static boolean isEvenRolled(int roll) {
      return roll % 2 == 0;
   }

   private void positionWhenNotInPenaltyBox(int roll) {
      places[currentPlayer] = places[currentPlayer] + roll;
      if (places[currentPlayer] > GameBoard.totalPositionInBoard - 1) {
         places[currentPlayer] = places[currentPlayer] - GameBoard.totalPositionInBoard;
      }

      System.out.println(players.get(currentPlayer)
                         + "'s new location is "
                         + places[currentPlayer]);
      System.out.println("The category is " + currentCategory());
   }

   private void askQuestion() {
      System.out.println(questionnaire.removeFirst(currentCategory()));
   }


   private Questionnaire.Category currentCategory() {
      return gameBoard.getCategoryByPosition(places[currentPlayer]);
   }

   public boolean wasCorrectlyAnswered() {
      if (inPenaltyBox[currentPlayer]) {
         return correctAnswerInPenalty();
      } else {
         return correctAnswerNotInPenalty();
      }
   }

   private boolean correctAnswerNotInPenalty() {
      return displayWinner("Answer was corrent!!!!");
   }

   private boolean correctAnswerInPenalty() {
      if (isGettingOutOfPenaltyBox) {
         return displayWinner("Answer was correct!!!!");
      } else {
         currentPlayer++;
         if (currentPlayer == players.size()) currentPlayer = 0;
         return true;
      }
   }

   private boolean displayWinner(String message) {
      System.out.println(message);
      purses[currentPlayer]++;
      System.out.println(players.get(currentPlayer)
              + " now has "
              + purses[currentPlayer]
              + " Gold Coins.");

      boolean winner = didPlayerWin();
      currentPlayer++;
      if (currentPlayer == players.size()) currentPlayer = 0;
      return winner;
   }

   public boolean wrongAnswer() {
      System.out.println("Question was incorrectly answered");
      System.out.println(players.get(currentPlayer) + " was sent to the penalty box");
      inPenaltyBox[currentPlayer] = true;

      currentPlayer++;
      if (currentPlayer == players.size()) currentPlayer = 0;
      return true;
   }


   private boolean didPlayerWin() {
      return !(purses[currentPlayer] == 6);
   }
}
