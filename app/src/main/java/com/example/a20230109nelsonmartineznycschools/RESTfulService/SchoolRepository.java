package com.example.a20230109nelsonmartineznycschools.RESTfulService;

import com.example.a20230109nelsonmartineznycschools.model.Schools;
import com.example.a20230109nelsonmartineznycschools.model.Scores;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface SchoolRepository {
    Single<List<Schools>> getAllSchools();
    Single<List<Scores>> getAllScores();
}
