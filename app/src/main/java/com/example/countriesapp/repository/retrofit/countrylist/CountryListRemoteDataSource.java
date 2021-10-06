package com.example.countriesapp.repository.retrofit.countrylist;

import com.example.countriesapp.model.Country;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountryListRemoteDataSource {
    private static final String BASE_URL = "https://raw.githubusercontent.com/";
    private static CountryListRemoteDataSource INSTANCE;

    private final CountryListApi countryListApi;


    public CountryListRemoteDataSource() {
        /*
        *   Here we convert the retrofit response from the server to List<Country> as we declared in our CountryListApi.
        *   But this conversion must know witch variable from the server's list object correspond to each attribute of our Country model.
        *   To make it know we use annotation in our Country model class, so in that class is the annotation needed for this conversion to work.
        *
        * */

        countryListApi = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())       // make sure you added this library : 'com.squareup.retrofit2:adapter-rxjava2:2.9.0'
                .build()
                .create(CountryListApi.class);
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
