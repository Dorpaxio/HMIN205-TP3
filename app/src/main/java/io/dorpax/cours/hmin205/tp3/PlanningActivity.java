package io.dorpax.cours.hmin205.tp3;

import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;

public class PlanningActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planning);

        PlanningModel planningModel = new ViewModelProvider(this).get(PlanningModel.class);
        TextView slot1 = findViewById(R.id.h1);
        slot1.setText(planningModel.getSlot(0));
        TextView slot2 = findViewById(R.id.h2);
        slot2.setText(planningModel.getSlot(1));
        TextView slot3 = findViewById(R.id.h3);
        slot3.setText(planningModel.getSlot(2));
        TextView slot4 = findViewById(R.id.h4);
        slot4.setText(planningModel.getSlot(3));
    }
}
