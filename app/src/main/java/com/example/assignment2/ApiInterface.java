package com.example.assignment2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("search")
    Call<ResponsePojo> getSongs(@Query("term") String term, @Query("media") String media, @Query("entity") String entity, @Query("limit") int limit);

}
