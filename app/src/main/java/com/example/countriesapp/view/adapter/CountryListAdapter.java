package com.example.countriesapp.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.countriesapp.R;
import com.example.countriesapp.model.Country;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.CountryViewHolder> {
    private List<Country> countryList = new ArrayList<>();
    private Context context;

    public CountryListAdapter(List<Country> countryList, Context context) {
        this.countryList = countryList;
        this.context = context;
    }

    public void updateCountryList(List<Country> newList){
//        this.countryList = newList;
        this.countryList.clear();
        this.countryList.addAll(newList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_row, parent,false);

        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        Country country = countryList.get(position);

        if (country != null){
            holder.countryName.setText(country.getName());
            holder.countryCapital.setText(country.getCapital());
        }

    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    @SuppressLint("NonConstantResourceId")
    public static class CountryViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.country_flag_image_view)
        ImageView countryFlag;

        @BindView(R.id.country_name)
        TextView countryName;

        @BindView(R.id.country_capital)
        TextView countryCapital;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }


    }
}
