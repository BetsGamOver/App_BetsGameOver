package edu.eci.ieti.betsgo.activitySidebar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.sql.Date;
import java.sql.Time;

import edu.eci.ieti.betsgo.R;
import edu.eci.ieti.betsgo.entity.dto.UserDto;
import edu.eci.ieti.betsgo.utils.DateSerializer;
import edu.eci.ieti.betsgo.utils.TimeSerializer;
import edu.eci.ieti.betsgo.viewModel.UserViewModel;

public class MainActivity extends AppCompatActivity {

    private EditText edtEmail;
    private EditText edtPassword;
    private Button loginBtn;
    private UserViewModel viewModel;
    private TextView txtInputUsuario;
    private TextView txtInputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initViewModel();
        this.init();
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(UserViewModel.class);
    }

    private void init() {
        edtEmail = findViewById(R.id.edtMail);
        edtPassword = findViewById(R.id.edtPassword);
        loginBtn = findViewById(R.id.btnIniciarSesion);
        loginBtn.setOnClickListener(v -> {
            viewModel.login(edtEmail.getText().toString(), edtPassword.getText().toString()).observe(this, response -> {
                if(response.getAnswer() == 1) {
                    Toast.makeText(this, response.getMessage(), Toast.LENGTH_SHORT).show();
                    UserDto u = response.getBody();
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                    SharedPreferences.Editor editor = preferences.edit();
                    final Gson g = new GsonBuilder()
                            .registerTypeAdapter(Date.class, new DateSerializer())
                            .registerTypeAdapter(Time.class, new TimeSerializer())
                            .create();
                    editor.putString("JsonUser", g.toJson(u, new TypeToken<UserDto>(){
                    }.getType()));
                    editor.apply();
                    edtEmail.setText("");
                    edtPassword.setText("");
                    startActivity(new Intent(this, UserInfoActivity.class));
                }
                else {
                    Toast.makeText(this, "An error ocurred: "+response.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}