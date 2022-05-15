package com.example.bgo.network.service;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

import com.example.bgo.network.dto.LoginDto;
import com.example.bgo.network.dto.TokenDto;

public interface AuthService {

    @POST("auth")
    Observable<TokenDto> auth(@Body LoginDto loginDto);

}