import java.util.Arrays;
import java.util.HashMap;

public class Question {

    private String text;
    private String[] alternatives;
    private HashMap<Candidate, String> candidateAnswers;

    public Question(String text) {
        this.text = text;
        this.alternatives = new String[]{"Agree", "Slightly Agree", "Slightly Disagree", "Disagree"};
        this.candidateAnswers = new HashMap<>();
    }

    public String getText() {
        return text;
    }

    public String[] getAlternatives() {
        return alternatives;
    }

    public int[] getAnswerCounts() {
        int[] counts = new int[4];
        Arrays.fill(counts,0 );
        for (String answer : candidateAnswers.values()) {
            switch (answer) {
                case "Agree":
                    counts[0]++;
                    break;
                case "Slightly Agree":
                    counts[1]++;
                    break;
                case "Slightly Disagree":
                    counts[2]++;
                    break;
                case "Disagree":
                    counts[3]++;
                    break;
            }
        }
        return counts;
    }

    public String getCandidateAnswer(Candidate candidate) {
        return candidateAnswers.get(candidate);
    }


    public void setCandidateAnswer(Candidate candidate, String answer) {
        if (Arrays.asList(alternatives).contains(answer)) {
            candidateAnswers.put(candidate, answer);
        }
    }
}