package smartclass.com.smartclass.Models;

/**
 * Created by peterpogorski on 2017-06-12.
 */
public class Course {

    private String className;
    private String teacherName;
    private int studentCount;

    public Course(String className, String teacherName, int studentCount) {
        this.className = className;
        this.teacherName = teacherName;
        this.studentCount = studentCount;
    }

    public String getClassName() {
        return className;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public int getStudentCount() {
        return studentCount;
    }
}
