package io.CatListGovor.cat_api;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;


import static android.content.ContentValues.TAG;

public class RetrofitTheCatAPI implements TheCatAPI {

    interface RetrofitCatService {
        @GET("images/get?format=xml&results_per_page=30")
        @Headers(value = "name: 53bfc559-4dda-43cd-9e87-c75837dd6d56")
        Call<CatImagesModel> ListCats();
    }
    //http://thecatapi.com/api/images/get?format=xml&limit=3&page=100&order=DESC
    @Override
    public void GetCats(final Callback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://thecatapi.com/api/")
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        RetrofitCatService retrofitCatService = retrofit.create(RetrofitCatService.class);
        retrofitCatService.ListCats().enqueue(new retrofit2.Callback<CatImagesModel>() {
            @Override
            public void onResponse(Call<CatImagesModel> call, Response<CatImagesModel> response) {
                callback.response(response.body());
            }

            @Override
            public void onFailure(Call<CatImagesModel> call, Throwable t) {
                Log.e(TAG, "Error fetching cat images", t);
                callback.response(null);
            }
        });
    }
}