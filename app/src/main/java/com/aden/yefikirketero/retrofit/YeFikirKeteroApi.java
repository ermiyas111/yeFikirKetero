package com.aden.yefikirketero.retrofit;

import com.aden.yefikirketero.retrofit.model.Payment;
import com.aden.yefikirketero.retrofit.model.PaymentUpload;
import com.aden.yefikirketero.retrofit.model.Person;
import com.aden.yefikirketero.retrofit.model.PhoneNotifications;
import com.aden.yefikirketero.retrofit.model.Post;
import com.aden.yefikirketero.retrofit.model.PostUpload;

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
    String BASE_URL = "https://fikirketeronodejs.herokuapp.com/";

    @Headers("Content-Type: application/json")
    @GET("posts")
    Call<List<Post>> getPosts();

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

    @POST("users")
    Call<PostUpload> uploadPost(
            @Body PostUpload post
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

    @GET("received_phone_numbers/{id}")
    Call<List<Payment>> getReceivedPhoneNumbers(
            @Path("id") String id
    );
}
