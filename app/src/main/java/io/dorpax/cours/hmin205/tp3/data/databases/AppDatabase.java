package io.dorpax.cours.hmin205.tp3.data.databases;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import io.dorpax.cours.hmin205.tp3.data.dao.PlanningDao;
import io.dorpax.cours.hmin205.tp3.data.entities.Planning;

@Database(entities = {Planning.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PlanningDao planningDao();
}
