package smartclass.com.smartclass.classroom.teacherGoals;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import smartclass.com.smartclass.R;
import smartclass.com.smartclass.models.Goal;

/**
 * Created by kevinT on 2017-06-16.
 */

public class GoalListAdapter extends RecyclerView.Adapter<GoalListAdapter.GoalViewHolder> {

    private List<Goal> goalList;
    private GoalContract.View mClassroomView;
    private GoalContract.Presenter mPresenter;

    public GoalListAdapter(@NonNull List<Goal> goalList, @NonNull GoalContract.Presenter presenter) {
        this.goalList = goalList;
        mPresenter = presenter;
    }

    @Override
    public GoalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_list_item, parent, false);
        return new GoalViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GoalViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public void onBindViewHolder(GoalViewHolder holder, int position) {
        final Goal goal = goalList.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mPresenter.onStudentSelected(student);
            }
        });
    }

    @Override
    public int getItemCount() {
        return goalList.size();
    }

    public class GoalViewHolder extends RecyclerView.ViewHolder{
        public View itemView;

        public GoalViewHolder(View view) {
            super(view);
            itemView = view;
        }
    }
}
