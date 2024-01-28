package trivia;

import java.util.Deque;
import java.util.LinkedList;

public class Questionnaire {

    enum Category {
        Pop, Science, Sports, Rock
    }

    private final Deque<String> popQuestions = new LinkedList<>();
    private final Deque<String> scienceQuestions = new LinkedList<>();
    private final Deque<String> sportsQuestions = new LinkedList<>();
    private final Deque<String> rockQuestions = new LinkedList<>();

    public Questionnaire(int total) {
        for (int i = 0; i < total; i++) {
            popQuestions.addLast(createPopQuestion(i));
            scienceQuestions.addLast(createScienceQuestion(i));
            sportsQuestions.addLast(createSportsQuestion(i));
            rockQuestions.addLast(createRockQuestion(i));
        }
    }

    public String createPopQuestion(final int index) {
        return "Pop Question " + index;
    }

    public String createScienceQuestion(final int index) {
        return "Science Question " + index;
    }

    public String createSportsQuestion(final int index) {
        return "Sports Question " + index;
    }
    public String createRockQuestion(final int index) {
        return "Rock Question " + index;
    }

    public String removeFirst(final Category category) {
        return switch (category) {
            case Pop -> popQuestions.removeFirst();
            case Science -> scienceQuestions.removeFirst();
            case Rock -> rockQuestions.removeFirst();
            case Sports -> sportsQuestions.removeFirst();
        };
    }
}
