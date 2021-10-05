package com.example.countriesapp.model.retrofit.countrylist;

import com.example.countriesapp.model.Country;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface CountryListApi {

    /*
    *   Single is an observer provided by RxJava witch observe the data we are going to get.
    *   The @GET is a retrofit annotation witch allow specify that a method is for getting data from a web server.
    *   The "DevTides/..." is the endpoint of the server url, that way if we want to get a data from the same service but with another endpoint,
    *   we just have to create another @GET method and specify that endpoint.
    *
    *   Notice that we could also pass the url endpoint as a parameter of the function instead of putting it the parentheses just after the @GET:
    *       @GET
    *       Single<Object> getObject(@Url String urlEndpoint);
    *       We often use that method when we don't know the url while implementing the function.
    *       But both way work just fine.
    *
    *   IMPORTANT : THE TYPE OF DATA INSIDE DE SINGLE MUST BE THE SAME THAT THE SERVER RETURN OTHERWISE WE GET ERRORS
    * */

    @GET("DevTides/countries/master/countriesV2.json")
    Single<List<Country>> getCountryList();
}
