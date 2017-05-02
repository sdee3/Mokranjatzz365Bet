package rs.stefandjokic.mokranjatzz365bet.api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rs.stefandjokic.mokranjatzz365bet.models.Result;

public interface APIService {

    //Register:
    @FormUrlEncoded
    @POST("register")
    Call<Result> createUser(
            @Field("username") String username,
            @Field("password") String password,
            @Field("email") String email,
            @Field("full_name") String full_name
    );

}
