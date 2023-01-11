package com.example.a20230109nelsonmartineznycschools.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.a20230109nelsonmartineznycschools.RESTfulService.SchoolRepository;
import com.example.a20230109nelsonmartineznycschools.model.Schools;
import com.example.a20230109nelsonmartineznycschools.model.Scores;
import java.util.List;
import java.util.Objects;
import javax.inject.Inject;
import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class SchoolViewModel extends ViewModel {

    private static final String TAG="SchoolViewModel";

    private SchoolRepository schoolRepository;

    public @Inject SchoolViewModel(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;

        getNYCSchools();
        getSATScores();
    }

    public Schools school;
    private List<Scores> schoolScores;

    private MutableLiveData<List<Schools>> _schools = new MutableLiveData();
    public LiveData<List<Schools>> schools = _schools;

    private MutableLiveData<Scores> _scores = new MutableLiveData();
    public LiveData<Scores> scores = _scores;

    public void getScores() {
        Log.d(TAG, "getScores: " + schoolScores.toString());
        if (school != null && schoolScores != null) {
            for(int i=0; i < schoolScores.size(); i++) {
                if(Objects.equals(school.getDbn(), schoolScores.get(i).getDbn())) {
                    _scores.setValue(schoolScores.get(i));
                }
            }
        }
    }

    private void getNYCSchools() {
        schoolRepository.getAllSchools()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new SingleObserver<List<Schools>>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onSuccess(@NonNull List<Schools> schoolsList) {
                                _schools.postValue(schoolsList);
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                Log.e(TAG, "onError: ${e.message}", e);
                            }
                        }
                );
    }

    private void getSATScores() {
        schoolRepository.getAllScores()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new SingleObserver<List<Scores>>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onSuccess(@NonNull List<Scores> scores) {
                                schoolScores = scores;
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                Log.e(TAG, "onError: ${e.message}", e);
                            }
                        }
                );

    }
}
