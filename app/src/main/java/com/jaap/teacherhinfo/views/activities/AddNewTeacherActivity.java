package com.jaap.teacherhinfo.views.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
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

        // connecting the views
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

        String fullName = etFullName.getText().toString();
        String designation = etDesignation.getText().toString();
        String address =  etAddress.getText().toString();
        String expertIn = etExpertIn.getText().toString();
        String email =  etEmail.getText().toString();
        String mobileNo = etMobileNo.getText().toString();

        if(TextUtils.isEmpty(fullName)){
            Toast.makeText(this, "Your fullName field is empty!", Toast.LENGTH_SHORT).show();
        }if(TextUtils.isEmpty(designation)){
            Toast.makeText(this, "Your designation field is empty!", Toast.LENGTH_SHORT).show();
        }if(TextUtils.isEmpty(address)){
            Toast.makeText(this, "Your address field is empty!", Toast.LENGTH_SHORT).show();
        }if(TextUtils.isEmpty(expertIn)){
            Toast.makeText(this, "Your expertIn field is empty!", Toast.LENGTH_SHORT).show();
        }if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Your email field is empty!", Toast.LENGTH_SHORT).show();
        }if(TextUtils.isEmpty(mobileNo)){
            Toast.makeText(this, "Your mobileNo field is empty!", Toast.LENGTH_SHORT).show();
        }else{

            // everything is fine, add teacher to database
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

