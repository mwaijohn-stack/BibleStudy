package fragments;


import android.content.Context;
import android.content.SharedPreferences;
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
import com.google.android.material.snackbar.Snackbar;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import utilities.SharedPref;


/**
 * A simple {@link Fragment} subclass.
 */
public class BioFragment extends Fragment implements BlockingStep {
    EditText f_name,middle_name,last_name,registration_no,phone_no;
    MaterialSpinner gender;
    SharedPreferences.Editor editor;
    public BioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_bio, container, false);
        MaterialSpinner spinner = (MaterialSpinner) root.findViewById(R.id.spinner);

//        SharedPreferences pref = getContext().getSharedPreferences(getActivity().getPackageName(), Context.MODE_PRIVATE); // 0 - for private mode
//        editor = pref.edit();

        String[] gender = {"Male","Female"};
        spinner.setItems(gender);
        SharedPref.write(SharedPref.GENDER, "Male");

//        editor.putString(SharedPref.GENDER,"Male");
//        editor.apply();
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
               SharedPref.write(SharedPref.GENDER,gender[position]);
//                editor.putString(SharedPref.GENDER,gender[position]);
//                editor.apply();
            }
        });






        f_name = root.findViewById(R.id.input_first_name);
        middle_name = root.findViewById(R.id.input_middle_name);
        last_name = root.findViewById(R.id.input_last_name);
        registration_no = root.findViewById(R.id.input_reg);
        phone_no = root.findViewById(R.id.input_phone);


        return root;
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


        if (f_name.getText().toString().isEmpty() || middle_name.getText().toString().isEmpty()
                || last_name.getText().toString().isEmpty() || registration_no.getText().toString().isEmpty()
        || phone_no.getText().toString().isEmpty()){


            callback.goToNextStep();

        }else {

            //f_name.getText().toString()

            SharedPref.write(SharedPref.FIRST_NAME, "first name");
            SharedPref.write(SharedPref.SECOND_NAME, middle_name.getText().toString());
            SharedPref.write(SharedPref.LAST_NAME, last_name.getText().toString());
            SharedPref.write(SharedPref.REG_NUMBER, registration_no.getText().toString());
            SharedPref.write(SharedPref.PHONE_NUMBER, phone_no.getText().toString());

//            editor.putString(SharedPref.FIRST_NAME, "first name");
//            editor.putString(SharedPref.SECOND_NAME, middle_name.getText().toString());
//            editor.putString(SharedPref.LAST_NAME, last_name.getText().toString());
//            editor.putString(SharedPref.REG_NUMBER, registration_no.getText().toString());
//            editor.putString(SharedPref.PHONE_NUMBER, phone_no.getText().toString());
//
//            editor.apply();
//            editor.commit();

            callback.goToNextStep();


            //Log.d("prefs", Sh(SharedPref.FIRST_NAME,"no value"));


        }
    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {

    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
    }
}
