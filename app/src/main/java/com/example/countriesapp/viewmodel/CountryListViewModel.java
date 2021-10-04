package com.example.countriesapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.countriesapp.model.Country;

import java.util.ArrayList;
import java.util.List;

public class CountryListViewModel extends ViewModel {
    public MutableLiveData<List<Country>> countryListLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> countryLoadError = new MutableLiveData<>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<>();

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

        countryListLiveData.setValue(countryList);
        countryLoadError.setValue(false);
        loading.setValue(false);
    }

}
