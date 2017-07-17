package smartclass.com.smartclass.course.fragments.quizzes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import smartclass.com.smartclass.R;
import smartclass.com.smartclass.models.Quiz;

/**
 * Created by kevinT on 2017-07-16.
 */

public class QuizListAdapter extends RecyclerView.Adapter<QuizListAdapter.QuizViewHolder> {

    private Context context;
    private List<Quiz> quizList;
    private QuizContract.View mView;
    private QuizContract.Presenter mPresenter;

    public QuizListAdapter(Context context, @NonNull List<Quiz> quizList, @NonNull QuizContract.Presenter presenter) {
        this.context = context;
        this.quizList = quizList;
        mPresenter = presenter;
    }

    @Override
    public QuizViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.quiz_list_item, parent, false);
        return new QuizViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(QuizViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public void onBindViewHolder(QuizViewHolder holder, int position) {
        final Quiz quiz = quizList.get(position);

        if (holder.titleLabel != null) {
            String title = quiz.getTitle();
            holder.titleLabel.setText(title == null ? "N/A" : title);
        }
        if (holder.dateLabel != null) {
            Date date = quiz.getDate();
            holder.dateLabel.setText(date == null ? "N/A" : DateFormat.getDateInstance().format(date));
        }

        if (holder.statusLabel != null && context != null) {
            boolean quizIsActive = quiz.isActivated();
            holder.statusLabel.setText(quizIsActive ? "Active" : "Inactive");
            holder.statusLabel.setTextColor(context.getResources().getColor(quizIsActive ? R.color.green : R.color.red));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onQuizSelected(quiz);
            }
        });
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
