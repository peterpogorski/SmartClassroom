package smartclass.com.smartclass.course.fragments.quizzes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;

import retrofit2.Retrofit;
import smartclass.com.smartclass.R;
import smartclass.com.smartclass.classroom.teacherGoals.GoalFragment;
import smartclass.com.smartclass.demodata.SmartClassRetrofit;
import smartclass.com.smartclass.models.Quiz;

/**
 * Created by peterpogorski on 2017-06-13.
 */

public class QuizFragment extends Fragment implements  QuizContract.View {

    /* CONSTANTS */
    private static final String TAG = "QuizFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 2;

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    private ArrayList<Quiz> quizzes;

    private LayoutManagerType mCurrentLayoutManagerType;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ProgressBar mLoadingSpinner;
    private QuizListAdapter mListAdapter;
    private QuizPresenter mPresenter;
    private Retrofit mRetrofit;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new QuizPresenter(this);
        mPresenter.onCreate();
        mRetrofit = SmartClassRetrofit.getInstance();
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.basic_recyclerview_view, container, false);
        rootView.setTag(TAG);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.list_view);
        mLoadingSpinner = (ProgressBar) rootView.findViewById(R.id.loading_spinner);

        mLayoutManager = new LinearLayoutManager(getActivity());

        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;

        if (savedInstanceState != null) {
            mCurrentLayoutManagerType = (LayoutManagerType) savedInstanceState.getSerializable(KEY_LAYOUT_MANAGER);
        }
        setmRecyclerViewLayoutManager(mCurrentLayoutManagerType);

        loadQuizzes();

        return rootView;
    }

    public void setmRecyclerViewLayoutManager(QuizFragment.LayoutManagerType layoutManagerType) {
        int scrollPosition = 0;

        // If a layout manager has already been set, get current scroll position.
        if (mRecyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }

        switch (layoutManagerType) {
            case GRID_LAYOUT_MANAGER:
                mLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT);
                mCurrentLayoutManagerType = QuizFragment.LayoutManagerType.GRID_LAYOUT_MANAGER;
                break;
            case LINEAR_LAYOUT_MANAGER:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = QuizFragment.LayoutManagerType.LINEAR_LAYOUT_MANAGER;
                break;
            default:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = QuizFragment.LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        }

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(scrollPosition);
    }

    private void loadQuizzes() {
        // TODO
    }

    // CONTRACT METHODS

    @Override
    public void hideLoading() {
        mLoadingSpinner.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showQuiz(Quiz quiz) {
        // TODO
    }
}
