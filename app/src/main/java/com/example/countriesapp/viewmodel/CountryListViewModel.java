package com.example.countriesapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.countriesapp.model.Country;

import java.util.ArrayList;
import java.util.List;

public class CountryListViewModel extends ViewModel {
    private MutableLiveData<List<Country>> countryListLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> countryLoadError = new MutableLiveData<>();
    private MutableLiveData<Boolean> loading = new MutableLiveData<>();

    public void refresh(){
        fetchCountries();
    }

    private void fetchCountries(){
        Country benin = new Country("Benin", "Porto-Novo", "");
        Country france = new Country("France", "Paris", "");
        Country unitedEmirates = new Country("United Arab Emirates", "Abou Dabi", "");

        List<Country> countryList = new ArrayList<>();
        countryList.add(benin);
        countryList.add(france);
        countryList.add(unitedEmirates);
        countryList.add(benin);
        countryList.add(france);
        countryList.add(unitedEmirates);
        countryList.add(benin);
        countryList.add(france);
        countryList.add(unitedEmirates);
        countryList.add(benin);
        countryList.add(france);
        countryList.add(unitedEmirates);

        countryListLiveData.setValue(countryList);
        countryLoadError.setValue(false);
        loading.setValue(false);
    }

    public MutableLiveData<List<Country>> getCountryListLiveData() {
        return countryListLiveData;
    }

    public MutableLiveData<Boolean> getCountryLoadError() {
        return countryLoadError;
    }

    public MutableLiveData<Boolean> getLoading() {
        return loading;
    }
}
