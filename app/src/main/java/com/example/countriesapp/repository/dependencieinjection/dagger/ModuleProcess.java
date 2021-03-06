package com.example.countriesapp.repository.dependencieinjection.dagger;

import com.example.countriesapp.repository.data.remotedata.retrofit.countrylist.CountryListApi;
import com.example.countriesapp.repository.data.remotedata.retrofit.countrylist.CountryListRemoteDataSource;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @ModuleProcess is a dagger module class to instantiate our inject objects classes.
 */
@Module
public class ModuleProcess {

    /**
     * @return a CountryListApi instance.
     */
    @Provides
    public CountryListApi provideCountryListApi(){
        String BASE_URL = "https://raw.githubusercontent.com/";

        /*
         *   Here we convert the retrofit response from the server to List<Country> as we declared in our CountryListApi.
         *   But this conversion must know witch variable from the server's list object correspond to each attribute of our Country model.
         *   To make it know we use annotation in our Country model class, so in that class is the annotation needed for this conversion to work.
         *
         * */
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())       // make sure you added this library : 'com.squareup.retrofit2:adapter-rxjava2:2.9.0'
                .build()
                .create(CountryListApi.class);
    }

    /**
     * @return a CountryListRemoteDataSource instance.
     */
    @Provides
    public CountryListRemoteDataSource provideCountryListRemoteData(){
        return CountryListRemoteDataSource.getInstance();
    }
}
