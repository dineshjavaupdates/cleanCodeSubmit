package trivia;

import java.util.HashMap;
import java.util.Map;

public class GameBoard {

    private final Map<Integer, Questionnaire.Category> gameBoardMap = new HashMap<>();

    public static final int totalPositionInBoard = 12;

    public GameBoard() {
        gameBoardMap.put(0, Questionnaire.Category.Pop);
        gameBoardMap.put(1, Questionnaire.Category.Science);
        gameBoardMap.put(2, Questionnaire.Category.Sports);
        gameBoardMap.put(3, Questionnaire.Category.Rock);
        gameBoardMap.put(4, Questionnaire.Category.Pop);
        gameBoardMap.put(5, Questionnaire.Category.Science);
        gameBoardMap.put(6, Questionnaire.Category.Sports);
        gameBoardMap.put(7, Questionnaire.Category.Rock);
        gameBoardMap.put(8, Questionnaire.Category.Pop);
        gameBoardMap.put(9, Questionnaire.Category.Science);
        gameBoardMap.put(10, Questionnaire.Category.Sports);
        gameBoardMap.put(11, Questionnaire.Category.Rock);
    }

    public Questionnaire.Category getCategoryByPosition(int position) {
        return gameBoardMap.get(position);
    }
}
