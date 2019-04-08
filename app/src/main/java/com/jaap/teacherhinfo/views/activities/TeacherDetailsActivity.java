package com.jaap.teacherhinfo.views.activities;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.jaap.teacherhinfo.R;
import com.jaap.teacherhinfo.models.Person;

public class TeacherDetailsActivity extends AppCompatActivity {

    public static final String PERSON_INTENT_KEY = "person_intent_key";
    private static final String TAG = "TeacherDetailsActivity";
    Toolbar toolbar;
    CollapsingToolbarLayout colToolbar;
    AppCompatTextView tvFullName;
    AppCompatTextView tvDesignation;
    AppCompatTextView tvAddress;
    AppCompatTextView tvExpertIn;
    AppCompatTextView tvEmailAddress;
    AppCompatTextView tvMobile;

    Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_details_activty);
        person = getIntent().getParcelableExtra(PERSON_INTENT_KEY);
        Log.d(TAG, "onCreate: " + person);
        initialSetup();
        setSupportActionBar(toolbar);
        setValueInViews();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.activity_details_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:
                break;
            case R.id.action_delete:
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    // method for initial setup
    public void initialSetup() {

        colToolbar = findViewById(R.id.colToolbar);
        toolbar = findViewById(R.id.toolbar);
        tvFullName = findViewById(R.id.tvFullName);
        tvDesignation = findViewById(R.id.tvDesignation);
        tvAddress = findViewById(R.id.tvAddress);
        tvExpertIn = findViewById(R.id.tvExpertIn);
        tvEmailAddress = findViewById(R.id.tvEmailAddress);
        tvMobile = findViewById(R.id.tvMobile);
    }

    //method for setup value in views
    public void setValueInViews() {

        tvFullName.setText(person.getFullName());
        tvDesignation.setText(person.getDesignation());
        tvAddress.setText(person.getAddress());
        tvExpertIn.setText(person.getExpertiseIn());
        tvEmailAddress.setText(person.getEmailAddress());
        tvMobile.setText(person.getMobileNo());
    }
}
