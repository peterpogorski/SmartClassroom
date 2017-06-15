package smartclass.com.smartclass.CourseList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import smartclass.com.smartclass.Models.Course;
import smartclass.com.smartclass.R;

/**
 * Created by peterpogorski on 2017-06-12.
 */

public class CourseListAdapter extends RecyclerView.Adapter<CourseListAdapter.CourseViewHolder> {

    private List<Course> courseList;
    private CourseListContract.View mCourseView;
    private CourseListContract.Presenter mPresenter;

    public CourseListAdapter(List<Course> courseList, CourseListContract.Presenter presenter) {
        this.courseList = courseList;
        mPresenter = presenter;
    }

    @Override
    public CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_list_item, parent, false);
        return new CourseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CourseViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public void onBindViewHolder(CourseViewHolder holder, int position) {
        final Course course = courseList.get(position);
        holder.courseTitle.setText(course.getClassName());
        holder.professorName.setText(course.getTeacherName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onCourseSelected(course);
            }
        });
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder{
        public TextView courseTitle;
        public TextView professorName;
        public View itemView;

        public CourseViewHolder(View view) {
            super(view);
            itemView = view;
            courseTitle = (TextView) view.findViewById(R.id.course_title);
            professorName = (TextView) view.findViewById(R.id.professor_name);
        }

    }
}
