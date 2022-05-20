package edu.eci.ieti.betsgo.service;

import edu.eci.ieti.betsgo.entity.GenericResponse;
import edu.eci.ieti.betsgo.entity.dto.UserDto;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.POST;

public interface UserService {

    String path = "/v1/user";

    @POST(path + "/")
    Call<GenericResponse<UserDto>> login(@Field("email") String email, @Field("password") String password);
}