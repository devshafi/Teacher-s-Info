package com.jaap.teacherhinfo.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.jaap.teacherhinfo.R;
import com.jaap.teacherhinfo.adapters.TeacherListAdapter;
import com.jaap.teacherhinfo.models.Person;
import com.jaap.teacherhinfo.utils.GeneratePersonList;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;

    RecyclerView rvTeacherList;
    TeacherListAdapter teacherListAdapter;
    FloatingActionButton fabAddTeacher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(toolbar);

        //init views
        fabAddTeacher = findViewById(R.id.fabAddTeacher);

        // drawer setup
        mDrawer = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        drawerToggle = setupDrawerToggle();
        mDrawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        //setting adapter with recycler view
        rvTeacherList = findViewById(R.id.rvTeacherList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        ArrayList<Person> teacherList = GeneratePersonList.getPersonList();
        teacherListAdapter = new TeacherListAdapter(this,teacherList);
        rvTeacherList.setLayoutManager(linearLayoutManager);
        rvTeacherList.setAdapter(teacherListAdapter);


        fabAddTeacher.setOnClickListener(v->{

            startActivity(new Intent(this,AddNewTeacherActivity.class));

        });

    }

    // method for getting drawer toggle
    private ActionBarDrawerToggle setupDrawerToggle() {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open,  R.string.drawer_close);

    }
}
