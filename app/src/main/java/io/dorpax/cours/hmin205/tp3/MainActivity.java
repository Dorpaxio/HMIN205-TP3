package io.dorpax.cours.hmin205.tp3;

import android.os.PersistableBundle;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private final static String USERID_KEY = "io.dorpax.cours.hmin205.tp3.USERID_KEY";
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if ((savedInstanceState != null) && (savedInstanceState.containsKey(USERID_KEY))) {
            userID = savedInstanceState.getString(USERID_KEY);
        } else {
            userID = generateUserID();
        }

        displayUserID();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        EditText passwordEditTExt = findViewById(R.id.passwordEditText);
        passwordEditTExt.setText(null);
        // Les autres champs sont gardés par défaut.

        savedInstanceState.putString(USERID_KEY, userID);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        displayUserID();
    }

    private String generateUserID() {
        return new Random().nextInt(10000) + "";
    }

    private void displayUserID() {
        Toast.makeText(this, "Identifiant : " + userID, Toast.LENGTH_SHORT).show();
    }
}
