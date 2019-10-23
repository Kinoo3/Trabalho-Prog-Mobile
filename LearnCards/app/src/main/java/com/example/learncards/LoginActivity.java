package com.example.learncards;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.learncards.Database.AppDatabase;
import com.example.learncards.Entities.User;
import com.example.learncards.ViewModel.UserViewModel;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private UserViewModel userViewModel;

    private EditText emailText;
    private EditText passwordText;

    private TextView errorsText;


    //TODO: deixar essa classe como static para evitar leaks na memória
    class LoginTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            AppDatabase mydb = AppDatabase.getInstance(LearnCards.getAppContext());

            List<User> users = mydb.userDao().getAllUsers2();

            for (User user : users) {
                if (user.getEmail().equals(emailText.getText().toString()) && user.getPassword().equals(passwordText.getText().toString())) {
                    Log.i("[ LOGIN ]", "Sucesso ! Logando como usuário: " + user.getName() + " / Email: " + user.getEmail());
                    errorsText.setText(null);
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    return null;
                }
            }

            errorsText.setText("Usuário ou senha incorreto !");

            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.passwordText);

        errorsText = findViewById(R.id.textErrors);

        /**
         * Que porra é essa fernando ? kkkk
         */
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                //update RecycleView
            }
        });

        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginTask loginTask = new LoginTask();
                loginTask.execute();
            }
        });
    }
}
