package com.example.myweight;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WeightEntryDao {
    @Insert
    void insertWeightEntry(WeightEntry weightEntry);

    @Delete
    void deleteWeightEntry(WeightEntry weightEntry);

    @Update
    void updateWeightEntry(WeightEntry weightEntry);

    @Query("SELECT * FROM weight_entries")
    List<WeightEntry> getAllWeightEntries();
}

