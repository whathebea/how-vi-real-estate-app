package dev.beagracia.handsonwork_imobiliaria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import dev.beagracia.handsonwork_imobiliaria.adapter.PropertyAdapter;
import dev.beagracia.handsonwork_imobiliaria.helper.FirebaseConfig;
import dev.beagracia.handsonwork_imobiliaria.model.Property;
import dev.beagracia.handsonwork_imobiliaria.retrofit.PropertyApi;
import dev.beagracia.handsonwork_imobiliaria.retrofit.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoggedInHomeActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private RecyclerView propertiesView;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in_home);
        initializeComponents();
        toolbarConfig();
        setFirebaseAuth();
        propertiesView.setLayoutManager(new LinearLayoutManager(this));
        loadProperties();
    }

    public void initializeComponents() {
        toolbar = findViewById(R.id.toolbarMain);
        propertiesView = findViewById(R.id.recyclerViewProperties);
    }

    public void setFirebaseAuth() {
        firebaseAuth = FirebaseConfig.getFirebaseAuth();
    }
    public void toolbarConfig() {
        toolbar.setTitle("Imóveis Itajaí");
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_leave) {
            logOffUser();
            goToLoginScreen();
        }
        return super.onOptionsItemSelected(item);
    }

    private void logOffUser() {
        try {
            firebaseAuth.signOut();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void goToLoginScreen() {
        Intent loginActivity = new Intent(this, LoginActivity.class);
        startActivity(loginActivity);
    }

    private void loadProperties() {
        RetrofitService retrofitService = new RetrofitService();
        PropertyApi propertyApi = retrofitService.getRetrofit().create(PropertyApi.class);

        propertyApi.getProperties().enqueue(new Callback<List<Property>>() {
            @Override
            public void onResponse(Call<List<Property>> call, Response<List<Property>> response) {
                populateListView(response.body());
                System.out.println(response);
            }

            @Override
            public void onFailure(Call<List<Property>> call, Throwable t) {
                Toast.makeText(LoggedInHomeActivity.this, "Nao foi possivel conectar", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void populateListView(List<Property> propertyList) {
        PropertyAdapter adapter = new PropertyAdapter(propertyList);
        propertiesView.setAdapter(adapter);
    }
}