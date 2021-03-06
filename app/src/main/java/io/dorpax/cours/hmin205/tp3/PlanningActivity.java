package io.dorpax.cours.hmin205.tp3;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;

import java.io.*;
import java.util.Random;

public class PlanningActivity extends AppCompatActivity {

    private final static String TASK_FILENAME = "tasks.txt";

    private PlanningModel planningModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planning);

        planningModel = new ViewModelProvider(this).get(PlanningModel.class);
        TextView slot1 = findViewById(R.id.h1);
        planningModel.getSlot(0).observe(this, slot1::setText);
        TextView slot2 = findViewById(R.id.h2);
        planningModel.getSlot(1).observe(this, slot2::setText);
        TextView slot3 = findViewById(R.id.h3);
        planningModel.getSlot(2).observe(this, slot3::setText);
        TextView slot4 = findViewById(R.id.h4);
        planningModel.getSlot(3).observe(this, slot4::setText);
    }

    public void update(View view) throws IOException {
        File file = new File(TASK_FILENAME);
        if (!file.exists()) {
            FileOutputStream fileOutputStream = openFileOutput(TASK_FILENAME, Context.MODE_PRIVATE);
            for (int i = 0; i < 100; i++) {
                fileOutputStream.write((i + "\n").getBytes());
            }
            fileOutputStream.close();
        }

        FileInputStream fileInputStream = openFileInput(TASK_FILENAME);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        int start = new Random().nextInt(96);
        for (int i = 0; i < start; i++) bufferedReader.readLine();

        for (int i = 0; i < 4; i++) {
            planningModel.setSlot(i, bufferedReader.readLine());
        }
        fileInputStream.close();
    }
}
