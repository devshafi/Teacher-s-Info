package com.jaap.teacherhinfo.views.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jaap.teacherhinfo.R;
import com.jaap.teacherhinfo.models.Person;
import com.jaap.teacherhinfo.views.fragments.EditTeacherInfoFragment;

public class EditTeacherInfoActivity extends AppCompatActivity {

    // note: this activity will work as a container  for 'EditTeacherInfoFragment'
    private static final String TAG = "EditTeacherInfoActivity";
    public static final String PERSON_INTENT_KEY = "person_intent_key";
    Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_teacher_info);
        person = getIntent().getParcelableExtra(PERSON_INTENT_KEY);
        Log.d(TAG, "onCreate: person: "+person);
        loadEditTeacherInfoFragment(person);
    }

    // method for loading EditTeacherInfoFragment
    public void loadEditTeacherInfoFragment(Person person){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,EditTeacherInfoFragment.newInstance(person));
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        finish();
    }
}
