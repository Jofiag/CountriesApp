package com.example.countriesapp.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.countriesapp.model.Country;
import com.example.countriesapp.repository.retrofit.countrylist.CountryListRemoteDataSource;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


/**
 * @CountryListViewModel : The ViewModel class on witch depend the UI to get the data, since we are using MVVM architecture.
 *
 * @countryListRemoteDataSource : The data source we used in our repository directory to get the data needed.
 *                                That data is just the list of all country.
 *
 * @countryListLiveDate : The live data that will contains the data gotten from our data source.
 *                        That data is going to be observed in our MainActivity by the UI
 *                        witch is going to be updated whenever the data changes.
 *
 * @countryLoadError : A boolean witch is true if there is an error while getting the data and false if there isn't,
 *                     so that we can inform the user that there was an error while getting the data.
 *
 * @loading : A boolean witch is true if the fetching of the data is not finished yet and false if it is,
 *            so that we can display to the user a progress bar to inform that the getting data is in progress.
 *
 * @compositeDisposable : An RxJava CompositeDisposable that is going to prevent the getting data process (on a background thread) to still
 *                        when the user close the app, receive a call, ...
 *                        In the onCleared override method witch is called when the activity is destroy, we clear the compositeDisposable.
 *                        By doing so we prevent memory leak.
 */
public class CountryListViewModel extends ViewModel {
    private /*final*/ CountryListRemoteDataSource countryListRemoteDataSource = CountryListRemoteDataSource.getInstance();
    private /*final*/ CompositeDisposable compositeDisposable = new CompositeDisposable();

    private /*final*/ MutableLiveData<List<Country>> countryListLiveData = new MutableLiveData<>();
    private /*final*/ MutableLiveData<Boolean> countryLoadError = new MutableLiveData<>();
    private /*final*/ MutableLiveData<Boolean> loading = new MutableLiveData<>();



    public void refresh(){
        fetchCountryList();
    }

    private void fetchCountryList(){
        /*Country benin = new Country("Benin", "Porto-Novo", "");
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
        loading.setValue(false);*/

        loading.setValue(true);
        compositeDisposable.add(

                countryListRemoteDataSource.getCountryList()
                .subscribeOn(Schedulers.newThread())                //make the task on a new thread
                .observeOn(AndroidSchedulers.mainThread())          //when the task is finish send result on the main thread
                .subscribeWith(new DisposableSingleObserver<List<Country>>() {

                    @Override
                    public void onSuccess(@NonNull List<Country> countryList) {
                        countryListLiveData.setValue(countryList);
                        countryLoadError.setValue(false);
                        loading.setValue(false);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        countryLoadError.setValue(true);
                        loading.setValue(false);
                        e.printStackTrace();    //show the error in the logcat
                    }
                })

        );
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

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
