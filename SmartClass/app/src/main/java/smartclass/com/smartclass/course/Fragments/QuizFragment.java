package smartclass.com.smartclass.course.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import smartclass.com.smartclass.R;

/**
 * Created by peterpogorski on 2017-06-13.
 */

public class QuizFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.quiz_view, container, false);
    }
}
