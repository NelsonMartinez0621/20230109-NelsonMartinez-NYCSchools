package com.example.a20230109nelsonmartineznycschools.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a20230109nelsonmartineznycschools.databinding.SchoolViewBinding;
import com.example.a20230109nelsonmartineznycschools.model.Schools;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewSchoolsAdapter extends RecyclerView.Adapter<RecyclerViewSchoolsAdapter.ViewHolder> {

    private List<Schools> schoolsList;
    private OnSchoolClicked onSchoolClicked;

    public RecyclerViewSchoolsAdapter(OnSchoolClicked onSchoolClicked) {
        this.onSchoolClicked = onSchoolClicked;
        schoolsList = new ArrayList<>();
    }

    public void updateSchools(List<Schools> newSchools) {
        schoolsList.clear();
        schoolsList.addAll(newSchools);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerViewSchoolsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(SchoolViewBinding.inflate(LayoutInflater.from(parent.getContext()), parent,  false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewSchoolsAdapter.ViewHolder holder, int position) {
        holder.onBind(schoolsList.get(position), onSchoolClicked);
    }

    @Override
    public int getItemCount() {
        return schoolsList.size();
    }

    /**
     * ViewHolder class for the School RecyclerView
     */
    public static class ViewHolder extends RecyclerView.ViewHolder{
        SchoolViewBinding schoolViewBinding;

        public ViewHolder(SchoolViewBinding schoolViewBinding) {
            super(schoolViewBinding.getRoot());
            this.schoolViewBinding = schoolViewBinding;
        }

        /**
         * Sets that actions for the onBindViewholder override
         * @param school The data for the school for binding.
         * @param onSchoolClicked sets the onClick function.
         */
        public void onBind(Schools school, OnSchoolClicked onSchoolClicked) {
            schoolViewBinding.tvSchoolName.setText(school.getSchoolName());

            schoolViewBinding.getRoot().setOnClickListener(view -> {
                // this click moves to the details fragment
                onSchoolClicked.schoolClicked(school);
            });
        }
    }
}
