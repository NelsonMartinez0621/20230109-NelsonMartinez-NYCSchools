package com.example.a20230109nelsonmartineznycschools.RESTfulService;

import com.example.a20230109nelsonmartineznycschools.model.Schools;
import com.example.a20230109nelsonmartineznycschools.model.Scores;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface ServiceAPI {
    String END_POINT = "https://data.cityofnewyork.us/resource/";
    String highschools = "s3k6-pzi2.json";
    String SATScores = "f9bf-2cp4.json";


    @GET(highschools)
    Single<List<Schools>> getSchools();

    @GET(SATScores  )
    Single<List<Scores>> getScores();
}
