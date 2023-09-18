package com.example.myweight;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "weight_entries")
public class WeightEntry {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private double weight;
    private String date;
    private int age;

    public WeightEntry(double weight, String date, int age) {
        this.weight = weight;
        this.date = date;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
