package com.example.jakfoodcrud.network;

import com.example.jakfoodcrud.model.ResponseKategori;
import com.example.jakfoodcrud.model.ResponseMakanan;
import com.example.jakfoodcrud.model.ResponseUser;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @FormUrlEncoded
    @POST("registeruser.php")
    Call<ResponseUser> requestRegister(
            @Field("vsnama") String nama,
            @Field("vsalamat") String alamat,
            @Field("vsjenkel") String jengkel,
            @Field("vsnotelp") String notelp,
            @Field("vsusername") String username,
            @Field("vslevel") String level,
            @Field("vspassword") String password


    );

    @FormUrlEncoded
    @POST("loginuser.php")
    Call<ResponseUser> requestLogin(
            @Field("edtusername") String username,
            @Field("edtpassword") String password,
            @Field("vslevel") String level
    );

    @FormUrlEncoded
    @POST("getdatamakanan.php")
    Call<ResponseMakanan> getDataMakanan (
            @Field("vsiduser") String idUser,
            @Field("vsidkastrkategorimakanan") String kategoriMakanan
    );

    @GET("kategorimakanan.php")
    Call<ResponseKategori> getKategoriMakanan();

    @FormUrlEncoded
    @POST("deletedatamakanan.php")
    Call<ResponseUser> deleteData (
            @Field("vsidmakanan") String idMakanan
    );
}
