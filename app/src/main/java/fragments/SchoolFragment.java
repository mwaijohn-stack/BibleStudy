package fragments;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.biblestudy.R;
import com.biblestudy.RegisterActivity;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SchoolFragment extends Fragment implements BlockingStep {
    MaterialSpinner university,campus,school,course,hostel;
    EditText input_hostel_no;

    public SchoolFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_school, container, false);

        university =  root.findViewById(R.id.university);
        campus =  root.findViewById(R.id.campus);
        course =  root.findViewById(R.id.course);
        school =  root.findViewById(R.id.school);
        hostel =  root.findViewById(R.id.hostel);

        input_hostel_no = root.findViewById(R.id.input_hostel_no);


        RegisterActivity registerActivity = (RegisterActivity) getActivity();
        ArrayList<String> university_data = registerActivity.getUniversities();

        Log.d("university_size",String.valueOf(university_data.size()));
        if (university_data != null){
            university.setItems(university_data);
        }

        return root;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        RegisterActivity registerActivity = (RegisterActivity) getActivity();
        ArrayList<String> university_data = registerActivity.getUniversities();

        Log.d("university_size",String.valueOf(university_data.size()));
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        return null;
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }

    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {
        callback.goToNextStep();
    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {

    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
        callback.goToPrevStep();
    }
}
