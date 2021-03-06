package io.dorpax.cours.hmin205.tp3.data.dao;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import io.dorpax.cours.hmin205.tp3.data.entities.Planning;

import java.util.List;

@Dao
public interface PlanningDao {
    @Query("SELECT * FROM planning")
    List<Planning> getAll();

    @Query("SELECT * FROM planning WHERE uid == (:uid)")
    LiveData<Planning> getPlanning(int uid);

    @Query("SELECT count(*) FROM planning")
    LiveData<Integer> nbPlannings();

    @Insert
    void insertAll(Planning... plannings);

    @Insert
    void insert(Planning planning);

    @Delete
    void delete(Planning planning);
}
