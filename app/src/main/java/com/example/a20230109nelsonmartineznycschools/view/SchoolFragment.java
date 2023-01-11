package com.example.a20230109nelsonmartineznycschools.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.a20230109nelsonmartineznycschools.R;
import com.example.a20230109nelsonmartineznycschools.databinding.FragmentSchoolBinding;
import com.example.a20230109nelsonmartineznycschools.model.Schools;
import com.example.a20230109nelsonmartineznycschools.view.adapter.OnSchoolClicked;
import com.example.a20230109nelsonmartineznycschools.view.adapter.RecyclerViewSchoolsAdapter;
import com.example.a20230109nelsonmartineznycschools.viewmodel.SchoolViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SchoolFragment extends Fragment implements OnSchoolClicked {

    FragmentSchoolBinding binding;

    RecyclerViewSchoolsAdapter rvSchoolAdapter;

    private SchoolViewModel schoolViewModel;

    public SchoolFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        schoolViewModel = new ViewModelProvider(requireActivity()).get(SchoolViewModel.class);
        rvSchoolAdapter = new RecyclerViewSchoolsAdapter(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSchoolBinding.inflate(inflater, container, false);
        binding.rvSchools.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvSchools.setAdapter(rvSchoolAdapter);

        schoolViewModel.schools.observe(getViewLifecycleOwner(), schoolsList -> {
            rvSchoolAdapter.updateSchools(schoolsList);
        });

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    /**
     * When a school is selected, opens the detail page for that school.
     * @param school the data for the school needed for the viewmodel
     */
    @Override
    public void schoolClicked(Schools school) {
        schoolViewModel.school = school;

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new DetailsFragment())
                .addToBackStack(null)
                .commit();
    }
}
