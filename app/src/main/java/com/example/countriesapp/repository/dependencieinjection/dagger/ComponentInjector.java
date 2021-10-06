package com.example.countriesapp.repository.dependencieinjection.dagger;

import com.example.countriesapp.repository.data.remotedata.retrofit.countrylist.CountryListRemoteDataSource;
import com.example.countriesapp.viewmodel.CountryListViewModel;

import dagger.Component;

/**
 * @ComponentInjector is an interface witch is the Dagger component that will contains all functions to link our inject objects and their module
 *                       by generating the class that will allow us to use our CountryListApi without knowing how it is created.
 */
@Component(modules = {ModuleProcess.class})
public interface ComponentInjector {

    /**
     * @param countryListRemoteDataSource is the class where we are going to use our CountryListApi without letting that class know how CountryApi is created.
     */
    void injectCountryListApi(CountryListRemoteDataSource countryListRemoteDataSource);

    /**
     * @param countryListViewModel is the class where we are going to use our CountryListViewModel without letting that class know how CountryApi is created.
     */
    void injectCountryListRemoteData(CountryListViewModel countryListViewModel);
}
