package com.jaap.teacherhinfo.views.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jaap.teacherhinfo.R;
import com.jaap.teacherhinfo.db.RealmService;
import com.jaap.teacherhinfo.models.Person;

import io.realm.Realm;

public class EditTeacherInfoFragment extends Fragment {


    private static final String TAG = "EditTeacherInfoFragment";

    // declaring the views
    AppCompatEditText etFullName;
    AppCompatEditText etDesignation;
    AppCompatEditText etAddress;
    AppCompatEditText etExpertIn;
    AppCompatEditText etEmail;
    AppCompatEditText etMobileNo;
    AppCompatButton btUpdateInfo;

    Realm realm;
    RealmService realmService;

    Person personFromDb;

    public EditTeacherInfoFragment() {
        // Required empty public constructor
    }

    public static EditTeacherInfoFragment newInstance(Person person) {
        Bundle args = new Bundle();
        args.putParcelable("personFromDb",person);
        EditTeacherInfoFragment fragment = new EditTeacherInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_teacher_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        personFromDb = getArguments().getParcelable("personFromDb");
        Log.d(TAG, "onViewCreated: personFromDb: "+ personFromDb);

        initialSetup(view);
        setValuesInViews();
        captureClickListener();

        
    }

    // method for initial setup
    public void initialSetup(View view){

        // initializing the view
        etFullName = view.findViewById(R.id.etFullName);
        etDesignation = view.findViewById(R.id.etDesignation);
        etAddress = view.findViewById(R.id.etAddress);
        etExpertIn = view.findViewById(R.id.etExpertIn);
        etEmail = view.findViewById(R.id.etEmail);
        etMobileNo = view.findViewById(R.id.etMobileNo);
        btUpdateInfo = view.findViewById(R.id.btUpdateInfo);

        // initializing realm
        realm = Realm.getDefaultInstance();
        realmService = new RealmService(realm);


    }

    //method for setting value iin views
    public void setValuesInViews(){

        etFullName.setText(personFromDb.getFullName());
        etDesignation.setText(personFromDb.getDesignation());
        etAddress.setText(personFromDb.getAddress());
        etExpertIn.setText(personFromDb.getExpertiseIn());
        etEmail.setText(personFromDb.getEmailAddress());
        etMobileNo.setText(personFromDb.getMobileNo());
    }

    // method for validating user info
    // if any field is empty let user know it
    public void validateUserData(){

        String fullName = etFullName.getText().toString().trim();
        String designation = etDesignation.getText().toString().trim();
        String address =  etAddress.getText().toString().trim();
        String expertIn = etExpertIn.getText().toString().trim();
        String email =  etEmail.getText().toString().trim();
        String mobileNo = etMobileNo.getText().toString().trim();

        if(TextUtils.isEmpty(fullName)){
            etFullName.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_danger));
        }else{
            etFullName.setBackgroundDrawable(getResources().getDrawable(R.drawable.border));
        }
        if(TextUtils.isEmpty(designation)){
            etDesignation.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_danger));
        }else{
            etDesignation.setBackgroundDrawable(getResources().getDrawable(R.drawable.border));
        }
        if(TextUtils.isEmpty(address)){
            etAddress.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_danger));
        }else{
            etAddress.setBackgroundDrawable(getResources().getDrawable(R.drawable.border));
        }
        if(TextUtils.isEmpty(expertIn)){
            etExpertIn.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_danger));
        }else{
            etExpertIn.setBackgroundDrawable(getResources().getDrawable(R.drawable.border));
        }
        if(TextUtils.isEmpty(email)){
            etEmail.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_danger));
        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){

            etEmail.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_danger));
            Toast.makeText(getContext(), getString(R.string.invalid_email_foramt), Toast.LENGTH_SHORT).show();
        }
        else{
            etEmail.setBackgroundDrawable(getResources().getDrawable(R.drawable.border));
        }
        if(TextUtils.isEmpty(mobileNo)){
            etMobileNo.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_danger));
        }else{
            etMobileNo.setBackgroundDrawable(getResources().getDrawable(R.drawable.border));
        }

        // show one or more fields are empty
        if(TextUtils.isEmpty(fullName) || TextUtils.isEmpty(designation) || TextUtils.isEmpty(address)
                || TextUtils.isEmpty(expertIn) || TextUtils.isEmpty(email) || TextUtils.isEmpty(mobileNo)){
            Toast.makeText(getContext(), getString(R.string.oner_or_more_fields_are_empty), Toast.LENGTH_SHORT).show();
        }

        // everything is fine, add teacher to database
        if(!TextUtils.isEmpty(fullName) && !TextUtils.isEmpty(designation) && !TextUtils.isEmpty(address)
                && !TextUtils.isEmpty(expertIn) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(mobileNo)
                && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Person person = new Person(fullName,designation,address,expertIn,email,mobileNo);
            person.setId(personFromDb.getId());
            realmService.updateTeacher(person);
            Toast.makeText(getContext(),getString(R.string.info_updated_successully), Toast.LENGTH_SHORT).show();
            getActivity().finish();
        }
    }

    //method for capturing click listener
    public void captureClickListener(){

        btUpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateUserData();
            }
        });
    }
}
