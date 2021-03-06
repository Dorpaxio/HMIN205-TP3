package io.dorpax.cours.hmin205.tp3;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;
import io.dorpax.cours.hmin205.tp3.data.databases.AppDatabase;
import io.dorpax.cours.hmin205.tp3.data.dao.PlanningDao;
import io.dorpax.cours.hmin205.tp3.data.entities.Planning;

import java.io.*;
import java.util.*;

public class PlanningActivity extends AppCompatActivity {

    private final static String LAST_UPDATE_FILENAME = "last_update";

    private PlanningModel planningModel;

    private AppDatabase appDatabase;
    private PlanningDao planning;

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

        appDatabase = Room.databaseBuilder(this, AppDatabase.class, "db-tp3")
                .allowMainThreadQueries().build();
        planning = appDatabase.planningDao();

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (shouldUpdate()) {
                    try {
                        update(null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 0, 600000);
    }

    public void update(View view) throws IOException {
        planning.nbPlannings().observe(this, nbPlannings -> {
            System.out.println(nbPlannings);
            if (nbPlannings == 0) {
                Random rand = new Random();
                for (int i = 0; i < 10; i++) {
                    planning.insert(new Planning(i, rand.nextInt(100) + "",
                            rand.nextInt(100) + "", rand.nextInt(100) + "",
                            rand.nextInt(100) + ""));

                }
                return;
            }

            planning.getPlanning(new Random().nextInt(10)).observe(this, p -> {
                planningModel.setSlot(0, p.task1);
                planningModel.setSlot(1, p.task2);
                planningModel.setSlot(2, p.task3);
                planningModel.setSlot(3, p.task4);
            });
        });


        updateLastUpdate();
    }

    private Date getLastUpdate() throws IOException {
        if (!fileExists(LAST_UPDATE_FILENAME)) {
            updateLastUpdate();
        }

        FileInputStream fileInputStream = openFileInput(LAST_UPDATE_FILENAME);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        long lastUpdateMillis;
        try {
            lastUpdateMillis = Long.parseLong(bufferedReader.readLine());
        } catch (NumberFormatException ex) {
            return new Date(0);
        }

        System.out.println(lastUpdateMillis);

        fileInputStream.close();

        return new Date(lastUpdateMillis);
    }

    private boolean shouldUpdate() {
        try {
            return new Date().getTime() - getLastUpdate().getTime() >= 86400000;
        } catch (IOException ex) {
            return true;
        }
    }

    private void updateLastUpdate() throws IOException {
        FileOutputStream fileOutputStream = openFileOutput(LAST_UPDATE_FILENAME, Context.MODE_PRIVATE);
        fileOutputStream.write(((new Date()).getTime() + "").getBytes());
        fileOutputStream.close();
    }

    private boolean fileExists(String filename) {
        boolean exists = false;
        for (String f : fileList()) {
            if (f.equals(filename)) {
                exists = true;
                break;
            }
        }
        return exists;
    }
}
