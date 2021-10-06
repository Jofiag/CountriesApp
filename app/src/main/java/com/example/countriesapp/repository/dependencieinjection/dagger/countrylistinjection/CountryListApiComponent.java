package com.example.countriesapp.repository.dependencieinjection.dagger.countrylistinjection;


import com.example.countriesapp.repository.data.remotedata.retrofit.countrylist.CountryListRemoteDataSource;

import dagger.Component;

/**
 * @CountryListViewModel is an interface witch is the Dagger component to link our CountryListApi and CountryListApiModule
 *                       by generating the class that will allow us to use our CountryListApi without knowing how it is created.
 */
@Component(modules = {CountryListApiModule.class})
public interface CountryListApiComponent {

    /**
     * @param countryListRemoteDataSource is the class where we are going to use our CountryListApi without letting that class know how CountryApi is created.
     */
    void inject(CountryListRemoteDataSource countryListRemoteDataSource);
}
