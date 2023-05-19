package dev.beagracia.handsonwork_imobiliaria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

/**
 * A classe MainActivity representa a tela inicial da aplicação.
 */
public class MainActivity extends AppCompatActivity {


    private Button cadastroButton;
    private TextView entrarText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeComponents();
        cadastroButton.setOnClickListener(v -> goToSignupActivity());
        entrarText.setOnClickListener(v -> goToLoginActivity());
    }
    /**
     * Inicializa os componentes da tela.
     */
    public void initializeComponents() {
        cadastroButton = findViewById(R.id.buttonCadastroInicial);
        entrarText = findViewById(R.id.textLoginInicial);
    }
    /**
     * Abre a tela de cadastro.
     */
    public void goToSignupActivity() {
        Intent signupIntent = new Intent(this, SignUpActivity.class);
        startActivity(signupIntent);
    }
    /**
     * Abre a tela de login.
     */
    public void goToLoginActivity() {
        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);
    }



}