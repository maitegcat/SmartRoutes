package urjc.com.wayfindingapp;


import com.google.firebase.firestore.auth.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;
import urjc.com.wayfindingapp.Model.City;
import urjc.com.wayfindingapp.Model.Guide;

public interface APIInterface {

   @GET("/api/guide")
   Call<List<Guide>> doGetListResources();
/*
   @GET("/api/lugar")
   Call<List<Lugar>> getLugar (); 
   

   @GET("/api/baliza")
   Call<List<Baliza>> getBaliza();*/

   @GET("/api/places")
   Call<ResponseBody> download(@Url String fileUrl);

   @GET("/api/cities/{nameDef}")
   Call<City> find(@Path( "nameDef" ) String nameDef);

   @GET("/api/user")
   Call<User> getLogin();


   }


