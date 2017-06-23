package smartclass.com.smartclass.classroom.students;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

import smartclass.com.smartclass.R;
import smartclass.com.smartclass.models.Student;

/**
 * Created by kevinT on 2017-06-15.
 */

public class StudentsListAdapter extends RecyclerView.Adapter<StudentsListAdapter.StudentViewHolder> {

    private List<Student> studentsList;
    private StudentsContract.View mClassroomView;
    private StudentsContract.Presenter mPresenter;
    private Context context;

    public StudentsListAdapter(Context context, List<Student> studentsList, StudentsContract.Presenter presenter) {
        this.context = context;
        this.studentsList = studentsList;
        mPresenter = presenter;
    }

    @Override
    public StudentsListAdapter.StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_list_item, parent, false);
        return new StudentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StudentsListAdapter.StudentViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public void onBindViewHolder(StudentsListAdapter.StudentViewHolder holder, int position) {
        final Student student = studentsList.get(position);
        if (holder.studentName == null || holder.currentGoalsCount == null
                || holder.completedGoalsCount == null || holder.totalPoints == null || holder.itemView == null) {
            return;
        }

        if (context != null) {
            Picasso.with(context).load(student.getFacebook().getProfilePicture()).into(holder.studentPicture);
        }
        holder.studentName.setText(student.getFirstName() + " " + student.getLastName());
        // TODO: Show real data
        holder.currentGoalsCount.setText("5");
        holder.completedGoalsCount.setText(String.format(Locale.CANADA, "%d", (position+3)*3+5));
        holder.totalPoints.setVisibility(View.GONE);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onStudentSelected(student);
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentsList.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder{
        public ImageView studentPicture;
        public TextView studentName;
        public TextView currentGoalsCount;
        public TextView completedGoalsCount;
        public TextView totalPoints;
        public View itemView;

        public StudentViewHolder(View view) {
            super(view);
            itemView = view;
            studentPicture = (ImageView) view.findViewById(R.id.student_picture);
            studentName = (TextView) view.findViewById(R.id.student_fullname);
            currentGoalsCount = (TextView) view.findViewById(R.id.current_goals_count);
            completedGoalsCount = (TextView) view.findViewById(R.id.completed_goals_count);
            totalPoints = (TextView) view.findViewById(R.id.total_points);
        }
    }
}
