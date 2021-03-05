package io.dorpax.cours.hmin205.tp3;

import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private final static String USERID_KEY = "io.dorpax.cours.hmin205.tp3.keys.USERID";
    public final static String FILENAME_EXTRA = "io.dorpax.cours.hmin205.tp3.extras.FILENAME";
    private String userID;

    private Utilisation utilisation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        utilisation = new Utilisation();
        getLifecycle().addObserver(utilisation);

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

    public void onSubmit(View view) throws IOException {
        String name = ((EditText) findViewById(R.id.nameEditText)).getText().toString();
        String fileName = name + "-" + userID;
        FileOutputStream fileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
        ConstraintLayout layout = findViewById(R.id.main_layout);

        for (int i = 0; i < layout.getChildCount(); i++) {
            View child = layout.getChildAt(i);
            if (child instanceof EditText) {
                fileOutputStream.write((((EditText) child).getText().toString() + "\n").getBytes());
            }
        }

        fileOutputStream.write(("Utilisations : " + utilisation.getNbUtilisations() + "\n").getBytes());

        fileOutputStream.close();

        Intent intent = new Intent(this, SubmitActivity.class);
        intent.putExtra(FILENAME_EXTRA, fileName);
        startActivity(intent);
    }

    public void displayPlanning(View view) {
        Intent intent = new Intent(this, PlanningActivity.class);
        startActivity(intent);
    }

    private String generateUserID() {
        return new Random().nextInt(10000) + "";
    }

    private void displayUserID() {
        Toast.makeText(this, "Identifiant : " + userID, Toast.LENGTH_SHORT).show();
    }
}
