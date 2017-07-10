package smartclass.com.smartclass.classroom.teacherGoals;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.Date;
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
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.goal_list_item, parent, false);
        return new GoalViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GoalViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public void onBindViewHolder(GoalViewHolder holder, int position) {
        final Goal goal = goalList.get(position);

        holder.goalTitle.setText(goal.getTitle());
        Date startDate = goal.getStartDate();
        Date endDate = goal.getEndDate();
        if (startDate != null && endDate != null) {
            holder.startDate.setText(DateFormat.getDateInstance().format(startDate));
            holder.endDate.setText(DateFormat.getDateInstance().format(endDate));
        } else {
            holder.startDateLabel.setVisibility(View.GONE);
            holder.endDateLabel.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onGoalSelected(goal);
            }
        });
    }

    @Override
    public int getItemCount() {
        int size = goalList != null ? size = goalList.size() : 0;

        return size;
    }

    public class GoalViewHolder extends RecyclerView.ViewHolder{
        public View itemView;
        public TextView goalTitle;
        public TextView startDateLabel;
        public TextView endDateLabel;
        public TextView startDate;
        public TextView endDate;
        public ImageView goalType;


        public GoalViewHolder(View view) {
            super(view);
            itemView = view;
            goalTitle = (TextView) view.findViewById(R.id.goal_title);
            startDateLabel = (TextView) view.findViewById(R.id.start_date_label);
            endDateLabel = (TextView) view.findViewById(R.id.end_date_label);
            startDate = (TextView) view.findViewById(R.id.start_date);
            endDate = (TextView) view.findViewById(R.id.end_date);
            goalType = (ImageView) view.findViewById(R.id.goal_type);
        }
    }
}
