package io.dorpax.cours.hmin205.tp3.data.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Planning {

    public Planning(int uid, String task1, String task2, String task3, String task4) {
        this.uid = uid;
        this.task1 = task1;
        this.task2 = task2;
        this.task3 = task3;
        this.task4 = task4;
    }

    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "task1")
    public String task1;

    @ColumnInfo(name = "task2")
    public String task2;

    @ColumnInfo(name = "task3")
    public String task3;

    @ColumnInfo(name = "task4")
    public String task4;
}
