package com.example.countriesapp.model.retrofit.countrylist;

import com.example.countriesapp.model.Country;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountryListService {
    private static final String BASE_URL = "https://raw.githubusercontent.com/";
    private static CountryListService INSTANCE;

    private CountryListApi countryListApi;


    public CountryListService() {
        /*
        *   Here we convert the retrofit response from the server to List<Country> as we declared in our CountryListApi.
        *   But this conversion must know witch variable from the server's list object correspond to each attribute of our Country model.
        *   To make it know we use annotation in our Country model class, so in that class is the annotation needed for this conversion to work.
        *
        * */
        countryListApi = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())       // make sure you added this library : 'com.squareup.retrofit2:adapter-rxjava:2.9.0'
                .build()
                .create(CountryListApi.class);
    }


    public static CountryListService getInstance(){
        if (INSTANCE == null)
            INSTANCE = new CountryListService();

        return INSTANCE;
    }

    public Single<List<Country>> getCountryList(){
        return countryListApi.getCountryList();
    }
}
