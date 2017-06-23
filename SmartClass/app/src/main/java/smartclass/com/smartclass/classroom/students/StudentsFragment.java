package smartclass.com.smartclass.classroom.students;

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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import smartclass.com.smartclass.R;
import smartclass.com.smartclass.demodata.SmartClassRetrofit;
import smartclass.com.smartclass.demodata.SmartClassService;
import smartclass.com.smartclass.demodata.TeacherModeDataManager;
import smartclass.com.smartclass.models.Student;

/**
 * Created by kevinT on 2017-06-15.
 */

public class StudentsFragment extends Fragment implements StudentsContract.View {

    /* CONSTANTS */
    private static final String TAG = "StudentsFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 2;

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    private ArrayList<Student> mStudentList = new ArrayList<>();

    private LayoutManagerType mCurrentLayoutManagerType;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ProgressBar mLoadingSpinner;
    private StudentsListAdapter mListAdapter;
    private StudentsPresenter mPresenter;
    private Retrofit mRetrofit;


    public static StudentsFragment newInstance() {
        return new StudentsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new StudentsPresenter(this);

        mRetrofit = SmartClassRetrofit.getInstance();
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

        SmartClassService smartClassService = mRetrofit.create(SmartClassService.class);

        Call<ArrayList<Student>> getStudents = smartClassService.getStudents();
        getStudents.enqueue(new Callback<ArrayList<Student>>() {
            @Override
            public void onResponse(Call<ArrayList<Student>> call, Response<ArrayList<Student>> response) {
                mPresenter.onStudentListLoaded(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Student>> call, Throwable t) {

            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        // Refresh the students list
        if (mListAdapter != null) {
            mStudentList = TeacherModeDataManager.getInstance().getStudentList();
            mListAdapter.notifyDataSetChanged();
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

    /**
     * Add a single student to the students list for this classroom
     */
    @Override
    public void addStudent(Student student) {
        mStudentList.add(student);
        if (mListAdapter != null) {
            mListAdapter.notifyDataSetChanged();
        }
    }

    /**
     * Initializes the fragment with a list of students
     */
    @Override
    public void initStudentsList(ArrayList<Student> students) {
        mStudentList = students;
        TeacherModeDataManager.getInstance().setStudentsList(students);
//        if(mListAdapter != null) {
//            mListAdapter.notifyDataSetChanged();
//        } else {
            mListAdapter = new StudentsListAdapter(mStudentList, mPresenter);
            mRecyclerView.setAdapter(mListAdapter);
//        }
    }

    @Override
    public void hideLoading() {
        mLoadingSpinner.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }
}