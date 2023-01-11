package com.example.a20230109nelsonmartineznycschools;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.a20230109nelsonmartineznycschools.databinding.ActivityMainBinding;
import com.example.a20230109nelsonmartineznycschools.view.SchoolFragment;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportFragmentManager()
                .beginTransaction()
                .replace(binding.fragmentContainer.getId(), new SchoolFragment())
                .commit();

    }
}