package com.example.myweight;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {WeightEntry.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract WeightEntryDao weightEntryDao();
}
