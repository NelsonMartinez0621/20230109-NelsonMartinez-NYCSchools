package com.example.a20230109nelsonmartineznycschools.RESTfulService;

import com.example.a20230109nelsonmartineznycschools.model.Schools;
import com.example.a20230109nelsonmartineznycschools.model.Scores;
import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class SchoolRepositoryImpl implements SchoolRepository {

    private ServiceAPI serviceAPI;

    public SchoolRepositoryImpl(ServiceAPI serviceAPI) {
        this.serviceAPI = serviceAPI;
    }

    @Override
    public Single<List<Schools>> getAllSchools() {
        return serviceAPI.getSchools();
    }

    @Override
    public Single<List<Scores>> getAllScores() {
        return serviceAPI.getScores();
    }
}
