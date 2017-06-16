package smartclass.com.smartclass.classroom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import smartclass.com.smartclass.R;

/**
 * Created by kevinT on 2017-06-15.
 */

public class StudentsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.students_view, container, false);
    }
}
