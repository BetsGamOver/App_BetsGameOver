package edu.eci.ieti.betsgo.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import edu.eci.ieti.betsgo.entity.GenericResponse;
import edu.eci.ieti.betsgo.entity.dto.UserDto;
import edu.eci.ieti.betsgo.repository.UserRepository;

public class UserViewModel extends AndroidViewModel {

    private final UserRepository repository;

    public UserViewModel(@NonNull Application application) {
        super(application);
        this.repository  = UserRepository.getInstance();
    }

    public LiveData<GenericResponse<UserDto>> login(String email, String password) {
        return this.repository.login(email, password);
    }
}