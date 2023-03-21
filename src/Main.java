import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Creating a new survey
        System.out.println("Create a new Survey:");
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Topic: ");
        String topic = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();
        Survey survey = new Survey(title, topic, description);

        // Adding questions to the survey
        int numberOfQuestions = 0;
        while (numberOfQuestions < 10 || numberOfQuestions > 40) {
            System.out.print("How many questions would you like to add (10-40)? ");
            numberOfQuestions = scanner.nextInt();
            scanner.nextLine();
        }
        for (int i = 0; i < numberOfQuestions; i++) {
            System.out.print("Enter question text: ");
            String questionText = scanner.nextLine();
            Question question = new Question(questionText);
            for (String alternatives : question.getAlternatives()) {
                System.out.print("Enter alternative for " + alternatives + ": ");
                scanner.nextLine();
            }
            survey.addQuestion(question);
        }

        // Adding candidates to the survey
        System.out.print("How many candidates will take the survey? ");
        int numCandidates = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        for (int i = 0; i < numCandidates; i++) {
            System.out.print("Enter candidate first name: ");
            String firstName = scanner.nextLine();
            System.out.print("Enter candidate last name: ");
            String lastName = scanner.nextLine();
            System.out.print("Enter candidate email: ");
            String email = scanner.nextLine();
            System.out.print("Enter candidate phone number: ");
            String phoneNumber = scanner.nextLine();
            Candidate candidate = new Candidate(firstName, lastName, email, phoneNumber);
            survey.addCandidate(candidate);
        }

        // Candidates taking the survey
        for (Candidate candidate : survey.getCandidates()) {
            System.out.println(candidate.getFirstName() + ", please take the survey:");
            for (Question question : survey.getQuestions()) {
                System.out.println(question.getText());
                int i = 1;
                for (String alternatives : question.getAlternatives()) {
                    System.out.println(i++ + ") " + alternatives);
                }
                String answer = scanner.nextLine();
                question.setCandidateAnswer(candidate, answer);
            }
            candidate.takeSurvey(survey);
        }

        // Printing survey results
        survey.printSurveyResult();

        // Finding the most given answer on the survey
        String mostGivenAnswer = survey.getMostGivenAnswer();
        System.out.println("The most given answer is: " + mostGivenAnswer);

        // Finding the candidate who has taken the most surveys
        Candidate candidateWithMostSurveys = survey.getCandidateWithMostSurveys();
        System.out.println("The candidate with the most surveys is: " + candidateWithMostSurveys.getFirstName()
                + " " + candidateWithMostSurveys.getLastName());
        // Finding  the answers given by a candidate on a survey
        System.out.print("Enter candidate first name to view their answers: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter candidate last name: ");
        String lastName = scanner.nextLine();
        Candidate candidate = null;
        for (Candidate character : survey.getCandidates()) {
            if (character.getFirstName().equals(firstName) && character.getLastName().equals(lastName)) {
                candidate = character;
                break;
            }
        }
        if (candidate != null) {
            ArrayList<String> answers = survey.getCandidateAnswers(candidate);
            if (!answers.isEmpty()) {
                System.out.println(candidate.getFirstName() + " " + candidate.getLastName() + "'s answers:");
                for (String answer : answers) {
                    System.out.println(answer);
                }
            } else {
                System.out.println(candidate.getFirstName() + " " + candidate.getLastName() + " has not answered any questions.");
            }
        } else {
            System.out.println("Candidate not found.");
        }

        // Add or remove a question to an existing survey
        System.out.println("Do you want to add or remove a question from the survey? (Enter 'add' or 'remove')");
        String action = scanner.nextLine();
        switch (action) {
            case "add":
                System.out.print("Enter new question text: ");
                String questionText = scanner.nextLine();
                Question question = new Question(questionText);
                for (String alt : question.getAlternatives()) {
                    System.out.print("Enter alternative for " + alt + ": ");
                }
                survey.addQuestion(question);
                break;
            case "remove":
                System.out.print("Enter question text to remove: ");
                questionText = scanner.nextLine();
                for (Question q : survey.getQuestions()) {
                    if (q.getText().equals(questionText)) {
                        survey.removeQuestion(q);
                        break;
                    }
                }
                break;
            default:
                System.out.println("Invalid input.");
        }

        // Checks if a question is answered by less than 50% of candidates and remove it from the survey
        ArrayList<Question> questionsToRemove = new ArrayList<>();
        for (Question q : survey.getQuestions()) {
            int numberAnswered = q.getAnswerCounts()[0] + q.getAnswerCounts()[1] + q.getAnswerCounts()[2] + q.getAnswerCounts()[3];
            double percentAnswered = (double) numberAnswered / survey.getCandidates().size() * 100;
            if (percentAnswered < 50) {
                questionsToRemove.add(q);
            }
        }
        for (Question q : questionsToRemove) {
            survey.removeQuestion(q);
        }

        scanner.close();
    }
}