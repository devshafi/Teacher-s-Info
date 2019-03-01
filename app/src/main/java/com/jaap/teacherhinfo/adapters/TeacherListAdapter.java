package com.jaap.teacherhinfo.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jaap.teacherhinfo.R;
import com.jaap.teacherhinfo.models.Person;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class TeacherListAdapter extends RecyclerView.Adapter<TeacherListAdapter.VieHolder> {


    private Context context;
    private ArrayList<Person> userArrayList;

    public TeacherListAdapter(Context context,ArrayList<Person> userArrayList){

        this.context =  context;
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public TeacherListAdapter.VieHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View userListView = LayoutInflater.from(context).inflate(R.layout.single_person_row,viewGroup,false);
        return new VieHolder(userListView);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherListAdapter.VieHolder vieHolder, int i) {

        Person teacher = userArrayList.get(i);
        vieHolder.tvFullName.setText(teacher.getFullName());
        vieHolder.tvDesignation.setText(teacher.getDesignation());

    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    // region ViewHolder
    class VieHolder extends RecyclerView.ViewHolder{

        VieHolder(@NonNull View itemView) {
            super(itemView);
        }

        CircleImageView ivUserPhoto = itemView.findViewById(R.id.ivUserPhoto);
        AppCompatTextView tvFullName = itemView.findViewById(R.id.tvFullName);
        AppCompatTextView tvDesignation = itemView.findViewById(R.id.tvDesignation);



    }
    //endregion



}
