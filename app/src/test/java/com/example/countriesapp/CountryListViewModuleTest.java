package com.example.countriesapp;

import androidx.annotation.NonNull;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.countriesapp.repository.data.remotedata.retrofit.countrylist.CountryListRemoteDataSource;
import com.example.countriesapp.repository.model.Country;
import com.example.countriesapp.viewmodel.CountryListViewModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;

@RunWith(MockitoJUnitRunner.class)
public class CountryListViewModuleTest {

    /**
     * @rule : is a rule witch indicate that every task execution is instant, that means it's not asynchronous.
     * That rule is necessary to test the task we make in the background thread through RxJava.
     * Make sure you implement this library before using that rule : 'androidx.arch.core:core-testing:2.1.0'
     */
    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Before
    public void setupRxSchedulers(){
        Scheduler immediate = new Scheduler() {
            @NonNull
            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(Runnable::run, true);
            }
        };

        RxJavaPlugins.setInitNewThreadSchedulerHandler(schedulerCallable -> immediate);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> immediate);
    }

    /**
     * @countryListRemoteDataSource : is the mock of the countryListRemoteDataSource we injected with dagger in our CountryListViewModel class
     */
    @Mock
    CountryListRemoteDataSource countryListRemoteDataSource;

    @InjectMocks
    CountryListViewModel countryListViewModel = new CountryListViewModel();

    @Test
    public void getCountryListSucceeded(){
        Country country = new Country("France", "Paris", "Flag");

        List<Country> countryList = new ArrayList<>();
        countryList.add(country);

        //@countryLoadSingleTest : is needed to test the Single<List<Country>> returned by the data source through retrofit,
        //and this is how we create an observable from an object existed.
        Single<List<Country>> countryListSingleTest = Single.just(countryList);

        //Whenever the getCountryList method of remote data is called, we return the observable countryListSingleTest we created above.
        Mockito.when(countryListRemoteDataSource.getCountryList()).thenReturn(countryListSingleTest);

        //Here is where we call the method of our view model witch use our remote data to get the list wanted by calling countryListRemoteDataSource.getCountryList()
        countryListViewModel.refresh();

        Assert.assertEquals(1, countryList.size());
        Assert.assertEquals(false, countryListViewModel.getCountryLoadError().getValue());
        Assert.assertEquals(false, countryListViewModel.getLoading().getValue());
    }

}
