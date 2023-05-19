package dev.beagracia.handsonwork_imobiliaria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import dev.beagracia.handsonwork_imobiliaria.helper.FirebaseConfig;
import dev.beagracia.handsonwork_imobiliaria.model.User;

/**
 * A classe LoginActivity representa a tela de login do usuário.
 */
public class LoginActivity extends AppCompatActivity {
    private Button loginButton;
    private TextView signupText;
    private EditText emailInput;
    private EditText passwordInput;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeComponents();
        signupText.setOnClickListener(v -> goToSignupActivity());
        loginButton.setOnClickListener(loginClick());
    }
    /**
     * Inicializa os componentes da tela.
     */
    public void initializeComponents() {
        loginButton = findViewById(R.id.loginButton);
        signupText = findViewById(R.id.signupTextLoginPage);
        emailInput = findViewById(R.id.loginActivityEmailInput);
        passwordInput = findViewById(R.id.loginActivityPasswordInput);
    }

    /**
     * Redireciona para a tela de cadastro.
     */
    public void goToSignupActivity() {
        Intent signupIntent = new Intent(this, SignUpActivity.class);
        startActivity(signupIntent);
    }
    /**
     * Retorna um objeto OnClickListener para o botão de login.
     *
     * @return O objeto OnClickListener.
     */
    public View.OnClickListener loginClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailInput.getText().toString();
                String password = passwordInput.getText().toString();

                if(!email.isEmpty() && !password.isEmpty()) {
                    User user = new User();
                    user.setEmail(email);
                    user.setPassword(password);
                    validateLogin(user);
                } else {
                    Toast.makeText(LoginActivity.this, "Por favor, preencha suas informacoes", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }
    /**
     * Valida o login do usuário.
     *
     * @param user O objeto User com os dados do usuário.
     */
    private void validateLogin(User user) {
        firebaseAuth = FirebaseConfig.getFirebaseAuth();
        firebaseAuth.signInWithEmailAndPassword(user.getEmail(), user.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    goToLoggedInHome();
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Erro ao fazer login", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    /**
     * Redireciona para a tela principal após o login.
     */
    public void goToLoggedInHome() {
        Intent intent = new Intent(this, LoggedInHomeActivity.class);
        startActivity(intent);
    }
}