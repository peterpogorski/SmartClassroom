package smartclass.com.smartclass.demodata;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import smartclass.com.smartclass.models.Classroom;
import smartclass.com.smartclass.models.Goal;
import smartclass.com.smartclass.models.Quiz;
import smartclass.com.smartclass.models.Student;
import smartclass.com.smartclass.models.StudentGoalHistory;
import smartclass.com.smartclass.models.StudentQuizHistory;
import smartclass.com.smartclass.models.Teacher;

/**
 * Created by kevinT on 2017-06-15.
 */

public class TeacherModeDataManager {

    private static TeacherModeDataManager instance;

    private boolean teacherModeEnabled = true;
    private String currentClassroomId = "";

    private Teacher currentTeacher;
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Classroom> classrooms;
    private ArrayList<Goal> goals = new ArrayList<>();
    private ArrayList<Quiz> quizzes = new ArrayList<>();

    private Quiz selectedQuiz = null;

    private String currentAttendanceId = "";

    private TeacherModeDataManager() {}

    public static TeacherModeDataManager getInstance() {
        if (instance == null) {
            instance = new TeacherModeDataManager();
        }

        return instance;
    }

    public boolean isTeacherModeEnabled() {
        return teacherModeEnabled;
    }

    public String getCurrentClassroomId() {
        return currentClassroomId;
    }

    public void setCurrentClassroomId(String classroomId) { currentClassroomId = classroomId; }

    private Teacher getTeacher() {
        if (currentTeacher == null) {
            //currentTeacher = new Teacher(null, "Charlie", "Brown", "CBrown", new Date(1984, 8, 12), new ArrayList<Classroom>());
        }

        return currentTeacher;
    }

    public ArrayList<Student> getStudentList() {
//        if (students == null) {
//            createStudentList();
//        }
        if (students == null) {
            students = new ArrayList<>();
        }

        return students;
    }

    public void setStudentsList(ArrayList<Student> students) {
        this.students = students;
    }

    public void init(boolean isTeacher) {
        teacherModeEnabled = isTeacher;
    }

    public ArrayList<Classroom> getClassrooms() {
        if (classrooms == null) {
            createClassroomsList();
        }

        return classrooms;
    }

    public void addGoal(Goal goal) {
        if (goals == null) {
            goals = new ArrayList<>();
        }

        goals.add(goal);
    }

    public ArrayList<Goal> getGoals() {
//        if (goals.isEmpty()) {
//            createInitialGoals();
//        }
        if (goals == null) {
            goals = new ArrayList<>();
        }

        return goals;
    }

    public void setGoals(ArrayList<Goal> goals) {
        this.goals = goals;
    }

    public void createInitialGoals() {
        Date date = new Date();
        goals.add(new Goal("Fourier Assignment", "Do stuff", "assignment", date, date, date, 10.0, 10.0));
        goals.add(new Goal("Circuits Assignment", "Do stuff", "assignment", date, date, date, 10.0, 10.0));
        goals.add(new Goal("Fun Assignment", "Do stuff", "assignment", date, date, date, 10.0, 10.0));
    }

    private void createStudentList() {
        students = new ArrayList<>();
        /*
        Student student1 = new Student(null, "John", "Doe", "JohnDoe", new Date(2000, 10, 14),
                getClassrooms(), new ArrayList<StudentQuizHistory>(), new ArrayList<StudentGoalHistory>());
        Student student2 = new Student(null, "Bob", "Bee", "BobBee", new Date(2000, 5, 4),
                getClassrooms(), new ArrayList<StudentQuizHistory>(), new ArrayList<StudentGoalHistory>());
        Student student3 = new Student(null, "Alice", "Apple", "AliceApple", new Date(2000, 5, 3),
                getClassrooms(), new ArrayList<StudentQuizHistory>(), new ArrayList<StudentGoalHistory>());
        students.add(0, student1);
        students.add(1, student2);
        students.add(2, student3);
        */
    }

    private void createClassroomsList() {
        classrooms = new ArrayList<>();
        /*
        classrooms.add(0, new Classroom("English", "English class ", "ENG101", 1, "academic",
                TeacherModeDataManager.getInstance().getTeacher(),
                new ArrayList<String>(),
                new ArrayList<String>()));
        classrooms.add(1, new Classroom("Chemistry", "Chemistry class ", "CHE101", 1, "academic",
                TeacherModeDataManager.getInstance().getTeacher(),
                new ArrayList<String>(),
                new ArrayList<String>()));
        classrooms.add(2, new Classroom("Biology", "Biology class ", "BIO101", 1, "academic",
                TeacherModeDataManager.getInstance().getTeacher(),
                new ArrayList<String>(),
                new ArrayList<String>()));
                */
    }

    // Quizzes

    public ArrayList<Quiz> getQuizzes() {
        return quizzes;
    }

    public void addQuiz(Quiz quiz) {
        quizzes.add(quiz);
    }

    public void setQuizzes(@NonNull ArrayList<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    public void setSelectedQuiz(@NonNull Quiz quiz) {
        selectedQuiz = quiz;
    }

    @Nullable
    public Quiz getSelectedQuiz() {
        return selectedQuiz;
    }

    public void clearData() {
        currentClassroomId = "";
        currentTeacher = null;
        students = new ArrayList<>();
        classrooms = new ArrayList<>();
        goals = new ArrayList<>();
        quizzes = new ArrayList<>();
        selectedQuiz = null;
    }

    public void setCurrentAttendanceId(@NonNull String id) { currentAttendanceId = id; }
    public String getCurrentAttendanceId() { return currentAttendanceId;}
}
