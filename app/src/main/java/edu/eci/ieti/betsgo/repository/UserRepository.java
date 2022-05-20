package edu.eci.ieti.betsgo.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import edu.eci.ieti.betsgo.api.ApiConfig;
import edu.eci.ieti.betsgo.entity.GenericResponse;
import edu.eci.ieti.betsgo.entity.dto.UserDto;
import edu.eci.ieti.betsgo.service.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    private static UserRepository repository;
    private final UserService service;

    public UserRepository() {
        service = ApiConfig.getUserService();
    }

    public static UserRepository getInstance() {
        if (repository == null) {
            repository = new UserRepository();
        }
        return repository;
    }

    public LiveData<GenericResponse<UserDto>> login(String email, String password) {
        final MutableLiveData<GenericResponse<UserDto>> liveData = new MutableLiveData<>();
        this.service.login(email, password).enqueue(new Callback<GenericResponse<UserDto>>() {
            @Override
            public void onResponse(Call<GenericResponse<UserDto>> call, Response<GenericResponse<UserDto>> response) {
                liveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<GenericResponse<UserDto>> call, Throwable t) {
                liveData.setValue(new GenericResponse());
                System.out.println("---- An error has been thrown while trying login: "+t.getMessage());
                t.printStackTrace();
            }
        });
        return liveData;
    }
}