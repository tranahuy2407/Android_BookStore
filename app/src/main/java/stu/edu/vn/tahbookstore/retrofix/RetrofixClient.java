package stu.edu.vn.tahbookstore.retrofix;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofixClient {
    private static Retrofit instane;
    public  static Retrofit getInstane(String baseURL){
        if(instane == null){
            instane = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();
        }
        return instane;
    }

}
