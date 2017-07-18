package smartclass.com.smartclass.classroom.teacherGoals.goalAssignment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

import smartclass.com.smartclass.R;
import smartclass.com.smartclass.classroom.students.StudentsContract;
import smartclass.com.smartclass.classroom.students.StudentsListAdapter;
import smartclass.com.smartclass.models.Student;

/**
 * Created by peterpogorski on 2017-07-16.
 */

public class GoalAssignmentAdapter extends RecyclerView.Adapter<GoalAssignmentAdapter.GoalAssignmentViewHolder> {

    private List<Student> studentsList;
    private GoalAssignmentContract.View mGoalAssignmentView;
    private GoalAssignmentContract.Presenter mPresenter;
    private Context context;

    public GoalAssignmentAdapter(Context context, List<Student> studentsList, GoalAssignmentContract.Presenter presenter) {
        this.context = context;
        this.studentsList = studentsList;
        mPresenter = presenter;
    }

    @Override
    public void onBindViewHolder(GoalAssignmentViewHolder holder, final int position) {
        final Student student = studentsList.get(position);
        if (holder.studentName == null || holder.currentGoalsCount == null
                || holder.completedGoalsCount == null || holder.studentSelected == null || holder.itemView == null) {
            return;
        }

        if (context != null) {
            //Picasso.with(context).load(student.getFacebook().getProfilePicture()).into(holder.studentPicture);
        }
        holder.studentName.setText(student.getFirstName() + " " + student.getLastName());
        holder.completedGoalsCount.setText(String.format(Locale.CANADA, "%d", (position+3)*3+5));
        holder.studentSelected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                studentsList.get(position).setAssignGoal(isChecked);
            }
        });
    }


    @Override
    public GoalAssignmentAdapter.GoalAssignmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_list_item, parent, false);
        return new GoalAssignmentAdapter.GoalAssignmentViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return studentsList == null ? 0 : studentsList.size();
    }

    public class GoalAssignmentViewHolder extends RecyclerView.ViewHolder{
        public ImageView studentPicture;
        public TextView studentName;
        public TextView currentGoalsCount;
        public TextView completedGoalsCount;
        public CheckBox studentSelected;
        public View itemView;

        public GoalAssignmentViewHolder(View view) {
            super(view);
            itemView = view;
            studentPicture = (ImageView) view.findViewById(R.id.student_picture);
            studentName = (TextView) view.findViewById(R.id.student_fullname);
            currentGoalsCount = (TextView) view.findViewById(R.id.current_goals_count);
            completedGoalsCount = (TextView) view.findViewById(R.id.completed_goals_count);
            studentSelected = (CheckBox) view.findViewById(R.id.assigned);
        }
    }
}
