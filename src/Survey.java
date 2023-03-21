import java.util.ArrayList;
import java.util.List;

public class Survey {
    private String title;
    private String topic;
    private String description;
    private ArrayList<Question> questions;
    private ArrayList<Candidate> candidates;

    public Survey(String title, String topic, String description) {
        this.title = title;
        this.topic = topic;
        this.description = description;
        this.questions = new ArrayList<>();
        this.candidates = new ArrayList<>();
    }

    public void addQuestion(Question question) { //adding question method
        if (questions.size() < 40 && !questions.contains(question)) {
            questions.add(question);
        }
    }

    public void addCandidate(Candidate candidate) {
        if (!candidates.contains(candidate)) {
            candidates.add(candidate);
        }
    }
    public void removeQuestion(Question question) { //remove question method
        questions.remove(question);
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public ArrayList<Candidate> getCandidates() {
        return candidates;
    }
//    public void setCandidates(ArrayList<Candidate> candidates) {
//        this.candidates = candidates;
//    }


    public void printSurveyResult() { // Print survey result method
        System.out.println(title + " Results:");
        for (Question q : questions) {
            int[] counts = q.getAnswerCounts();
            String output = q.getText() + "\n";
            output += "\tAgree: " + counts[0] + "\n";
            output += "\tSlightly Agree: " + counts[1] + "\n";
            output += "\tSlightly Disagree: " + counts[2] + "\n";
            output += "\tDisagree: " + counts[3];
            System.out.println(output);
        }
    }

    public String getMostGivenAnswer() { // Most given answer method
        String mostGivenAnswer = "";
        int mostGivenCount = 0;
        for (Question q : questions) {
            int[] counts = q.getAnswerCounts();
            for (int i = 0; i < counts.length; i++) {
                if (counts[i] > mostGivenCount) {
                    mostGivenCount = counts[i];
                    switch (i) {
                        case 0:
                            mostGivenAnswer = "Agree";
                            break;
                        case 1:
                            mostGivenAnswer = "Slightly Agree";
                            break;
                        case 2:
                            mostGivenAnswer = "Slightly Disagree";
                            break;
                        case 3:
                            mostGivenAnswer = "Disagree";
                            break;
                    }
                }
            }
        }
        return mostGivenAnswer;
    }

    public Candidate getCandidateWithMostSurveys() { //Candidate who has taken the most survey
        Candidate candidateWithMostSurveys = null;
        int maxSurveys = 0;
        for (Candidate c : candidates) {
            if (c.getSurveysTaken().size() > maxSurveys) {
                maxSurveys = c.getSurveysTaken().size();
                candidateWithMostSurveys = c;
            }
        }
        return candidateWithMostSurveys;
    }

    public ArrayList<String> getCandidateAnswers(Candidate candidate) { //finding the answer given by a candidate method
        ArrayList<String> answers = new ArrayList<>();
        for (Question q : questions) {
            String answer = q.getCandidateAnswer(candidate);
            if (answer != null) {
                answers.add(q.getText() + ": " + answer);
            }
        }
        return answers;
    }

}