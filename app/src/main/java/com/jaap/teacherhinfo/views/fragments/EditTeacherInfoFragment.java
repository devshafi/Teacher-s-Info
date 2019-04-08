package com.jaap.teacherhinfo.views.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
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

    public EditTeacherInfoFragment() {
        // Required empty public constructor
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

        // initializing the view
        etFullName = view.findViewById(R.id.etFullName);
        etDesignation = view.findViewById(R.id.etDesignation);
        etAddress = view.findViewById(R.id.etAddress);
        etExpertIn = view.findViewById(R.id.etExpertIn);
        etEmail = view.findViewById(R.id.etEmail);
        etMobileNo = view.findViewById(R.id.etMobileNo);
        btUpdateInfo = view.findViewById(R.id.btAddTeacher);

        // initializing realm
        realm = Realm.getDefaultInstance();
        realmService = new RealmService(realm);

        
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
            Toast.makeText(getContext(), "Your fullName field is empty!", Toast.LENGTH_SHORT).show();
        }if(TextUtils.isEmpty(designation)){
            Toast.makeText(getContext(), "Your designation field is empty!", Toast.LENGTH_SHORT).show();
        }if(TextUtils.isEmpty(address)){
            Toast.makeText(getContext(), "Your address field is empty!", Toast.LENGTH_SHORT).show();
        }if(TextUtils.isEmpty(expertIn)){
            Toast.makeText(getContext(), "Your expertIn field is empty!", Toast.LENGTH_SHORT).show();
        }if(TextUtils.isEmpty(email)){
            Toast.makeText(getContext(), "Your email field is empty!", Toast.LENGTH_SHORT).show();
        }if(TextUtils.isEmpty(mobileNo)){
            Toast.makeText(getContext(), "Your mobileNo field is empty!", Toast.LENGTH_SHORT).show();
        }else{

            // everything is fine, add teacher to database
            Person person = new Person(fullName,designation,address,expertIn,email,mobileNo);

            realmService.addNewTeacher(person);
            Toast.makeText(getContext(), getContext().getString(R.string.new_teacher_added_successfully), Toast.LENGTH_SHORT).show();

        }
    }
}
