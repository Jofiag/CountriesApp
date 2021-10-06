package com.example.countriesapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.countriesapp.R;
import com.example.countriesapp.view.adapter.CountryListAdapter;
import com.example.countriesapp.viewmodel.CountryListViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressLint("NonConstantResourceId")
public class MainActivity extends AppCompatActivity {
    @BindView(R.id.country_recycler_view)
    RecyclerView countryRecyclerView;

    @BindView(R.id.list_error_message)
    TextView listErrorMessage;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout refreshLayout;

    private CountryListViewModel countryListVewModel;
    private CountryListAdapter countryListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initializeCountryListViewModel();

        showLoadingProcess();
        showCountryList();
        showEventualErrorMessage();
    }

    private void initializeCountryListViewModel(){
        countryListVewModel = new ViewModelProvider(this).get(CountryListViewModel.class);
        countryListVewModel.refresh();
    }

    private void showCountryList(){

        //Observing the country list from our view model so that ui get updated whenever the list changes
        countryListVewModel.getCountryListLiveData().observe(this, countryList -> {
            if (countryList != null){
                countryListAdapter = new CountryListAdapter(this, countryList);
                countryRecyclerView.setHasFixedSize(true);
                countryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                countryRecyclerView.setAdapter(countryListAdapter);
            }
        });
    }

    private void showEventualErrorMessage(){
        countryListVewModel.getCountryLoadError().observe(this, isError -> {
            if (isError != null && isError)
                listErrorMessage.setVisibility(View.VISIBLE);
            else
                listErrorMessage.setVisibility(View.GONE);
        });
    }

    private void showLoadingProcess(){
        countryListVewModel.getLoading().observe(this, isLoading -> {
            if (isLoading != null && isLoading) {
//                countryRecyclerView.setVisibility(View.GONE);
                listErrorMessage.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
            } else
                progressBar.setVisibility(View.GONE);
        });
    }
}