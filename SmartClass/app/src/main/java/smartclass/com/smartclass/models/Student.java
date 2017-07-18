package smartclass.com.smartclass.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by max on 2017-06-15.
 */

public class Student {

    @SerializedName("firstName")
    private String firstName;

    @SerializedName("lastName")
    private String lastName;

    //@SerializedName("quizHistory")
    //private ArrayList<String> quizHistory;

    @SerializedName("classrooms")
    private ArrayList<Classroom> classrooms;

    //public ArrayList<String> getQuizHistory() { return this.quizHistory; }
    //public ArrayList<StudentGoalHistory> getGoals() { return this.goals; }
    //public int getQuizHistoryCount() { return this.quizHistory.size(); }
    //public int getGoalCount() { return this.goals.size(); }

    public ArrayList<Classroom> getClassrooms() {
        return classrooms;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
