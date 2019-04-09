package com.jaap.teacherhinfo.views.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.jaap.teacherhinfo.R;
import com.jaap.teacherhinfo.db.RealmService;
import com.jaap.teacherhinfo.models.Person;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmModel;

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

    AppCompatImageView ivCall;
    AppCompatImageView ivMessage;


    Person person;
    String personId;
    Realm realm;
    RealmService  realmService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_details);
        Log.d(TAG, "onCreate: " + person);
        initialSetup();
        setSupportActionBar(toolbar);
        setValueInViews();
        captureClickListener();

        person.addChangeListener(new RealmChangeListener<RealmModel>() {
            @Override
            public void onChange(RealmModel realmModel) {
                setValueInViews();
            }
        });

    }


    // enabling menu option in app
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.activity_details_menu, menu);
        return true;
    }


    // activating menu option
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:
                Intent intent = new Intent(TeacherDetailsActivity.this,EditTeacherInfoActivity.class);
                intent.putExtra(EditTeacherInfoActivity.PERSON_INTENT_KEY,person);
                startActivity(intent);
                break;
            case R.id.action_delete:
                AppCompatDialog dialog;
                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
                builder.setTitle(R.string.are_you_sure_you_want_to_delete);
                builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        realmService.deleteTeacherById(person.getId());
                        Toast.makeText(TeacherDetailsActivity.this, getString(R.string.teacher_deleted_successfully), Toast.LENGTH_SHORT).show();
                        dialogInterface.dismiss();
                        finish();
                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                dialog = builder.create();
                dialog.show();

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
        ivCall =  findViewById(R.id.ivCall);
        ivMessage = findViewById(R.id.ivMessage);

        realm = Realm.getDefaultInstance();
        realmService = new RealmService(realm);
        personId = getIntent().getStringExtra(PERSON_INTENT_KEY);
        person = realmService.getASingleTeacher(personId);

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

    //method for capturing click listener
    public void captureClickListener(){

        ivCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeCall(person.getMobileNo());
            }
        });

        ivMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendSMS(person.getMobileNo());
            }
        });
    }

    // method for making a call
    public void makeCall(String number){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+number));
        startActivity(intent);
    }

    // method for sending sms
    public void sendSMS(String number){

        Uri uri = Uri.parse("smsto:"+number);
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        startActivity(intent);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
