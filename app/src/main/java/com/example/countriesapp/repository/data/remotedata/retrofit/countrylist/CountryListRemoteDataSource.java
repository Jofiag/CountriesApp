package com.example.countriesapp.repository.data.remotedata.retrofit.countrylist;

import com.example.countriesapp.repository.dependencieinjection.dagger.countrylistinjection.DaggerCountryListApiComponent;
import com.example.countriesapp.repository.model.Country;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountryListRemoteDataSource {
    private static CountryListRemoteDataSource INSTANCE;

    @Inject
    public CountryListApi countryListApi;


    private CountryListRemoteDataSource() {
        //Using the dagger generated class to tell our CountryListRemoteDataSource how to use CountryListApi
        DaggerCountryListApiComponent.create().inject(this);
    }


    public static CountryListRemoteDataSource getInstance(){
        if (INSTANCE == null)
            INSTANCE = new CountryListRemoteDataSource();

        return INSTANCE;
    }

    public Single<List<Country>> getCountryList(){
        return countryListApi.getCountryList();
    }
}
