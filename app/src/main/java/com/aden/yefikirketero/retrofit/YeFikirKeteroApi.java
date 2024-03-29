package com.aden.yefikirketero.retrofit;

import com.aden.yefikirketero.retrofit.model.PaymentUpload;
import com.aden.yefikirketero.retrofit.model.Person;
import com.aden.yefikirketero.retrofit.model.PhoneNotifications;
import com.aden.yefikirketero.retrofit.model.Post;
import com.aden.yefikirketero.retrofit.model.PostUpload;
import com.aden.yefikirketero.retrofit.model.PostWrapper;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface YeFikirKeteroApi {

    //"http://192.168.43.226:3000";
//    String BASE_URL = "https://fikirketeronodejs.herokuapp.com/";
    String BASE_URL = "https://fikirketeroapinodejs.herokuapp.com/";
//    String BASE_URL = "https://pacific-citadel-12282.herokuapp.com/";


    @Headers("Content-Type: application/json")
    @GET("posts")
    Call<PostWrapper> getPosts(
            @Query("limit") int limit,
            @Query("skip") int skip
    );

    @POST("users")
    Call<Post> uploadPost(
            @Body Post post
    );

    @Headers("Content-Type: application/json")
    @GET("get_phone_notifications/251904132755/{limit}")
    Call<List<Person>> getPhoneNotifications(
            @Path("limit") int calculatedLimit
    );

    @Headers("Content-Type: application/json")
    @GET("received_requests/251904132755/{limit}")
    Call<List<Person>> getReceivedRequests(
            @Path("limit") int calculatedLimit
    );

    @POST("person")
    Call<Person> uploadPerson(
            @Body Person person
    );

    @POST("payment")
    Call<PaymentUpload> uploadPayment(
            @Body PaymentUpload paymentUpload
    );

    @Multipart
    @POST("uploadAudio")
    Call<ResponseBody> upload(
            @Part("description") RequestBody description,
            @Part MultipartBody.Part file
    );

//    @Headers("Content-Type: application/json")
//    @GET("user/userPosts")
//    Call<Post> getPosts();
//
//    @POST("user/userPosts")
//    Call<PostUpload> uploadPost(
//            @Body PostUpload post
//    );
}
