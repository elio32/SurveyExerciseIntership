import java.util.ArrayList;

public class Candidate {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private ArrayList<Survey> surveysTaken;

    public Candidate(String firstName, String lastName, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.surveysTaken = new ArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

//    public String getEmail() {
//        return email;
//    }

//    public String getPhoneNumber() {
//        return phoneNumber;
//    }

    public ArrayList<Survey> getSurveysTaken() {
        return surveysTaken;
    }

    public void takeSurvey(Survey survey) {
        if (!surveysTaken.contains(survey)) {
            surveysTaken.add(survey);
        }
    }
}