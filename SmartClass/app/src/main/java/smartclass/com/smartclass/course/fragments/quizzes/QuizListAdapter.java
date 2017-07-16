package smartclass.com.smartclass.course.fragments.quizzes;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import smartclass.com.smartclass.R;
import smartclass.com.smartclass.models.Quiz;

/**
 * Created by kevinT on 2017-07-16.
 */

public class QuizListAdapter extends RecyclerView.Adapter<QuizListAdapter.QuizViewHolder> {

    private List<Quiz> quizList;
    private QuizContract.View mView;
    private QuizContract.Presenter mPresenter;

    public QuizListAdapter(@NonNull List<Quiz> quizList, @NonNull QuizContract.Presenter presenter) {
        this.quizList = quizList;
        mPresenter = presenter;
    }

    @Override
    public QuizViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.goal_list_item, parent, false);
        return new QuizViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(QuizViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public void onBindViewHolder(QuizViewHolder holder, int position) {
        final Quiz quiz = quizList.get(position);

        String title = quiz.getTitle();
        holder.titleLabel.setText(title == null ? "N/A" : title);
        // TODO: Set quiz status
        Date date = quiz.getDate();
        holder.dateLabel.setText(date == null ? "N/A" : DateFormat.getDateInstance().format(date));

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mPresenter.onQuizSelected(goal);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return quizList == null ? 0 : quizList.size();
    }

    public class QuizViewHolder extends RecyclerView.ViewHolder{
        public View itemView;
        public TextView titleLabel;
        public TextView dateLabel;
        public TextView statusLabel;

        public QuizViewHolder(View view) {
            super(view);
            itemView = view;
            titleLabel = (TextView) view.findViewById(R.id.quiz_title);
            dateLabel = (TextView) view.findViewById(R.id.quiz_date);
            statusLabel = (TextView) view.findViewById(R.id.quiz_status);
        }
    }
}
