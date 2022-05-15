package com.example.bgo.network.service;

import com.example.bgo.network.dto.UserDto;

import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {

    @POST("/v1/user")
    UserDto create(@Body UserDto userDto, @Query("referedUser") String referedUser);

}
