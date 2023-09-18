package com.example.myweight;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class TrackWeight extends AppCompatActivity {
    private AppDatabase database;
    private ListView listWeights;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_weight);

        // Get the database instance from MyApp
        database = MyApp.getDatabase();

        // Initialize ListView
        listWeights = findViewById(R.id.listWeights);

        // Retrieve weight entries from the database
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<WeightEntry> weightEntries = database.weightEntryDao().getAllWeightEntries();

                // Create an array of weight entry strings
                String[] weightStrings = new String[weightEntries.size()];
                for (int i = 0; i < weightEntries.size(); i++) {
                    WeightEntry entry = weightEntries.get(i);
                    weightStrings[i] = "Weight: " + entry.getWeight() + " kg\nDate: " + entry.getDate() + "\nAge: " + entry.getAge();
                }

                // Create an ArrayAdapter to populate the ListView
                ArrayAdapter<String> adapter = new ArrayAdapter<>(TrackWeight.this, R.layout.list_item_layout, R.id.textWeight, weightStrings) {
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);

                        ImageButton btnDelete = view.findViewById(R.id.btnDelete);
                        ImageButton btnUpdate = view.findViewById(R.id.btnUpdate);

                        // Set click listeners for delete and update buttons
                        btnDelete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // Handle delete button click
                                deleteWeightEntry(position);
                            }
                        });

//                        btnUpdate.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                // Handle update button click
//                                updateWeightEntry(position);
//                            }
//                        });

                        return view;
                    }
                };

                // Set ArrayAdapter on ListView
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listWeights.setAdapter(adapter);
                    }
                });
            }
        }).start();
    }

    private void deleteWeightEntry(int position) {
        // Retrieve weight entries from database
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<WeightEntry> weightEntries = database.weightEntryDao().getAllWeightEntries();

                if (position >= 0 && position < weightEntries.size()) {
                    WeightEntry entry = weightEntries.get(position);
                    database.weightEntryDao().deleteWeightEntry(entry);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            refreshListView();
                        }
                    });
                }
            }
        }).start();
    }

//    private void updateWeightEntry(int position) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                List<WeightEntry> weightEntries = database.weightEntryDao().getAllWeightEntries();
//
//                if (position >= 0 && position < weightEntries.size()) {
//                    WeightEntry entry = weightEntries.get(position);
//
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            // Start the TrackWeight activity with existing values for updating the weight entry
//                            Intent intent = new Intent(TrackWeight.this, EditWeight.class);
//                            intent.putExtra("weight_entry_id", entry.getId());
//                            intent.putExtra("weight", entry.getWeight());
//                            intent.putExtra("date", entry.getDate());
//                            intent.putExtra("age", entry.getAge());
//                            startActivity(intent);
//                        }
//                    });
//                }
//            }
//        }).start();
//    }


    private void refreshListView() {
        // Retrieve weight entries from database and update ListView
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<WeightEntry> weightEntries = database.weightEntryDao().getAllWeightEntries();

                // Create array of weight entry strings
                String[] weightStrings = new String[weightEntries.size()];
                for (int i = 0; i < weightEntries.size(); i++) {
                    WeightEntry entry = weightEntries.get(i);
                    weightStrings[i] = "Weight: " + entry.getWeight() + " kg\nDate: " + entry.getDate() + "\nAge: " + entry.getAge();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(TrackWeight.this, R.layout.list_item_layout, R.id.textWeight, weightStrings);
                        listWeights.setAdapter(adapter);
                    }
                });
            }
        }).start();
    }



}
