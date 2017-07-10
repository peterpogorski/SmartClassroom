package smartclass.com.smartclass.classroom.teacherGoals;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import smartclass.com.smartclass.R;
import smartclass.com.smartclass.classroom.students.StudentsFragment;
import smartclass.com.smartclass.classroom.teacherGoals.goalCreation.GoalCreationActivity;
import smartclass.com.smartclass.demodata.SmartClassRetrofit;
import smartclass.com.smartclass.demodata.SmartClassService;
import smartclass.com.smartclass.demodata.TeacherModeDataManager;
import smartclass.com.smartclass.goalViewing.ViewGoalActivity;
import smartclass.com.smartclass.models.Goal;
import smartclass.com.smartclass.models.Student;

/**
 * Created by kevinT on 2017-06-16.
 */

public class GoalFragment extends Fragment implements GoalContract.View {

    /* CONSTANTS */
    private static final String TAG = "GoalFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 2;

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    private ArrayList<Goal> goals;

    private LayoutManagerType mCurrentLayoutManagerType;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ProgressBar mLoadingSpinner;
    private GoalListAdapter mListAdapter;
    private GoalPresenter mPresenter;
    private Retrofit mRetrofit;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new GoalPresenter(this);
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

        loadGoals();

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_teacher_mode_goals, menu);
        if (!TeacherModeDataManager.getInstance().isTeacherModeEnabled()) {
            menu.findItem(R.id.action_create_goal).setVisible(false);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    /**
     * Handles action bar button clicks
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                loadGoals();
                return true;
            case R.id.action_create_goal:
                Context context = getActivity();
                if (context != null) {
                    Intent intent = new Intent(getActivity(), GoalCreationActivity.class);
                    startActivity(intent);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setmRecyclerViewLayoutManager(LayoutManagerType layoutManagerType) {
        int scrollPosition = 0;

        // If a layout manager has already been set, get current scroll position.
        if (mRecyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }

        switch (layoutManagerType) {
            case GRID_LAYOUT_MANAGER:
                mLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT);
                mCurrentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
                break;
            case LINEAR_LAYOUT_MANAGER:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
                break;
            default:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        }

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(scrollPosition);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save currently selected layout manager
        savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, mCurrentLayoutManagerType);
        super.onSaveInstanceState(savedInstanceState);
    }

    /* Contract methods */

    @Override
    public void hideLoading() {
        mLoadingSpinner.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    // TODO: Move this to presenter
    public void loadGoals() {
        SmartClassService smartClassService = mRetrofit.create(SmartClassService.class);

        Call<ArrayList<Goal>> getGoals = smartClassService.getGoals();
        getGoals.enqueue(new Callback<ArrayList<Goal>>() {
            @Override
            public void onResponse(Call<ArrayList<Goal>> call, Response<ArrayList<Goal>> response) {
                goals = response.body();
                TeacherModeDataManager.getInstance().setGoals(goals);

//                if (mListAdapter == null) {
                mListAdapter = new GoalListAdapter(goals, mPresenter);
                mRecyclerView.setAdapter(mListAdapter);
//                } else {
//                    mListAdapter.notifyDataSetChanged();
//                }

                mPresenter.onGoalsLoaded();
            }

            @Override
            public void onFailure(Call<ArrayList<Goal>> call, Throwable t) {

            }
        });
    }

    @Override
    public void showGoal(Goal goal) {
        FragmentActivity activity = getActivity();
        if (activity == null) { return; }

        Intent intent = new Intent(activity, ViewGoalActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("goal", goal);
        intent.putExtras(bundle);

        startActivity(intent);
    }
}
