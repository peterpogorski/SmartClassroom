package smartclass.com.smartclass.classroom;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import smartclass.com.smartclass.R;
import smartclass.com.smartclass.models.Student;

/**
 * Created by kevinT on 2017-06-15.
 */

public class StudentsListAdapter extends RecyclerView.Adapter<StudentsListAdapter.StudentViewHolder> {

    private List<Student> studentsList;
    private ClassroomContract.View mClassroomView;
    private ClassroomContract.Presenter mPresenter;

    public StudentsListAdapter(List<Student> studentsList, ClassroomContract.Presenter presenter) {
        this.studentsList = studentsList;
        mPresenter = presenter;
    }

    @Override
    public StudentsListAdapter.StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_list_item, parent, false);
        return new StudentsListAdapter.StudentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StudentsListAdapter.StudentViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public void onBindViewHolder(StudentsListAdapter.StudentViewHolder holder, int position) {
        final Student student = studentsList.get(position);
        holder.studentName.setText(student.getFirstName() + " " + student.getLastName());
        holder.studentCurrentGoalsCount.setText("0");
        holder.studentCompletedGoalsCount.setText("10");
        holder.studentAverage.setText("65%");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mPresenter.onCourseSelected(course);
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentsList.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder{
        public TextView studentName;
        public TextView studentCurrentGoalsCount;
        public TextView studentCompletedGoalsCount;
        public TextView studentAverage;
        public View itemView;

        public StudentViewHolder(View view) {
            super(view);
            itemView = view;
            studentName = (TextView) view.findViewById(R.id.student_fullname);
            studentCurrentGoalsCount = (TextView) view.findViewById(R.id.current_goals_count);
            studentCompletedGoalsCount = (TextView) view.findViewById(R.id.completed_goals_count);
            studentAverage = (TextView) view.findViewById(R.id.student_average);
        }

    }
}
