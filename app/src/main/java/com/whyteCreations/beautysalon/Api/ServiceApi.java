package com.whyteCreations.beautysalon.Api;


import com.whyteCreations.beautysalon.Model.Salon;
import com.whyteCreations.beautysalon.Model.SaloneResponse;
import com.whyteCreations.beautysalon.Model.UpdateUserResponse;
import com.whyteCreations.beautysalon.Model.UserModel;
import com.whyteCreations.beautysalon.Model.UserResponse;
import com.whyteCreations.beautysalon.Model.updateUser;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServiceApi {
    //User Register
    @FormUrlEncoded
    @POST("register")
    Call<UserResponse> registerUser(@Field("name") String name,
                                    @Field("contact_no") String contcatnumber,
                                    @Field("email") String email,
                                    @Field("password") String password,
                                    @Field("device_type") String devicetype,
                                    @Field("device_token") String devicetoken);

    //Update User
    @FormUrlEncoded
    @POST("update_profile")
    Call<UpdateUserResponse> updateProfile(
            @Field("user_id") String userid,
            @Field("name") String name,
            @Field("location") String location,
            @Field("building_number") String building_number,
            @Field("zone_number") String zone_number,
            @Field("street_number") String street_number,
            @Field("street_name") String street_name);



    //ContentDatas List
    @POST("search_business")
    Call<SaloneResponse> getList();

    //ContentDatas List
    @POST("search_business")
    Call<SaloneResponse> getSearchList(@Query("business_name")String busnessname);
}
