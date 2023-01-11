package com.example.a20230109nelsonmartineznycschools.di;

import com.example.a20230109nelsonmartineznycschools.RESTfulService.SchoolRepository;
import com.example.a20230109nelsonmartineznycschools.RESTfulService.SchoolRepositoryImpl;
import com.example.a20230109nelsonmartineznycschools.RESTfulService.ServiceAPI;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {

    @Provides
    public ServiceAPI providesServiceApi() {
        return new Retrofit.Builder()
                .baseUrl(ServiceAPI.END_POINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(ServiceAPI.class);
    }

    @Provides
    public SchoolRepository providesSchoolRepo(ServiceAPI serviceAPI) {
        return new SchoolRepositoryImpl(serviceAPI);
    }
}
