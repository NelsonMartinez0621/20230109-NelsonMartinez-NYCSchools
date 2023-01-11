package com.example.a20230109nelsonmartineznycschools.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a20230109nelsonmartineznycschools.R;
import com.example.a20230109nelsonmartineznycschools.databinding.FragmentDetailsBinding;
import com.example.a20230109nelsonmartineznycschools.model.Schools;
import com.example.a20230109nelsonmartineznycschools.viewmodel.SchoolViewModel;

public class DetailsFragment extends Fragment {

    private static final String TAG = "DetailsFragment";

    private SchoolViewModel schoolViewModel;
    FragmentDetailsBinding binding;

    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        schoolViewModel = new ViewModelProvider(requireActivity()).get(SchoolViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentDetailsBinding.inflate(inflater, container, false);
        populateSchoolDetails();
        schoolViewModel.scores.observe(getViewLifecycleOwner(), scores -> {
            if (scores != null) {
                binding.tvNumTestTakers.setText(String.format("%s %s",getString(R.string.num_test_takers), scores.getNumOfSatTestTakers()));
                binding.tvMathScore.setText(String.format("%s %s",getString(R.string.math_score), scores.getSatMathAvgScore()));
                binding.tvReadingScore.setText(String.format("%s %s",getString(R.string.reading_score), scores.getSatCriticalReadingAvgScore()));
                binding.tvWritingScore.setText(String.format("%s %s",getString(R.string.writing_score), scores.getSatWritingAvgScore()));
            } else {
                binding.tvNumTestTakers.setText(getString(R.string.results_unavailable));
                binding.tvMathScore.setText(getString(R.string.results_unavailable));
                binding.tvReadingScore.setText(getString(R.string.results_unavailable));
                binding.tvWritingScore.setText(getString(R.string.results_unavailable));
            }
        });

        schoolViewModel.getScores();

        return binding.getRoot();
    }

    /**
     * Populates only school data for separation of concerns
     */
    private void populateSchoolDetails() {
        Schools school = schoolViewModel.school;
        binding.tvSchoolName.setText(school.getSchoolName());
        binding.tvOverviewParagraph.setText(school.getOverviewParagraph());
        binding.address.setText(String.format("%s: %s, %s, %s, %s", getString(R.string.address), school.getPrimaryAddressLine1(), school.getCity(), school.getStateCode(), school.getZip()));
        binding.email.setText(String.format("%s: %s", getString(R.string.email), school.getSchoolEmail()));
        binding.phone.setText(String.format("%s: %s", getString(R.string.phone), school.getPhoneNumber()));
    }
}