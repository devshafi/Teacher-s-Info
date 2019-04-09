package com.jaap.teacherhinfo.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.jaap.teacherhinfo.R;
import com.jaap.teacherhinfo.adapters.TeacherListAdapter;
import com.jaap.teacherhinfo.db.RealmService;
import com.jaap.teacherhinfo.models.Person;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";

    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;

    RecyclerView rvTeacherList;
    TeacherListAdapter teacherListAdapter;
    FloatingActionButton fabAddTeacher;
    AppCompatTextView tvNoTeacherFound;
    RealmResults<Person> teacherList;

    Realm realm;
    RealmService realmService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialSetup();
        initAdapter();
        reflectChangesInTeacherList();
        captureClickListener();
        toggleRecyclerViewVisibility();

    }

    // method for initializing components
    public void initialSetup(){

        //init views
        fabAddTeacher = findViewById(R.id.fabAddTeacher);
        tvNoTeacherFound = findViewById(R.id.tvNoTeacherFound);
        rvTeacherList = findViewById(R.id.rvTeacherList);

        // drawer setup
        mDrawer = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        drawerToggle = setupDrawerToggle();
        mDrawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        // initializing realmService
        realm = Realm.getDefaultInstance();
        realmService = new RealmService(realm);
    }

    // method for getting drawer toggle
    private ActionBarDrawerToggle setupDrawerToggle() {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open,  R.string.drawer_close);

    }

    // method for showing or hiding recyclerView
    public void toggleRecyclerViewVisibility(){

        if(teacherList.isEmpty()){
            rvTeacherList.setVisibility(View.GONE);
            tvNoTeacherFound.setVisibility(View.VISIBLE);
        }else{
            tvNoTeacherFound.setVisibility(View.GONE);
            rvTeacherList.setVisibility(View.VISIBLE);
        }
    }

    // method for reflecting change in teacher list
    public void reflectChangesInTeacherList(){

        teacherList.addChangeListener(new RealmChangeListener<RealmResults<Person>>() {
            @Override
            public void onChange(RealmResults<Person> teachers) {
                Log.d(TAG, "onChange: "+teachers.size());
                teacherListAdapter.notifyDataSetChanged();
                toggleRecyclerViewVisibility();
            }
        });
    }

    // method for initializing adapter
    public void initAdapter(){

        // fetching all the teacher from database
        teacherList = (RealmResults<Person>) realmService.getAllTeacher();
        teacherListAdapter = new TeacherListAdapter(this, teacherList, new TeacherListAdapter.OnSingleItemSelected() {
            @Override
            public void onSingleItemSelected(String personId) {
                Intent intent = new Intent(MainActivity.this,TeacherDetailsActivity.class);
                intent.putExtra(TeacherDetailsActivity.PERSON_INTENT_KEY,personId);
                startActivity(intent);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvTeacherList.setLayoutManager(linearLayoutManager);
        rvTeacherList.setAdapter(teacherListAdapter);
    }

    // method for capturing listeners
    public void captureClickListener(){

        fabAddTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,AddNewTeacherActivity.class));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
