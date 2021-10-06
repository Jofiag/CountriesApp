package com.example.countriesapp.repository.data.remotedata.retrofit.countrylist;

import com.example.countriesapp.repository.dependencieinjection.dagger.DaggerComponentInjector;
import com.example.countriesapp.repository.model.Country;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class CountryListRemoteDataSource {
    private static CountryListRemoteDataSource INSTANCE;

    @Inject
    public CountryListApi countryListApi;


    private CountryListRemoteDataSource() {
        //Using the dagger generated class to tell our CountryListRemoteDataSource how to use CountryListApi
        DaggerComponentInjector.create().injectCountryListApi(this);
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
