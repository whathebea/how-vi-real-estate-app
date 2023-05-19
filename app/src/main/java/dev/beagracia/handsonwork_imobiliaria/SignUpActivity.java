package dev.beagracia.handsonwork_imobiliaria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import dev.beagracia.handsonwork_imobiliaria.helper.FirebaseConfig;
import dev.beagracia.handsonwork_imobiliaria.model.User;

/**
 * A classe SignUpActivity representa a tela de cadastro de usuários.
 */
public class SignUpActivity extends AppCompatActivity {

    private EditText firstNameInput;
    private EditText lastNameInput;
    private EditText emailInput;
    private EditText passwordInput;
    private EditText confirmPasswordInput;
    private Button signupButton;
    private User user;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initializeComponents();
        signupButton.setOnClickListener(signupClick());
    }
    /**
     * Inicializa os componentes da tela.
     */
    public void initializeComponents() {
        firstNameInput = findViewById(R.id.nameInputSignup);
        lastNameInput = findViewById(R.id.lastNameInputSignup);
        emailInput = findViewById(R.id.emailInputSignup);
        passwordInput = findViewById(R.id.passwordInputSignup);
        confirmPasswordInput = findViewById(R.id.confirmPasswordInputSignup);
        signupButton = findViewById(R.id.buttonSignup);
    }
    /**
     * Retorna um objeto OnClickListener para o botão de cadastro.
     *
     * @return O objeto OnClickListener.
     */
    public View.OnClickListener signupClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = firstNameInput.getText().toString();
                String lastName = lastNameInput.getText().toString();
                String email = emailInput.getText().toString();
                String password = passwordInput.getText().toString();
                String confirmPassword = confirmPasswordInput.getText().toString();

                if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(confirmPassword)) {
                    Toast.makeText(SignUpActivity.this, "As senhas devem ser iguais", Toast.LENGTH_SHORT).show();
                } else {
                    user = new User();
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setEmail(email);
                    user.setPassword(password);
                    signupUser(user);
                }
            }
        };
    }
    /**
     * Realiza o cadastro do usuário.
     *
     * @param user O objeto User com os dados do usuário.
     */
    public void signupUser(User user) {
        firebaseAuth = FirebaseConfig.getFirebaseAuth();
        firebaseAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword()).addOnCompleteListener(
                this,
                task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(SignUpActivity.this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
                        goToLoggedInScreen();
                        finish();
                    } else {
                        String exception;
                        try {
                            throw task.getException();
                        } catch (FirebaseAuthWeakPasswordException e) {
                            exception = "Digite uma senha mais forte";
                        } catch (FirebaseAuthInvalidCredentialsException e) {
                            exception = "Digite um e-mail válido";
                        } catch (FirebaseAuthUserCollisionException e) {
                            exception = "Esse usuario ja existe!";
                        } catch (Exception e) {
                            exception = "Erro ao cadastrar usuario: " + e.getMessage();
                        }
                        Toast.makeText(SignUpActivity.this, exception, Toast.LENGTH_SHORT);
                    }
                }
        );
    }

    /**
     * Redireciona para a tela principal após o login.
     */
    public void goToLoggedInScreen() {
        Intent loggedInHomeActivity = new Intent(this, LoggedInHomeActivity.class);
        startActivity(loggedInHomeActivity);
    }
}