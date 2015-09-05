package com.illapa.appalerta.request;

import com.illapa.appalerta.model.entity.BaseResponse;
import com.illapa.appalerta.model.entity.EventEntity;
import com.illapa.appalerta.model.entity.EventsResponse;
import com.illapa.appalerta.model.entity.ReportResponse;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

public class ApiClient {

    private static final String PATH="http://45.55.32.116";
    private static IllapaApiInterface illapaApiInterface;

    public static IllapaApiInterface getILlapaApiClient() {
        if (illapaApiInterface == null) {
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(PATH)
                    .build();
            illapaApiInterface = restAdapter.create(IllapaApiInterface.class);
        }
        return illapaApiInterface;
    }


    public interface IllapaApiInterface
    {
        @FormUrlEncoded
        @POST("/events/register")
        void registerEvent(@Field("lat") double lat, @Field("lng") double lng, @Field("uuid") String uuid,
                           @Field("cat") int cat , @Field("obs") String obs,
                           Callback<ReportResponse> callback);
        //lng , lat, phone, obs, cat
        @FormUrlEncoded
        @POST("/phone/register")
        void registerPhone(@Field("id") String id,@Field("uuid") String uuid, Callback<BaseResponse> callback);

        @GET("/events/list")
        void events( Callback<EventsResponse> callback);

        /*//servicios/registro.php?tip=1&nom=XXX&ape=YYY%20ZZZ&tel=1111111&cel=111111111&dir=XXX%20YYY%20ZZZ&sex=1&ema=xxx@yyy.zzz&cla=123
        @GET("/servicios/registro.php")
        void register(@Query("tip") Integer tip, @Query("nom") String nom, @Query("ape") String ape, @Query("cel") String tel,
                      @Query("cel") String cel, @Query("dir") String dir, @Query("sex") int sex,
                      @Query("ema") String ema, @Query("cla") String cla, Callback<BaseResponse[]> callback);

        //servicios/curso.php?cat=1&bus=mat
        @GET("/servicios/curso.php")
        void curso(@Query("cat") Integer cat, @Query("bus") String bus, Callback<String> callback);*/
    }
}
