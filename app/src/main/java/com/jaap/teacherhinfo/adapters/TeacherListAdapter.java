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

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class TeacherListAdapter extends RecyclerView.Adapter<TeacherListAdapter.VieHolder> {


    private Context context;
    private List<Person> userArrayList;
    private OnSingleItemSelected onSingleItemSelected;

    public TeacherListAdapter(Context context,List<Person> userArrayList, OnSingleItemSelected onSingleItemSelected){

        this.context =  context;
        this.userArrayList = userArrayList;
        this.onSingleItemSelected = onSingleItemSelected;
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onSingleItemSelected.onSingleItemSelected(userArrayList.get(getAdapterPosition()).getId());
                }
            });
        }

        CircleImageView ivUserPhoto = itemView.findViewById(R.id.ivUserPhoto);
        AppCompatTextView tvFullName = itemView.findViewById(R.id.tvFullName);
        AppCompatTextView tvDesignation = itemView.findViewById(R.id.tvDesignation);


    }
    //endregion

    // region interface for item selection
    public interface OnSingleItemSelected {
        void onSingleItemSelected(String personId);
    }
    //endregion


}
