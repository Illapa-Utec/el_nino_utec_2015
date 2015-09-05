package com.illapa.appalerta.request;

import com.illapa.appalerta.model.entity.EventResponse;

import java.util.List;
import java.util.Objects;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

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
                           Callback<EventResponse> callback);
        //lng , lat, phone, obs, cat

        /*//login /servicios/login.php?ema=xxx@yyy.zzz&cla=123
        @GET("/servicios/login.php")
        void login(@Query("ema") String username, @Query("cla") String password, Callback<LoginResponse[]> callback);

        //servicios/registro.php?tip=1&nom=XXX&ape=YYY%20ZZZ&tel=1111111&cel=111111111&dir=XXX%20YYY%20ZZZ&sex=1&ema=xxx@yyy.zzz&cla=123
        @GET("/servicios/registro.php")
        void register(@Query("tip") Integer tip, @Query("nom") String nom, @Query("ape") String ape, @Query("cel") String tel,
                      @Query("cel") String cel, @Query("dir") String dir, @Query("sex") int sex,
                      @Query("ema") String ema, @Query("cla") String cla, Callback<BaseResponse[]> callback);

        //servicios/curso.php?cat=1&bus=mat
        @GET("/servicios/curso.php")
        void curso(@Query("cat") Integer cat, @Query("bus") String bus, Callback<String> callback);

        //servicios/reset.php?ema=xxx@yyy.zzz&cla=54321
        @GET("/servicios/reset.php")
        void reset(@Query("ema") String ema, @Query("cla") String cla, Callback<String> callback);

        //servicios/sugerencia.php?cat=2&tip=2&nom=PRUEBA%20101&id_usu=7&est=2
        @GET("/servicios/sugerencia.php")
        void sugerencia(@Query("cat") Integer cat, @Query("tip") Integer tip, @Query("nom") String nom,
                        @Query("id_usu") Integer id_usu, @Query("est") Integer est, Callback<String> callback);
        */
    }
}
