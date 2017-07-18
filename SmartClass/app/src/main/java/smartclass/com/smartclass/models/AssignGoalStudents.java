package smartclass.com.smartclass.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by peterpogorski on 2017-07-17.
 */

public class AssignGoalStudents {

    @SerializedName("students")
    ArrayList<String> students;

    @SerializedName("goal")
    GoalCreate goalCreate;

    public AssignGoalStudents(ArrayList<String> students, GoalCreate goalCreate) {
        this.students = students;
        this.goalCreate = goalCreate;
    }
}
