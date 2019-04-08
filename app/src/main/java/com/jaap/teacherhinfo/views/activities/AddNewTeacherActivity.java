package com.jaap.teacherhinfo.views.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.jaap.teacherhinfo.R;
import com.jaap.teacherhinfo.db.RealmService;
import com.jaap.teacherhinfo.models.Person;

import io.realm.Realm;

public class AddNewTeacherActivity extends AppCompatActivity {


    // declaring the views
    AppCompatEditText etFullName;
    AppCompatEditText etDesignation;
    AppCompatEditText etAddress;
    AppCompatEditText etExpertIn;
    AppCompatEditText etEmail;
    AppCompatEditText etMobileNo;
    AppCompatButton btAddTeacher;

    Realm realm;
    RealmService realmService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_teacher);

        // initializing the views
        etFullName = findViewById(R.id.etFullName);
        etDesignation = findViewById(R.id.etDesignation);
        etAddress = findViewById(R.id.etAddress);
        etExpertIn = findViewById(R.id.etExpertIn);
        etEmail = findViewById(R.id.etEmail);
        etMobileNo = findViewById(R.id.etMobileNo);
        btAddTeacher = findViewById(R.id.btAddTeacher);

        // initializing realm service
        realm = Realm.getDefaultInstance();
        realmService = new RealmService(realm);


        // setting click listener in add teacher button
        btAddTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateUserData();
            }
        });

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
            Toast.makeText(this, getString(R.string.invalid_email_foramt), Toast.LENGTH_SHORT).show();
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
            Toast.makeText(this, getString(R.string.oner_or_more_fields_are_empty), Toast.LENGTH_SHORT).show();
        }

        // everything is fine, add teacher to database
        if(!TextUtils.isEmpty(fullName) && !TextUtils.isEmpty(designation) && !TextUtils.isEmpty(address)
        && !TextUtils.isEmpty(expertIn) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(mobileNo)
        && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Person person = new Person(fullName,designation,address,expertIn,email,mobileNo);
            realmService.addNewTeacher(person);
            Toast.makeText(this, getString(R.string.new_teacher_added_successfully), Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}

